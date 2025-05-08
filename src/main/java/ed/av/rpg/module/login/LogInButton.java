package ed.av.rpg.module.login;

import javafx.scene.control.Button;

public class LogInButton extends Button {

    public LogInButton(LogInForm logInForm) {
        super("Log In");

        setFont(LogInFormFactory.FONT);
        setOnAction(event -> logInForm.onClick());
    }
}