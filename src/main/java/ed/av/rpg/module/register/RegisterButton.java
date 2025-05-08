package ed.av.rpg.module.register;

import ed.av.rpg.module.login.LogInForm;
import ed.av.rpg.module.login.LogInFormFactory;
import javafx.scene.control.Button;

public class RegisterButton extends Button {

    public RegisterButton(RegisterForm registerForm) {
        super("Register");

        setFont(RegisterFormFactory.FONT);
        setOnAction(event -> registerForm.onClick());
    }
}