package ed.av.rpg.form.chat;

import ed.av.rpg.config.MainSession;
import ed.av.rpg.event.ChatMessageEvent;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import ed.av.rpg.form.common.lazycomponents.controls.LScrollPane;
import ed.av.rpg.form.common.lazycomponents.controls.LTextArea;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ed.av.rpg.GeneralUtils;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.enums.MessageType;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.Arrays;

import static ed.av.rpg.enums.MessageType.PLAYER_MESSAGE;

public class Chat extends LVBox {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 300;
    private final LVBox messagesArea;
    private final LTextArea textArea;
    private final LScrollPane scrollPane;

    private final ConnectionData connection;
    private final MainSession mainSession;

    public Chat(ConnectionData connectionData, MainSession mainSession) {
        super(() -> {
            var vb = new VBox();
            vb.setPrefWidth(WIDTH + 30.);
            vb.setPrefHeight(HEIGHT + 50.);
            return vb;
        });

        this.connection = connectionData;
        this.mainSession = mainSession;

        messagesArea = new LVBox(() -> {
            var messagesArea = new VBox();
            messagesArea.setSpacing(10.);
            return messagesArea;
        });

        scrollPane = new LScrollPane(() -> {
            var sp = new ScrollPane();
            sp.setFitToWidth(true);
            sp.setMaxHeight(HEIGHT);
            sp.setPrefViewportHeight(HEIGHT);
            sp.setPrefViewportWidth(WIDTH);
            return sp;
        });
        scrollPane.setContent(messagesArea);

        textArea = new LTextArea(() -> {
            final var textArea = new TextArea();
            textArea.setFont(new Font(15));
            textArea.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER) {
                    byte[] bytes = textArea.getText().getBytes();
                    byte[] newByteArray = Arrays.copyOf(bytes, bytes.length - 1);
                    String text = new String(newByteArray);
                    sendMessage(text);
                    textArea.setText("");
                    new Thread(() -> {
                        GeneralUtils.sleep(50);
                        scrollPane.setVValue(1.0);
                    }).start();
                }
            });
            textArea.setPrefWidth(WIDTH);
            textArea.setPrefHeight(50.);
            return textArea;
        });

        preInitAddAll(scrollPane, textArea);
    }

    @EventListener
    public void handleChatMessage(ChatMessageEvent event) {
        addMessage(PLAYER_MESSAGE, event.text());
    }

    public void addMessage(MessageType messageType, String text) {
        var message = new ChatMessage();
        message.addMessage(messageType, text);
        Platform.runLater(() -> messagesArea.postInitAddAll(message));
    }

    private void sendMessage(String text) {
        StompSession session = mainSession.getSession();
        if(session != null) {
            session.send("/app/chat", new ChatMessageEvent(text));
        }
    }
}
