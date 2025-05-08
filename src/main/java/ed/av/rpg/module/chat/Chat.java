package ed.av.rpg.module.chat;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ed.av.rpg.GeneralUtils;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import static java.util.stream.Stream.of;
import static ed.av.rpg.enums.MessageType.MY_MESSAGE;

public class Chat extends VBox {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 300;
    private final VBox messagesArea;
    private final TextArea textArea;
    private final ScrollPane scrollPane;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConnectionData connectionData;

    public Chat() {

        setPrefWidth(WIDTH + 30.);
        setPrefHeight(HEIGHT + 50.);

        scrollPane = new ScrollPane();
        messagesArea = createMessageArea();
        textArea = createTextArea();
    }

    private VBox createMessageArea() {

        var messagesArea = new VBox();
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(HEIGHT);
        scrollPane.setPrefViewportHeight(HEIGHT);
        scrollPane.setPrefViewportWidth(WIDTH);
        scrollPane.setContent(messagesArea);
        messagesArea.setSpacing(10.);
        getChildren().add(scrollPane);
        return messagesArea;
    }

    private TextArea createTextArea() {
        final var textArea = new TextArea();
        textArea.setFont(new Font(15));
        textArea.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                byte[] bytes = textArea.getText().getBytes();
                byte[] newByteArray = Arrays.copyOf(bytes, bytes.length - 1);
                String text = new String(newByteArray);
                addMessage(MY_MESSAGE, text);
                sendMessage(text);
                textArea.setText("");
                new Thread(() -> {
                    GeneralUtils.sleep(50);
                    scrollPane.setVvalue(1.0);
                }).start();
            }
        });
        textArea.setPrefWidth(WIDTH);
        textArea.setPrefHeight(50.);
        getChildren().add(textArea);
        return textArea;
    }

    public void addMessage(MessageType messageType, String text) {
        var message = new Message();
        message.addMessage(messageType, text);
        Platform.runLater(() -> messagesArea.getChildren().add(message));
    }

    private void sendMessage(String text) {

        String uri = UriComponentsBuilder
                .fromUriString(getMasterUrl() + "/chat/messages")
                .encode()
                .toUriString();
        restTemplate.postForLocation(uri, text);
    }

    private String getMasterUrl() {
        //return "http://" + connectionData.getMasterIP() + ":8080";
        return "https://lawlessly-infinite-sylph.cloudpub.ru/";
    }


}
