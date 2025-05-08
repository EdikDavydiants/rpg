package ed.av.rpg.auth.model.dto;

public record LogInDto (

        String login,
        String password,
        String masterIP
) {
}
