package ed.av.rpg.form.connection;

import ed.av.rpg.form.common.NamedField;
import ed.av.rpg.form.common.lazycomponents.containers.LVBox;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ConnectionForm extends LVBox {

    private final ApplicationEventPublisher eventPublisher;
    private final NamedField serverUrlField;

    public ConnectionEventDto getConnectionEventDto() {
        return new ConnectionEventDto(getServerUrl());
    }

    public String getServerUrl() {
        return serverUrlField.getValue();
    }

    public void onClick() {
        eventPublisher.publishEvent(getConnectionEventDto());
    }
}
