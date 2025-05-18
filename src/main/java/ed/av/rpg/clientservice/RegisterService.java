package ed.av.rpg.clientservice;

import ed.av.rpg.auth.model.dto.RegisterDto;
import ed.av.rpg.auth.model.dto.Topical;
import org.springframework.stereotype.Service;

@Service
public class RegisterService extends ClientService{

    @Override
    public void processTopicalDto(Topical topical) {
        RegisterDto registerDto = castToChildDto(topical);
    }

    @Override
    public RegisterDto castToChildDto(Topical topical) {
        return null;
    }
}
