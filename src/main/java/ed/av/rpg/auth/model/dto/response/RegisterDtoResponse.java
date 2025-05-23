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
public class RegisterDtoResponse implements Topical {

    private boolean isSuccess;
    private String message;

    @Override
    public ClientService chooseClientService(ClassTypeExecutor classTypeExecutor) {
        return classTypeExecutor.getRegisterClientService();
    }
}
