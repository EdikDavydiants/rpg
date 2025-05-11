package ed.av.rpg.form.connection;

import ed.av.rpg.auth.model.dto.ConnectionDto;
import ed.av.rpg.form.common.NamedField;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectionForm extends LVBox {

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
