package ed.av.rpg.auth.model.dto.response;

import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.service.client.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInDtoResponse implements Topical {

    private String message;
    private String userId;
    private String username;

    @Override
    public ClientService chooseClientService(ClassTypeExecutor classTypeExecutor) {
        return classTypeExecutor.getLogInClientService();
    }
}
