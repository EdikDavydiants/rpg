package ed.av.rpg.controller;

import ed.av.rpg.module.chat.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static ed.av.rpg.enums.MessageType.PLAYER_MESSAGE;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final Chat chat;

    @PostMapping("/messages")
    public void getMessage(@RequestBody String text) {

        chat.addMessage(PLAYER_MESSAGE, text);
    }
}
