package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.login.LogInForm;
import ed.av.rpg.form.login.LogInFormFactory;
import ed.av.rpg.form.login.LogInManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LogInConfig {

    @Bean
    LogInForm logInForm(LogInManager logInManager) {

        return LogInFormFactory.getLogInForm(logInManager);
    }

    @Bean
    LogInManager logInManager(ConnectionData connectionData) {

        return new LogInManager(new RestTemplate(), connectionData);
    }
}
