package ed.av.rpg.form.connection;

import javafx.scene.control.Button;

public class ConnectionButton extends Button {

    public ConnectionButton(ConnectionForm connectionForm) {
        super("Connect");

        setFont(ConnectionFormFactory.FONT);
        setOnAction(event -> connectionForm.onClick());
    }
}
