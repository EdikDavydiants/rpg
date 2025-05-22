package ed.av.rpg.form.connection;

import ed.av.rpg.Logger;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.config.MainStompSessionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ConnectionManager {

    private final ConnectionForm form;
    private final ConnectionData connectionData;
    private final MainStompSessionHandler handler;

    @EventListener
    public void processData(ConnectionEventDto connectionEventDto) {
        var serverUrl = connectionEventDto.serverUrl();
        connectionData.setServerUrl(serverUrl);

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        CompletableFuture<StompSession> futureSession = stompClient.connectAsync(serverUrl, handler);

        futureSession.thenAccept(session -> {
            form.getNode().setVisible(false);
        }).exceptionally(ex -> {
            Logger.log("Не удалось соединиться с сервером!");
            return null;
        });
    }
}
