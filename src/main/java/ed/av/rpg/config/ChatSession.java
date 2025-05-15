package ed.av.rpg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.simp.stomp.StompSession;

public class ChatSession {

    @Setter
    @Getter
    private StompSession session;
}
