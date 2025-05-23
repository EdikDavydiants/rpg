package ed.av.rpg.form.connection;

import ed.av.rpg.form.regloginwitcher.RegLogInSwitcherManager;
import ed.av.rpg.form.regloginwitcher.SwitchToLogInEvent;
import ed.av.rpg.util.Logger;
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

import static ed.av.rpg.util.StringConstants.InfoMessages.CONNECTION_FAILED;
import static ed.av.rpg.util.StringConstants.InfoMessages.CONNECTION_SUCCESS;

@RequiredArgsConstructor
public class ConnectionManager {

    private final ConnectionForm connectionForm;
    private final ConnectionData connectionData;
    private final MainStompSessionHandler handler;
    private final RegLogInSwitcherManager regLogInSwitcherManager;

    @EventListener
    public void processData(ConnectionEventDto connectionEventDto) {
        var serverUrl = connectionEventDto.serverUrl();
        connectionData.setServerUrl(serverUrl);

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        CompletableFuture<StompSession> futureSession = stompClient.connectAsync(serverUrl, handler);

        futureSession.thenAccept(session -> {
            connectionForm.close();
            regLogInSwitcherManager.openForm();
            regLogInSwitcherManager.switchToLogIn(null);
            Logger.log(CONNECTION_SUCCESS);
        }).exceptionally(ex -> {
            Logger.log(CONNECTION_FAILED);
            return null;
        });
    }
}
