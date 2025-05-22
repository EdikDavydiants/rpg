package ed.av.rpg.auth.service.client;

import ed.av.rpg.Logger;
import ed.av.rpg.auth.connection.AuthenticationData;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.auth.model.dto.response.LogInDtoResponse;
import ed.av.rpg.config.MainSession;
import ed.av.rpg.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInClientService extends ClientService {

    private final AuthenticationData authData;
    private final MainSession session;

    @Override
    public void processTopicalDto(Topical topical) {
        var logInDtoResponse = castToChildDto(topical);
        authData.setUsername(logInDtoResponse.getUsername());
        authData.setUsername(logInDtoResponse.getUserId());
        session.switchPersonalSubscription(logInDtoResponse.getUsername());
        Logger.log(logInDtoResponse.getMessage());
    }

    @Override
    public LogInDtoResponse castToChildDto(Topical topical) {
        return (LogInDtoResponse) topical;
    }
}
