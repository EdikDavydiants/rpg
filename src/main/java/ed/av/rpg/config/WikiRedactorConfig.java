package ed.av.rpg.config;

import ed.av.rpg.wiki.redactor.Redactor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class WikiRedactorConfig {

    @Bean
    public Redactor redactor() {
        var redactor = new Redactor();
        redactor.addChild("Материальное", "Все существующие во вселенной материальные объекты и субстанции.");
        redactor.addChild("Нематериальное", "Все существующее во вселенной нематериальное");
        return redactor;
    }
}
