package ed.av.rpg.form.login;

import ed.av.rpg.form.common.NamedField;
import javafx.scene.text.Font;
import org.springframework.context.ApplicationEventPublisher;

public class LogInFormFactory {

    public static final Font FONT = Font.font("Arial", 20);
    public static final float NAME_FIELD_SIZE = 100f;

    private LogInFormFactory() {}

    public static LogInForm getLogInForm(ApplicationEventPublisher eventPublisher) {

        NamedField usernameRaw = NamedField.getHorField("Username: ", NAME_FIELD_SIZE, FONT);
        NamedField passwordRaw = NamedField.getHorField("Password: ", NAME_FIELD_SIZE, FONT);

        var logInForm = new LogInForm(usernameRaw, passwordRaw, eventPublisher);
        logInForm.preInitAddAll(
                usernameRaw.getContainer(),
                passwordRaw.getContainer(),
                new LogInButton(logInForm));
        return logInForm;
    }
}
