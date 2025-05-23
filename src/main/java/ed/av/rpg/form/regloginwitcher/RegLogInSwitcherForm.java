package ed.av.rpg.form.regloginwitcher;

import ed.av.rpg.form.common.lazycomponents.containers.LPane;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class RegLogInSwitcherForm extends LPane {

    private final ApplicationEventPublisher eventPublisher;
    @Setter
    private RegSwitcherButton regSwitcherButton;
    @Setter
    private LogInSwitcherButton logInSwitcherButton;

    public void switchToLogIn() {
        eventPublisher.publishEvent(new SwitchToLogInEvent());
        showRegisterButton();
    }

    public void switchToRegister() {
        eventPublisher.publishEvent(new SwitchToRegisterEvent());
        showLogInButton();
    }

    public void showLogInButton() {
        regSwitcherButton.close();
        logInSwitcherButton.open();
    }

    public void showRegisterButton() {
        logInSwitcherButton.close();
        regSwitcherButton.open();
    }
}
