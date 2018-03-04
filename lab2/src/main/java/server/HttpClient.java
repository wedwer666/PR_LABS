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

    //implement sendAsyncFunctionFromModel

    // function send to request final request
    public List<String> send(HttpRequest request)
    {
        try {
            final URL url = request.uri.toURL();

        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    public CompletableFuture<List<String>> sendAsync (HttpRequest request)
    {
        // in order to avoid deamon thread i made new thread to start and to avoid terminating process with 0
        return CompletableFuture.supplyAsync(() -> this.send(request), e -> new Thread(e).start());
    }
}
