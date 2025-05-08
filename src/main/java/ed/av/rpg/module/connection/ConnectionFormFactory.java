package ed.av.rpg.module.connection;

import ed.av.rpg.module.common.NamedField;
import javafx.scene.text.Font;

public class ConnectionFormFactory {

    public static final Font FONT =  Font.font("Arial", 30);
    public static final float NAME_FIELD_SIZE = 100f;

    private ConnectionFormFactory() {}

    public static ConnectionForm getEnterForm(ConnectionManager connectionManager) {

        NamedField serverUrlField = NamedField.getHorField("Server URL: ", NAME_FIELD_SIZE, FONT);

        var enterForm = new ConnectionForm(connectionManager, serverUrlField);
        enterForm.getChildren().addAll(
                serverUrlField.getPane(),
                new ConnectionButton(enterForm));
        return enterForm;
    }
}
