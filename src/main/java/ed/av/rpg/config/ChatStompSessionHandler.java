package ed.av.rpg.config;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@SuppressWarnings("NullableProblems")
//@Component
public class ChatStompSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Подключено к серверу WebSocket!");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("Ошибка: " + exception.getMessage());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;  // Ожидаем строковые сообщения
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Получено сообщение: " + payload);  // Принимаем сообщения из /topic/messages
    }
}