package ed.av.rpg;

import org.springframework.stereotype.Component;

@Component
public class Logger {

    public static void log(String msg) {
        System.out.println(msg);
    }
}
