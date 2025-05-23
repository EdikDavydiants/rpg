package ed.av.rpg.config;

import ed.av.rpg.util.Logger;
import ed.av.rpg.form.register.RegisterForm;
import ed.av.rpg.form.register.RegisterManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ed.av.rpg.form.register.RegisterFormFactory.getRegisterForm;

@Configuration
public class RegisterConfig {

    @Bean
    RegisterForm registerForm(ApplicationEventPublisher eventPublisher) {
        return getRegisterForm(eventPublisher);
    }

    @Bean
    RegisterManager registerManager(MainSession mainSession, RegisterForm form, Logger logger) {
        return new RegisterManager(mainSession, form, logger);
    }
}
