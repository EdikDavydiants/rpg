package ed.av.rpg.config;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import ed.av.rpg.gamescene.HexNet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MapRedactorConfig {

    @Bean
    public HexNet hexNet() {
        return new HexNet(7, 15);
    }

    @Bean
    public Pane episode(@Qualifier("hexNet") HexNet hexNet) {
        return hexNet;
    }

    @Bean
    public Pane mainPane(@Qualifier("episode") Pane episode) {
        var mainPane = new Pane();
        mainPane.getChildren().add(episode);
        return mainPane;
    }

    @Bean
    public Scene mainScene(@Qualifier("mainPane") Pane mainPane) {
        return new Scene(mainPane, 1000, 700);
    }
}
