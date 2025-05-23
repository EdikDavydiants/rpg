package ed.av.rpg.auth.model.dto;

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
public class SimpleMessageDto implements Topical{

    private String message;

    @Override
    public ClientService chooseClientService(ClassTypeExecutor classTypeExecutor) {
        return classTypeExecutor.getSimpleMessageService();
    }
}
