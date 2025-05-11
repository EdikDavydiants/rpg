package ed.av.rpg.form.register;

import javafx.scene.control.Button;

public class RegisterButton extends Button {

    public RegisterButton(RegisterForm registerForm) {
        super("Register");

        setFont(RegisterFormFactory.FONT);
        setOnAction(event -> registerForm.onClick());
    }
}