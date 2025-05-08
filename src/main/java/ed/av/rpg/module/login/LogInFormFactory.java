package ed.av.rpg.module.login;

import ed.av.rpg.module.common.NamedField;
import javafx.scene.text.Font;

public class LogInFormFactory {

    public static final Font FONT = Font.font("Arial", 20);
    public static final float NAME_FIELD_SIZE = 100f;

    private LogInFormFactory() {}

    public static LogInForm getLogInForm(LogInManager logInManager) {

        NamedField loginRaw = NamedField.getHorField("Login: ", NAME_FIELD_SIZE, FONT);
        NamedField passwordRaw = NamedField.getHorField("Password: ", NAME_FIELD_SIZE, FONT);
        NamedField masterIdRaw = NamedField.getHorField("Master ID: ", NAME_FIELD_SIZE, FONT);

        var loginForm = new LogInForm(logInManager, loginRaw, passwordRaw, masterIdRaw);
        loginForm.getChildren().addAll(
                loginRaw.getPane(),
                passwordRaw.getPane(),
                masterIdRaw.getPane(),
                new LogInButton(loginForm));
        return loginForm;
    }
}
