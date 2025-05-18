package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.chat.Chat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public Chat chat(ConnectionData connectionData, MainSession session) {
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
