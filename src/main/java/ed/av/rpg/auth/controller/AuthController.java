package ed.av.rpg.auth.controller;

import ed.av.rpg.auth.model.dto.request.LogInDtoRequest;
import ed.av.rpg.auth.model.dto.request.RegisterDtoRequest;
import ed.av.rpg.auth.service.server.AuthServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import static ed.av.rpg.util.StringConstants.Headers.SESSION_ID_HEADER_KEY;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthServerService authServerService;

    @MessageMapping("/login")
    public void logIn(@Header(SESSION_ID_HEADER_KEY) String sessionId, @Payload LogInDtoRequest logInDtoRequest) {
        authServerService.logInUser(sessionId, logInDtoRequest.getUsername(), logInDtoRequest.getPassword());
    }

    @MessageMapping("/register")
    public void register(@Header(SESSION_ID_HEADER_KEY) String sessionId, @Payload RegisterDtoRequest registerDtoRequest) {
        authServerService.registerNewUser(sessionId, registerDtoRequest.getLogin(), registerDtoRequest.getPassword());
    }
}
