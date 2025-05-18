package ed.av.rpg.auth.controller;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/register")
    public void register(RegisterDto registerDto) {
        boolean isSuccess = authService.registerNewUser(registerDto.getLogin(), registerDto.getPassword());
        String message;
        if (isSuccess) {
            message = "Вы зарегистрированы.";
        } else {
            message = "Ошибка регистрации: Логин занят.";
        }
        Map<String, Object> headers = new HashMap<>();
        headers.put("simple-class-name", SimpleMessageDto.class.getSimpleName());
        messagingTemplate.convertAndSend("/topic/common", new SimpleMessageDto(message), headers);
    }
}
