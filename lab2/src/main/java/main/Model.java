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
import org.apache.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;
public class Model {
    final private String csvSplitBy = ",";
    final private String key = "55193451-1409-4729-9cd4-7c65d63b8e76";

    final private Map<String, String> urls = Map.of(
            "categories", "https://evil-legacy-service.herokuapp.com/api/v101/categories/",
            "orders", "https://evil-legacy-service.herokuapp.com/api/v101/orders/"
    );

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
