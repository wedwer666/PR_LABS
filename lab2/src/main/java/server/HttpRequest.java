package server;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    final public Map<String, String> headers;
    final public URI uri;

    private HttpRequest(URI uri, Map<String, String> headers) {
        this.uri = uri;
        this.headers = headers;
    }

    // crearea constructorilor pentru httprequest
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
        private Map<String, String> headers = new HashMap<>();
        private URI uri;

        public HttpRequest build()
        {
            return new HttpRequest(uri, headers);
        }
        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }
        public Builder header(String name, String value)
        {
            headers.put(name, value);
            return this;
        }
    }
}
