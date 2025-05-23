package ed.av.rpg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompSession;

public class MainSession {

    @Setter
    @Getter
    private StompSession session;
    private StompSession.Subscription personalSubscription;
    @Setter
    private StompFrameHandler handler;

    public void switchPersonalSubscription(String username) {
        if (personalSubscription != null) {
            personalSubscription.unsubscribe();
        }
        personalSubscription = session.subscribe("/topic/personal/" + username, handler);
    }

    public boolean compareSessionId(String sessionId) {
        return session.getSessionId().equals(sessionId);
    }
}
