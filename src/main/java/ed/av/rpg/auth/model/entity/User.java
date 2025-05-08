package ed.av.rpg.auth.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {

    // Идентификаторы
    private UUID id;
    private String username;
    private String email;

    // Безопасность
    private String passwordHash;
    private String salt;
    private boolean isActive;

    // Игровые данные
    private LocalDateTime lastLogin;

    // Настройки
    private boolean showTutorials = true;

    // Социальное
    private List<UUID> friendsList;
    private String avatarUrl;
}
