package ed.av.rpg.form.register;

import ed.av.rpg.util.Logger;
import ed.av.rpg.auth.model.dto.request.RegisterDtoRequest;
import ed.av.rpg.config.MainSession;
import ed.av.rpg.util.HeadersBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

import static ed.av.rpg.util.StringConstants.Headers.SESSION_ID_HEADER_KEY;

@RequiredArgsConstructor
public class RegisterManager {

    private final MainSession mainSession;
    private final RegisterForm form;
    private final Logger logger;

    @EventListener
    public void processData(RegisterEventDto registerEventDto) {
        StompSession session = mainSession.getSession();
        if (!registerEventDto.login().matches("[a-zA-Z_0-9]*")) {
            logger.log("Логин должен соответствовать паттерну 'a-z, A-Z, 0-9, _'");
        } else if (registerEventDto.login().length() < 2) {
            logger.log("Логин должен содержать хотя бы 2 символа");
        } else if (registerEventDto.login().length() > 16) {
            logger.log("Логин должен содержать менее 17 символов");
        } else if (!registerEventDto.password().matches("[a-zA-Z_0-9]*")) {
            logger.log("Пароль должен соответствовать паттерну 'a-z, A-Z, 0-9, _'");
        } else if (registerEventDto.password().length() < 5) {
            logger.log("Пароль должен содержать хотя бы 5 символов");
        } else if (registerEventDto.password().length() > 20) {
            logger.log("Пароль должен содержать менее 21 символа");
        } else {
            var registerDto = new RegisterDtoRequest(
                    session.getSessionId(),
                    registerEventDto.login(),
                    registerEventDto.password());
            StompHeaders headers = HeadersBuilder.builder()
                    .destination("/app/register")
                    .addHeader(SESSION_ID_HEADER_KEY, session.getSessionId())
                    .build();
            session.send(headers, registerDto);
        }
    }

    public void clearForm() {
        form.clearFields();
    }

    public void openForm() {
        form.open();
    }

    public void closeForm() {
        form.close();
    }
}
