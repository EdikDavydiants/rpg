package ed.av.rpg.module.connection;

import javafx.scene.control.Button;

public class ConnectionButton extends Button {

    public ConnectionButton(ConnectionForm connectionForm) {
        super("Connect");

        setFont(ConnectionFormFactory.FONT);
        setOnAction(event -> connectionForm.onClick());
    }
}
