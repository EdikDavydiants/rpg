package ed.av.rpg.auth.model.dto.request;

import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.service.client.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto implements Topical {

    private String login;
    private String password;

    @Override
    public ClientService chooseClientService(ClassTypeExecutor classTypeExecutor) {
        return null;
    }
}
