package ed.av.rpg.module.chat;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ed.av.rpg.enums.MessageType;

import static javafx.scene.paint.Color.LIGHTBLUE;
import static javafx.scene.paint.Color.LIGHTGREEN;
import static javafx.scene.paint.Color.LIGHTSKYBLUE;

public class Message extends AnchorPane {
    public Message() {

        setPrefWidth(Chat.WIDTH);
        setPrefHeight(100);
    }

    public void addMessage(MessageType messageType, String message) {
        var text = new Text(message);
        text.setFont(new Font(15));
        var background = new Rectangle(0.7 * Chat.WIDTH, 100.);
        var stackPane = new StackPane(background, text);

        switch (messageType) {
            case MY_MESSAGE -> {
                background.setFill(LIGHTSKYBLUE);
                setRightAnchor(stackPane, 10.);
            }
            case PLAYER_MESSAGE -> {
                background.setFill(LIGHTBLUE);
                setLeftAnchor(stackPane, 10.);
            }
            case BOT_MESSAGE -> {
                background.setWidth(Chat.WIDTH - 10.);
                background.setFill(LIGHTGREEN);
                setLeftAnchor(stackPane, 10.);
                setRightAnchor(stackPane, 10.);
            }
        }
        getChildren().add(stackPane);
    }
}
