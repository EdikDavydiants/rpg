package ed.av.rpg.form.connection;

import ed.av.rpg.form.common.lazycomponents.controls.LButton;
import javafx.scene.control.Button;

public class ConnectionButton extends LButton {

    public ConnectionButton(ConnectionForm connectionForm) {
        super(() -> {
            var button = new Button("Connect");
            button.setFont(ConnectionFormFactory.FONT);
            button.setOnAction(event -> connectionForm.onClick());
            return button;
        });
    }
}
