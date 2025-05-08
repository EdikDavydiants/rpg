package ed.av.rpg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
public class MessageRedirector {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendToTopic(String message) {
        messagingTemplate.convertAndSend("/topic/messages", message);  // Отправка в топик
    }
}
