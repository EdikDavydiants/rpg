package ed.av.rpg.form.connection;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.auth.model.dto.ConnectionDto;
import ed.av.rpg.config.ChatStompSessionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@RequiredArgsConstructor
public class ConnectionManager {

    private final ConnectionData connectionData;
    private final ChatStompSessionHandler handler;

    public void processData(ConnectionDto connectionDto) {
        var serverUrl = connectionDto.serverUrl();
        connectionData.setServerUrl(serverUrl);

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompClient.connectAsync(serverUrl, handler);
    }
}
