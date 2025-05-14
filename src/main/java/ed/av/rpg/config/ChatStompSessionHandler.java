package ed.av.rpg.config;

import ed.av.rpg.event.ChatMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class ChatStompSessionHandler extends StompSessionHandlerAdapter {

    private final ApplicationEventPublisher eventPublisher;
    private final ChatSession chatSession;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Подключено к серверу WebSocket!");
        session.subscribe("/topic/messages", this);
        chatSession.setSession(session);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("Ошибка: " + exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        eventPublisher.publishEvent(new ChatMessageEvent(payload.toString()));
    }
}