package ed.av.rpg.form.register;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class RegisterManager {

    private final RestTemplate restTemplate;
    private final ConnectionData connectionData;

    public void processData(RegisterDto registerDto) {

    }
}
