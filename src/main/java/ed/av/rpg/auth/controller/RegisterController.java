package ed.av.rpg.auth.controller;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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
        messagingTemplate.convertAndSend("/topic/common",
                new SimpleMessageDto(registerDto.getSessionId(), message));
    }
}
