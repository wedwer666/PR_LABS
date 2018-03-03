package server;

import javafx.util.Builder;

import java.net.URI;
import java.util.Map;

public class HttpRequest {
    final public Map<String, String> headers;
    final public URI uri;

    public HttpRequest(Map<String, String> headers, URI uri) {
        this.headers = headers;
        this.uri = uri;
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static Builder newBuilder(URI uri)
    {
        final Builder builder = new Builder();
        return builder.uri(uri);
    }
    static public class Builder
    {
        private URI uri;

        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }
    }
}
