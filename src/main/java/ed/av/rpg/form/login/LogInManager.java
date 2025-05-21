package ed.av.rpg.form.login;

import ed.av.rpg.config.MainSession;
import ed.av.rpg.util.HeadersBuilder;
import lombok.RequiredArgsConstructor;
import ed.av.rpg.auth.model.dto.request.LogInDtoRequest;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaders;

@RequiredArgsConstructor
public class LogInManager {

    private final LogInForm form;
    private final MainSession session;

    @EventListener
    public void processData(LogInEventDto logInEventDto) {
        LogInDtoRequest logInDtoRequest = LogInDtoRequest.builder()
                .login(logInEventDto.login())
                .password(logInEventDto.password())
                .build();
        StompHeaders headers = HeadersBuilder.builder()
                .destination("/app/login")
                .addHeader("session-id", session.getSession().getSessionId())
                .build();
        session.getSession().send(headers, logInDtoRequest);
    }

    public void openForm() {
        form.getNode().setVisible(true);
    }

    public void closeForm() {
        form.getNode().setVisible(false);
    }
}
