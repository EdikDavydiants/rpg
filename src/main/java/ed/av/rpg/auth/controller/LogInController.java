package ed.av.rpg.auth.controller;

import ed.av.rpg.auth.model.dto.request.LogInDto;
import ed.av.rpg.service.server.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LogInController {

    private final AuthService authService;

    @MessageMapping("/login")
    public void logIn(@Header("session-id") String sessionId, @Payload LogInDto logInDtoRequest) {
        authService.logInUser(sessionId, logInDtoRequest.getLogin(), logInDtoRequest.getPassword());
    }
}