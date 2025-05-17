package ed.av.rpg.form.login;

import ed.av.rpg.form.common.NamedField;
import javafx.scene.text.Font;
import org.springframework.context.ApplicationEventPublisher;

public class LogInFormFactory {

    public static final Font FONT = Font.font("Arial", 20);
    public static final float NAME_FIELD_SIZE = 100f;

    private LogInFormFactory() {}

    public static LogInForm getLogInForm(ApplicationEventPublisher eventPublisher) {

        NamedField loginRaw = NamedField.getHorField("Login: ", NAME_FIELD_SIZE, FONT);
        NamedField passwordRaw = NamedField.getHorField("Password: ", NAME_FIELD_SIZE, FONT);

        var loginForm = new LogInForm(loginRaw, passwordRaw, eventPublisher);
        loginForm.preInitAddAll(
                loginRaw.getContainer(),
                passwordRaw.getContainer(),
                new LogInButton(loginForm));
        return loginForm;
    }
}
