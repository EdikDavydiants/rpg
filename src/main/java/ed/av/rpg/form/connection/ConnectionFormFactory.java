package ed.av.rpg.form.connection;

import ed.av.rpg.form.common.NamedField;
import javafx.application.Platform;
import javafx.scene.text.Font;

public class ConnectionFormFactory {

    public static final Font FONT =  Font.font("Arial", 30);
    public static final float NAME_FIELD_SIZE = 100f;

    private ConnectionFormFactory() {}

    public static ConnectionForm getConnectionForm(ConnectionManager connectionManager) {

        NamedField serverUrlField = NamedField.getVertFieldWithListener(
                "Server URL: ",
                NAME_FIELD_SIZE,
                FONT,
                (textField, oldText, newText) ->  {
                    String httpProtocol = "https:";
                    String webSocketProtocol = "wss:";
                    if(newText.startsWith(httpProtocol)) {
                        String serverUrl = newText.replaceFirst(httpProtocol, webSocketProtocol);
                        Platform.runLater(() -> textField.setText(serverUrl + "ws"));
                    }
                });

        var connectionForm = new ConnectionForm(connectionManager, serverUrlField);
        connectionForm.preInitAddAll(
                serverUrlField.getContainer(),
                new ConnectionButton(connectionForm));
        return connectionForm;
    }
}
