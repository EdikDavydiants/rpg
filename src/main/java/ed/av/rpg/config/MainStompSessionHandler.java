package ed.av.rpg.config;

import ed.av.rpg.Logger;
import ed.av.rpg.auth.connection.AuthenticationData;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.util.ClassTypeExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class MainStompSessionHandler extends StompSessionHandlerAdapter {

    private final MainSession mainSession;
    private final ClassTypeExecutor classTypeExecutor;
    private final AuthenticationData authData;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Подключено к серверу.");
        session.subscribe("/topic/common", this);
        mainSession.setSession(session);
        mainSession.setHandler(this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("Ошибка: " + exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        List<String> values = headers.get("simple-class-name");
        if (values == null || values.isEmpty()) {
            throw new RuntimeException("Нет заголовка 'simple-class-name'!");
        }
        return classTypeExecutor.getType(values.get(0));
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        if (headersFailCheck(headers)) return;

        Topical topical;
        if((topical = castToTopical(payload)) != null) {
            classTypeExecutor.handleTopical(topical);
        }
    }

    private boolean headersFailCheck(StompHeaders headers) {
        List<String> sessionIdValues = headers.get("session-id");
        List<String> userIdValues = headers.get("user-id");

        if (!(userIdValues == null || userIdValues.isEmpty())) {
            String userId = userIdValues.get(0);
            if (!authData.compareUserId(userId)) {
                return !authData.isUserIdNull();
            }
        } else {
            if (sessionIdValues == null || sessionIdValues.isEmpty()) {
                Logger.log("Нет хотя бы одного заголовка в ответе сервера: 'session-id', 'user-id'");
                return true;
            }
            String sessionId = sessionIdValues.get(0);
            return !mainSession.compareSessionId(sessionId);
        }
        return false;
    }

    private Topical castToTopical(Object payload) {
        if (!(payload instanceof Topical)) {
            Logger.log("Payload должен быть типа Topical!");
            return null;
        }
        return (Topical) payload;
    }
}
