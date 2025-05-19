package ed.av.rpg.config;

import ed.av.rpg.form.login.LogInForm;
import ed.av.rpg.form.login.LogInFormFactory;
import ed.av.rpg.form.login.LogInManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogInConfig {

    @Bean
    LogInForm logInForm(ApplicationEventPublisher eventPublisher) {
        return LogInFormFactory.getLogInForm(eventPublisher);
    }

    @Bean
    LogInManager logInManager(LogInForm logInForm, MainSession session) {
        return new LogInManager(logInForm, session);
    }
}
