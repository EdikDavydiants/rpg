package ed.av.rpg.auth.model.dto.request;

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
