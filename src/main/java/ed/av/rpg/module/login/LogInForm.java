package ed.av.rpg.module.login;

import ed.av.rpg.auth.model.dto.LogInDto;
import ed.av.rpg.module.common.NamedField;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogInForm extends VBox {

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
