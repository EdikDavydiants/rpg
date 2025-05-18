package ed.av.rpg.form.register;

import ed.av.rpg.form.common.NamedField;
import javafx.scene.text.Font;
import org.springframework.context.ApplicationEventPublisher;

public class RegisterFormFactory {

    public static final Font FONT =  Font.font("Arial", 20);
    public static final float NAME_FIELD_SIZE = 100f;

    private RegisterFormFactory() {}

    public static RegisterForm getRegisterForm(ApplicationEventPublisher eventPublisher) {

        NamedField loginRaw = NamedField.getHorField("Login: ", NAME_FIELD_SIZE, FONT);
        NamedField passwordRaw = NamedField.getHorField("Password: ", NAME_FIELD_SIZE, FONT);

        var registerForm = new RegisterForm(loginRaw, passwordRaw, eventPublisher);
        registerForm.preInitAddAll(
                loginRaw.getContainer(),
                passwordRaw.getContainer(),
                new RegisterButton(registerForm));
        return registerForm;
    }
}
