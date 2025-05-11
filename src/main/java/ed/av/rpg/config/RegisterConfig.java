package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.register.RegisterForm;
import ed.av.rpg.form.register.RegisterManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static ed.av.rpg.form.register.RegisterFormFactory.getRegisterForm;

//@Configuration
public class RegisterConfig {

    @Bean
    RegisterForm registerForm(RegisterManager registerManager) {

        return getRegisterForm(registerManager);
    }

    @Bean
    RegisterManager registerManager(RestTemplate restTemplate, ConnectionData connectionData) {

        return new RegisterManager(restTemplate, connectionData);
    }
}
