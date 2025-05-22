package ed.av.rpg.auth.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ed.av.rpg.auth.model.dto.Topical;
import ed.av.rpg.service.client.ClientService;
import ed.av.rpg.util.ClassTypeExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoRequest implements Serializable {

    private String sessionId;
    private String login;
    private String password;
}
