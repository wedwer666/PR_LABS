package server;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class HttpClient {
    public HttpClient() {
    }

    public static HttpClient newHttpClient()
    {
        return new HttpClient();
    }
}
