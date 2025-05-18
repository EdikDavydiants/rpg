package ed.av.rpg.auth.model.dto;

import ed.av.rpg.clientservice.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessageDto implements Topical{

    private String message;

    @Override
    public ClientService chooseClientService(ClassTypeExecutor classTypeExecutor) {
        return classTypeExecutor.getSimpleMessageService();
    }
}
