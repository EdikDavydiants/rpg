package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.chat.Chat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.CompletableFuture;

import static ed.av.rpg.enums.MessageType.BOT_MESSAGE;
import static ed.av.rpg.enums.MessageType.MY_MESSAGE;
import static ed.av.rpg.enums.MessageType.PLAYER_MESSAGE;

@Configuration
public class ChatConfig {

    @Bean
    public Chat chat(ConnectionData connectionData, ChatSession session) {
        var chat = new Chat(connectionData, session);
        //chat.addMessage(BOT_MESSAGE, "Welcome to the chat!");
        return chat;
    }

//    @Bean
//    private DiceTable diceTable() {
//        return new DiceTable(600, 600)
//                .createDices()
//                .createAndSetStackArea(600, 600)
//                .createAndSetThrowArea(600, 600)
//                .setDices();
//    }
}
