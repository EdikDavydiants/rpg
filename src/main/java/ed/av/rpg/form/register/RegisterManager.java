package ed.av.rpg.form.register;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class RegisterManager {

    private final ConnectionData connectionData;
    private final AuthService authService;

    public void processData(RegisterDto registerDto) {
        authService.registerNewUser(registerDto.login(), registerDto.password());
    }
}
