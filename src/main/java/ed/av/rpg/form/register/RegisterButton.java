package ed.av.rpg.form.register;

import ed.av.rpg.form.common.lazycomponents.controls.LButton;
import javafx.scene.control.Button;

public class RegisterButton extends LButton {

    public RegisterButton(RegisterForm registerForm) {
        super(() -> {
            Button b = new Button("Register");
            b.setFont(RegisterFormFactory.FONT);
            b.setOnAction(event -> registerForm.onClick());
            return b;
        });


    }
}