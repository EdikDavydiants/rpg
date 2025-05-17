package ed.av.rpg.auth.service;

import ed.av.rpg.auth.model.entity.User;
import ed.av.rpg.auth.repository.UserRepository;
import ed.av.rpg.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean registerNewUser(String username, String password) {
        String passwordHash = passwordEncoder.encode(password);
        User user = User.builder()
                .username(username)
                .passwordHash(passwordHash)
                .isActive(true)
                .build();

        if (userRepository.existsByUsername(username)) {
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
