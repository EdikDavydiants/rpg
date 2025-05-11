package ed.av.rpg.form.login;

import lombok.RequiredArgsConstructor;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.LogInDto;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class LogInManager {

    private final RestTemplate restTemplate;
    private final ConnectionData connectionData;

    public void processData(LogInDto logInData) {

    }

    private String findOutMyIP() {

        return restTemplate.getForObject("https://api.ipify.org?format=text", String.class);
    }
}
