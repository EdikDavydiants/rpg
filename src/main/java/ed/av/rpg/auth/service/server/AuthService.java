package ed.av.rpg.auth.service.server;

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

import static ed.av.rpg.util.StringConstants.Headers.CLASS_NAME_HEADER_KEY;
import static ed.av.rpg.util.StringConstants.Headers.SESSION_ID_HEADER_KEY;
import static ed.av.rpg.util.StringConstants.InfoMessages.AUTHENTICATION_SUCCESS;
import static ed.av.rpg.util.StringConstants.InfoMessages.LOGIN_ALREADY_EXISTS;
import static ed.av.rpg.util.StringConstants.InfoMessages.REGISTRATION_SUCCESS;
import static ed.av.rpg.util.StringConstants.InfoMessages.USERNAME_NOT_FOUND;
import static ed.av.rpg.util.StringConstants.InfoMessages.WRONG_PASSWORD;
import static ed.av.rpg.util.StringConstants.TopicUri.COMMON_TOPIC_URI;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SimpMessagingTemplate messagingTemplate;

    public void registerNewUser(String sessionId, String username, String password) {
        String passwordHash = passwordEncoder.encode(password);
        String message;
        if (userRepository.existsByUsername(username)) {
            message = LOGIN_ALREADY_EXISTS;
        } else {
            User user = User.builder()
                    .username(username)
                    .passwordHash(passwordHash)
                    .isActive(true)
                    .build();
            userRepository.save(user);
            message = REGISTRATION_SUCCESS;
        }
        Map<String, Object> headers = new HashMap<>();
        headers.put(CLASS_NAME_HEADER_KEY, SimpleMessageDto.class.getSimpleName());
        headers.put(SESSION_ID_HEADER_KEY, sessionId);
        messagingTemplate.convertAndSend(COMMON_TOPIC_URI, new SimpleMessageDto(message), headers);
    }

    public void logInUser(String sessionId, String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        Map<String, Object> headers = new HashMap<>();
        headers.put(SESSION_ID_HEADER_KEY, sessionId);

        if(userOpt.isEmpty()) {
            var simpleMessageDto =  SimpleMessageDto.builder()
                    .message(USERNAME_NOT_FOUND)
                    .build();
            headers.put(CLASS_NAME_HEADER_KEY, SimpleMessageDto.class.getSimpleName());
            messagingTemplate.convertAndSend(COMMON_TOPIC_URI, simpleMessageDto, headers);
        } else if (!passwordEncoder.matches(password, userOpt.get().getPasswordHash())) {
            var simpleMessageDto =  SimpleMessageDto.builder()
                    .message(WRONG_PASSWORD)
                    .build();
            headers.put(CLASS_NAME_HEADER_KEY, SimpleMessageDto.class.getSimpleName());
            messagingTemplate.convertAndSend(COMMON_TOPIC_URI, simpleMessageDto, headers);
        } else {
            User user = userOpt.get();
            var logInDtoResponse = LogInDtoResponse.builder()
                    .message(AUTHENTICATION_SUCCESS)
                    .userId(user.getId().toString())
                    .username(user.getUsername())
                    .build();
            headers.put(CLASS_NAME_HEADER_KEY, LogInDtoResponse.class.getSimpleName());
            messagingTemplate.convertAndSend(COMMON_TOPIC_URI, logInDtoResponse, headers);
        }
    }
}
