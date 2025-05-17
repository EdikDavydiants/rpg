package ed.av.rpg.config;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Objects;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class ChatStompSessionHandler extends StompSessionHandlerAdapter {

    private final ApplicationEventPublisher eventPublisher;
    private final MainSession mainSession;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Подключено к серверу!");
        session.subscribe("/topic/messages", this);
        session.subscribe("/topic/common", this);
        mainSession.setSession(session);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("Ошибка: " + exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        if (headers.getContentType() != null &&
                MediaType.APPLICATION_JSON.includes(headers.getContentType())) {
            return RegisterDto.class;
        }
        return Object.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        if (payload instanceof SimpleMessageDto) {
            if (Objects.equals(((SimpleMessageDto) payload).sessionId(), mainSession.getSession().getSessionId())) {
                eventPublisher.publishEvent(payload);
            }
        }
        eventPublisher.publishEvent(payload);
    }
}
