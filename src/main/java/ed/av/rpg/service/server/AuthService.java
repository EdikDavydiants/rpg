package ed.av.rpg.service.server;

import ed.av.rpg.auth.model.dto.SimpleMessageDto;
import ed.av.rpg.auth.model.dto.response.LogInDtoResponse;
import ed.av.rpg.auth.model.entity.User;
import ed.av.rpg.auth.repository.UserRepository;
import ed.av.rpg.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SimpMessagingTemplate messagingTemplate;

    public boolean registerNewUser(String username, String password) {
        String passwordHash = passwordEncoder.encode(password);
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        User user = User.builder()
                .username(username)
                .passwordHash(passwordHash)
                .isActive(true)
                .build();
        userRepository.save(user);
        return true;
    }

    public void logInUser(String sessionId, String login, String password) {
        Optional<User> userOpt = userRepository.findByUsername(login);
        Map<String, Object> headers = new HashMap<>();
        headers.put("session-id", sessionId);

        if(userOpt.isEmpty()) {
            var simpleMessageDto =  SimpleMessageDto.builder()
                    .message("Ошибка аутентификации: пользователя с таким username не существует!")
                    .build();
            headers.put("simple-class-name", SimpleMessageDto.class.getSimpleName());
            messagingTemplate.convertAndSend("/topic/common", simpleMessageDto, headers);
        } else if (!passwordEncoder.matches(password, userOpt.get().getPasswordHash())) {
            var simpleMessageDto =  SimpleMessageDto.builder()
                    .message("Ошибка аутентификации : неверный пароль!")
                    .build();
            headers.put("simple-class-name", SimpleMessageDto.class.getSimpleName());
            messagingTemplate.convertAndSend("/topic/common", simpleMessageDto, headers);
        } else {
            User user = userOpt.get();
            var logInDtoResponse = LogInDtoResponse.builder()
                    .message("Аутентификация прошла успешно.")
                    .userId(user.getId().toString())
                    .username(user.getUsername())
                    .build();
            headers.put("simple-class-name", LogInDtoResponse.class.getSimpleName());

            messagingTemplate.convertAndSend("/topic/common", logInDtoResponse, headers);
        }
    }
}
