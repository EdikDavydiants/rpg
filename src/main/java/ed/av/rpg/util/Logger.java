package ed.av.rpg.util;

import org.springframework.stereotype.Component;

@Component
public class Logger {

    public static void log(String msg) {
        System.out.println(msg);
    }
}
