package ed.av.rpg.form;

import ed.av.rpg.form.connection.ConnectionManager;
import ed.av.rpg.form.login.LogInManager;
import ed.av.rpg.form.register.RegisterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UIManager {

    private final ConnectionManager connectionManager;
    private final LogInManager logInManager;
    private final RegisterManager registerManager;
}
