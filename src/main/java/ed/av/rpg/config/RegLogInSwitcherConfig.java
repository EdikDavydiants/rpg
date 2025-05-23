package ed.av.rpg.config;

import ed.av.rpg.form.login.LogInForm;
import ed.av.rpg.form.register.RegisterForm;
import ed.av.rpg.form.regloginwitcher.RegLogInSwitcherFactory;
import ed.av.rpg.form.regloginwitcher.RegLogInSwitcherForm;
import ed.av.rpg.form.regloginwitcher.RegLogInSwitcherManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegLogInSwitcherConfig {

    @Bean
    RegLogInSwitcherForm regLogInSwitcherForm(ApplicationEventPublisher publisher) {
        return RegLogInSwitcherFactory.createRegLogInSwitcherForm(publisher);
    }

    @Bean
    RegLogInSwitcherManager regLogInSwitcherManager(RegLogInSwitcherForm regLogInSwitcherForm,
                                                    RegisterForm registerForm, LogInForm logInForm) {
        return new RegLogInSwitcherManager(regLogInSwitcherForm, registerForm, logInForm);
    }
}
