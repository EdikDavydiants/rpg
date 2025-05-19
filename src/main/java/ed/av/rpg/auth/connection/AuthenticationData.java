package ed.av.rpg.auth.connection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationData {

    private String userId;
    private String username;
    private UserRole role;

    public boolean compareUserId(String userId) {
        return userId.equals(this.userId);
    }

    public boolean isUserIdNull() {
        return userId == null;
    }
}
