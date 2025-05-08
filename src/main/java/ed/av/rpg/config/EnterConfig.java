package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.module.connection.ConnectionForm;
import ed.av.rpg.module.connection.ConnectionFormFactory;
import ed.av.rpg.module.connection.ConnectionManager;
import ed.av.rpg.module.login.LogInForm;
import ed.av.rpg.module.register.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EnterConfig {

    private final ConnectionData connectionData;

    @Bean
    ConnectionManager enterManager() {

        return new ConnectionManager(connectionData);
    }

    @Bean
    ConnectionForm enterForm(ConnectionManager connectionManager) {

        return ConnectionFormFactory.getEnterForm(connectionManager);
    }
}
