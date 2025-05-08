package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {

    @Bean
    ConnectionData connectionData() {
        return new ConnectionData();
    }
}
