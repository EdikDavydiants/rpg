package ed.av.rpg.form.regloginwitcher;

import ed.av.rpg.form.common.lazycomponents.controls.LButton;
import javafx.scene.control.Button;

public class LogInSwitcherButton extends LButton {

    public LogInSwitcherButton(RegLogInSwitcherForm form) {
        super(() -> {
            var b = new Button();
            b.setText("Log In");
            b.setOnAction(event -> {
                form.switchToLogIn();
            });
            return b;
        });
    }
}