package ed.av.rpg.form.regloginwitcher;

import ed.av.rpg.form.common.lazycomponents.controls.LButton;
import javafx.scene.control.Button;

public class RegSwitcherButton extends LButton {

    public RegSwitcherButton(RegLogInSwitcherForm form) {
        super(() -> {
            var b = new Button();
            b.setText("Registration");
            b.setOnAction(event -> {
                form.switchToRegister();
            });
            return b;
        });
    }
}
