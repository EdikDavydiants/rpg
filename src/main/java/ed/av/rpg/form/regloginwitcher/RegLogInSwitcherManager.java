package ed.av.rpg.form.regloginwitcher;

import ed.av.rpg.form.login.LogInForm;
import ed.av.rpg.form.register.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class RegLogInSwitcherManager {

    private final RegLogInSwitcherForm form;
    private final RegisterForm registerForm;
    private final LogInForm logInForm;

    @EventListener
    public void switchToRegister(SwitchToRegisterEvent event) {
        logInForm.close();
        registerForm.open();
        form.showLogInButton();
    }

    @EventListener
    public void switchToLogIn(SwitchToLogInEvent event) {
        registerForm.close();
        logInForm.open();
        form.showRegisterButton();
    }

    public void openForm() {
        form.open();
    }
}
