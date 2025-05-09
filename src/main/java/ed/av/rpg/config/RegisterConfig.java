package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.module.register.RegisterFormFactory;
import ed.av.rpg.module.register.RegisterForm;
import ed.av.rpg.module.register.RegisterManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static ed.av.rpg.module.register.RegisterFormFactory.getRegisterForm;

@Configuration
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
