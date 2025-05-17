package ed.av.rpg;

import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.form.login.LogInManager;
import ed.av.rpg.form.register.RegisterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleMessageHandler {

    private final RegisterManager registerManager;
    private final LogInManager logInManager;

    @EventListener
    public void handleSimpleMessage(SimpleMessageDto dto) {
        registerManager.clearForm();
        registerManager.closeForm();
        System.out.println(dto.message());
        //logInManager.openForm();
    }
}
