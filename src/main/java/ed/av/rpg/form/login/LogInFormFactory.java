package ed.av.rpg.form.login;

import ed.av.rpg.form.common.NamedField;
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
        loginForm.preInitAddAll(
                loginRaw.getContainer(),
                passwordRaw.getContainer(),
                masterIdRaw.getContainer(),
                new LogInButton(loginForm));
        return loginForm;
    }
}
