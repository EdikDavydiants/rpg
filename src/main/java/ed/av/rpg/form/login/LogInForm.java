package ed.av.rpg.form.login;

import ed.av.rpg.form.common.NamedField;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class LogInForm extends LVBox {

    private final NamedField usernameField;
    private final NamedField passwordField;
    private final ApplicationEventPublisher eventPublisher;

    public LogInEventDto getLogInData() {
        return new LogInEventDto(getUsername(), getPassword());
    }

    public String getUsername() {
        return usernameField.getValue();
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public void onClick() {
        eventPublisher.publishEvent(getLogInData());
    }
}
