package ed.av.rpg.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")  // Клиенты отправляют сюда сообщения (/app/chat)
    @SendTo("/topic/messages")  // Ответ отправляется всем подписчикам /topic/messages
    public String handleMessage(String message) {
        return "Ответ сервера: " + message;
    }
}