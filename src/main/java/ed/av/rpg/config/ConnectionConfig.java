package ed.av.rpg.config;

import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.connection.ConnectionForm;
import ed.av.rpg.form.connection.ConnectionFormFactory;
import ed.av.rpg.form.connection.ConnectionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConnectionConfig {

    @Bean
    ConnectionData connectionData() {
        return new ConnectionData();
    }

    @Bean
    ConnectionManager connectionManager(ConnectionData connectionData) {

        return new ConnectionManager(connectionData);
    }

    @Bean
    ConnectionForm connectionForm(ConnectionManager connectionManager) {

        return ConnectionFormFactory.getConnectionForm(connectionManager);
    }
}
