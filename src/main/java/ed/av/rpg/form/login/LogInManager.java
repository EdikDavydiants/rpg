package ed.av.rpg.form.login;

import ed.av.rpg.config.MainSession;
import ed.av.rpg.util.HeadersBuilder;
import lombok.RequiredArgsConstructor;
import ed.av.rpg.auth.model.dto.request.LogInDtoRequest;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaders;

import static ed.av.rpg.util.StringConstants.Headers.SESSION_ID_HEADER_KEY;

@RequiredArgsConstructor
public class LogInManager {

    private final LogInForm logInForm;
    private final MainSession session;

    @EventListener
    public void processData(LogInEventDto logInEventDto) {
        LogInDtoRequest logInDtoRequest = LogInDtoRequest.builder()
                .username(logInEventDto.username())
                .password(logInEventDto.password())
                .build();
        StompHeaders headers = HeadersBuilder.builder()
                .destination("/app/login")
                .addHeader(SESSION_ID_HEADER_KEY, session.getSession().getSessionId())
                .build();
        session.getSession().send(headers, logInDtoRequest);
    }

    public void openForm() {
        logInForm.open();
    }

    public void closeForm() {
        logInForm.close();
    }
}
