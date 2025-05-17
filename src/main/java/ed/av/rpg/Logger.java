package ed.av.rpg;

import org.springframework.stereotype.Component;

@Component
public class Logger {

    public void log(String msg) {
        System.out.println(msg);
    }
}
