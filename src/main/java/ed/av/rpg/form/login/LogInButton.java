package ed.av.rpg.form.login;

import ed.av.rpg.form.common.lazycomponents.controls.LButton;
import javafx.scene.control.Button;

public class LogInButton extends LButton {

    public LogInButton(LogInForm logInForm) {
        super(() -> {
            var button = new Button("Log In");
            button.setFont(LogInFormFactory.FONT);
            button.setOnAction(event -> logInForm.onClick());
            return button;
        });
    }
}