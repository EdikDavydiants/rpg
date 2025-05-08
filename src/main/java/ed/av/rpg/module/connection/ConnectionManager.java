package ed.av.rpg.module.connection;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.ConnectionDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectionManager {

    private final ConnectionData connectionData;

    public void processData(ConnectionDto connectionDto) {

        connectionData.setServerUrl(connectionDto.serverUrl());
    }
}
