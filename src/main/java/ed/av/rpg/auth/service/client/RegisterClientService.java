package ed.av.rpg.auth.service.client;

import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.auth.model.dto.response.RegisterDtoResponse;
import ed.av.rpg.form.login.LogInManager;
import ed.av.rpg.form.register.RegisterManager;
import ed.av.rpg.service.client.ClientService;
import ed.av.rpg.util.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterClientService extends ClientService {

    private final RegisterManager registerManager;
    private final LogInManager logInManager;

    @Override
    public void processTopicalDto(Topical topical) {
        var registerDtoResponse = castToChildDto(topical);
        if (registerDtoResponse.isSuccess()) {
            registerManager.closeForm();
            logInManager.openForm();
        }
        Logger.log(registerDtoResponse.getMessage());
    }

    @Override
    public RegisterDtoResponse castToChildDto(Topical topical) {
        return (RegisterDtoResponse) topical;
    }
}
