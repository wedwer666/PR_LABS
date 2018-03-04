package main;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import model.Category;
import model.Order;
import org.apache.http.client.utils.URIBuilder;
import server.HttpClient;
import server.HttpRequest;

public class Model {
    final private String csvSplitBy = ",";
    final private HttpClient client = HttpClient.newHttpClient();
    final private String key = "55193451-1409-4729-9cd4-7c65d63b8e76";

    // puting all info in a map structure
    final private Map<String, String> urls = Map.of(
            "categories", "https://evil-legacy-service.herokuapp.com/api/v101/categories/",
            "orders", "https://evil-legacy-service.herokuapp.com/api/v101/orders/"
    );

    public CompletableFuture<List<Category>> getCategories()
    {
        // seting headers to the list that will be returned
        final URI categoriesURI = URI.create(urls.get("categories"));
        final HttpRequest categoriesRequest = HttpRequest.newBuilder(categoriesURI)
                .header("Accept", "text/csv")
                .header("x-api-key", key)
                .build();

        // return the result in async form
        return client.sendAsync
    }
    // function that send async all the data available
    public CompletableFuture<List<String>> sendAsync(HttpRequest request)
    {
        // in order to avoid deamon thread i made new thread to start and to avoid terminating process with 0
        return CompletableFuture.supplyAsync(() -> );
    }
    // function that gets all categories from urls
    public CompletableFuture<List<Category>> getCategories()
    {
        final URI categoriesURI = URI.create(urls.get("categories"));
        final HttpRequest categoriesRequest = HttpRequest;
    }
    //function to get all orders from both urls
    public CompletableFuture<List<Order>> getOrders(LocalDate start, LocalDate end) {
        try {
            final URI ordersURI = new URIBuilder(urls.get("orders"))
                    .addParameter("start", start.toString())
                    .addParameter("end", end.toString())
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    // final function that will calculate total sum from categories
    public CompletableFuture<List<Category>> getTotalPerCategories(LocalDate start, LocalDate end)
    {
        //
        final CompletableFuture<List<Order>> ordersFuture = getOrders(start, end);
        return;
    }

}
