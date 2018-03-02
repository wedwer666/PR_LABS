package main;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import model.Order;
import org.apache.http.client.utils.URIBuilder;
public class Model {
    final private String csvSplitBy = ",";
    final private String key = "55193451-1409-4729-9cd4-7c65d63b8e76";

    final private Map<String, String> urls = Map.of(
            "categories", "https://evil-legacy-service.herokuapp.com/api/v101/categories/",
            "orders", "https://evil-legacy-service.herokuapp.com/api/v101/orders/"
    );

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
}
