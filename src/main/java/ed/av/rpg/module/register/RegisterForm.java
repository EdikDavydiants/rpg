package ed.av.rpg.module.register;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.module.common.NamedField;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterForm extends VBox {

    private final RegisterManager registerManager;
    private final NamedField loginField;
    private final NamedField passwordField;

    public RegisterDto getRegisterData() {
        return new RegisterDto(getLogin(), getPassword());
    }

    public String getLogin() {
        return loginField.getValue();
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public void onClick() {
        registerManager.processData(getRegisterData());
    }
}
