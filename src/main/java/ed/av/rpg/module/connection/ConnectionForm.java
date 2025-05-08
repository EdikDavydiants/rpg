package ed.av.rpg.module.connection;

import ed.av.rpg.auth.model.dto.ConnectionDto;
import ed.av.rpg.module.common.NamedField;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectionForm extends VBox {

    private final ConnectionManager connectionManager;
    private final NamedField serverUrlField;

    public ConnectionDto getConnectionDto() {
        return new ConnectionDto(getServerUrl());
    }

    public String getServerUrl() {
        return serverUrlField.getValue();
    }

    public void onClick() {
        connectionManager.processData(getConnectionDto());
    }
}
