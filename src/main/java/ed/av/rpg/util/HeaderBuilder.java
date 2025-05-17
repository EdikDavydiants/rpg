package ed.av.rpg.util;

import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompHeaders;

public class HeaderBuilder {

    public static StompHeaders createHeader(String destination) {
        var header = new StompHeaders();
        header.setDestination(destination);
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }
}
