package ed.av.rpg.form.login;

import ed.av.rpg.auth.model.dto.LogInDto;
import ed.av.rpg.form.common.NamedField;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogInForm extends LVBox {

    private final LogInManager logInManager;
    private final NamedField loginField;
    private final NamedField passwordField;
    private final NamedField masterIdField;

    public LogInDto getLogInData() {
        return new LogInDto(getLogin(), getPassword(), getMasterId());
    }

    public String getLogin() {
        return loginField.getValue();
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public String getMasterId() {
        return masterIdField.getValue();
    }

    public void onClick() {
        logInManager.processData(getLogInData());
    }
}
