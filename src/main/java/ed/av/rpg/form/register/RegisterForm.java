package ed.av.rpg.form.register;

import ed.av.rpg.form.common.NamedField;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class RegisterForm extends LVBox {

    private final NamedField loginField;
    private final NamedField passwordField;
    private final ApplicationEventPublisher eventPublisher;

    public RegisterEventDto getRegisterData() {
        return new RegisterEventDto(getLogin(), getPassword());
    }

    public String getLogin() {
        return loginField.getValue();
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public void onClick() {
        eventPublisher.publishEvent(getRegisterData());
    }

    public void clearFields() {
        loginField.clearField();
        passwordField.clearField();
    }
}
