package ed.av.rpg.util;

import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompHeaders;

public class HeadersBuilder {

    private final StompHeaders headers;

    private HeadersBuilder(StompHeaders headers) {
        this.headers = headers;
    }

    public static HeadersBuilder builder() {
        var headersBuilder = new HeadersBuilder(new StompHeaders());
        headersBuilder.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return headersBuilder;
    }

    public HeadersBuilder destination(String destination) {
        headers.setDestination(destination);
        return this;
    }

    public HeadersBuilder addHeader(String key, String value) {
        headers.add(key, value);
        return this;
    }

    public StompHeaders build() {
        return headers;
    }

    private StompHeaders getHeaders() {
        return this.headers;
    }
}
