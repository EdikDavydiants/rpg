package ed.av.rpg.config;

import ed.av.rpg.form.chat.Chat;
import org.springframework.context.annotation.Bean;

import static ed.av.rpg.enums.MessageType.BOT_MESSAGE;
import static ed.av.rpg.enums.MessageType.MY_MESSAGE;
import static ed.av.rpg.enums.MessageType.PLAYER_MESSAGE;

//@Configuration
public class ChatConfig {

    @Bean
    public Chat chat() {
        var chat = new Chat();
        chat.addMessage(BOT_MESSAGE, "Welcome to the chat!");
        chat.addMessage(MY_MESSAGE, "Hi!");
        chat.addMessage(PLAYER_MESSAGE, "Good day!");
        chat.addMessage(MY_MESSAGE, "Let's start?");
        chat.addMessage(PLAYER_MESSAGE, "Yes!");
        chat.addMessage(BOT_MESSAGE, "Game has been started!");
        chat.addMessage(PLAYER_MESSAGE, "Let's go!");
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
