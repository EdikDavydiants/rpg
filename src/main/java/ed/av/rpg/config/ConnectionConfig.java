package ed.av.rpg.config;

import ed.av.rpg.auth.connection.AuthenticationData;
import ed.av.rpg.auth.connection.ConnectionData;
import ed.av.rpg.form.connection.ConnectionForm;
import ed.av.rpg.form.connection.ConnectionFormFactory;
import ed.av.rpg.form.connection.ConnectionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    AuthenticationData authenticationData() {
        return new AuthenticationData();
    }

    @Bean
    ConnectionManager connectionManager(ConnectionForm connectionForm,
                                        ConnectionData connectionData, MainStompSessionHandler handler) {
        return new ConnectionManager(connectionForm, connectionData, handler);
    }

    @Bean
    ConnectionForm connectionForm(ApplicationEventPublisher eventPublisher) {
        return ConnectionFormFactory.getConnectionForm(eventPublisher);
    }
}
