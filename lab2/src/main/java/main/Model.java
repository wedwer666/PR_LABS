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
        return client.sendAsync(categoriesRequest)
                .thenApply(list -> list.stream()
                        .skip(1)
                        .map(csv -> Category.fromCSV(csv, csvSplitBy))
                        .collect(Collectors.toList()));
    }

    // function that gets all categories from urls

    //function to get all orders from both urls
    public CompletableFuture<List<Order>> getOrders(LocalDate start, LocalDate end) {
        try {
            final URI ordersURI = new URIBuilder(urls.get("orders"))
                    .addParameter("start", start.toString())
                    .addParameter("end", end.toString())
                    .build();
            final HttpRequest ordersRequest = HttpRequest.newBuilder(ordersURI)
                    .header("Accept", "text/csv")
                    .header("x-api-key", key)
                    .build();
            return client.sendAsync(ordersRequest)
                    .thenApply(list -> list.stream()
                            .skip(1)
                            .map(csv -> Order.fromCSV(csv, csvSplitBy))
                            .collect(Collectors.toList())
                            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    // final function that will calculate total sum from categories
    // forma in care trebuie sa ajunga datele
    public CompletableFuture<List<Category>> getTotalPerCategories(LocalDate start, LocalDate end)
    {
        final CompletableFuture<List<Category>> categoriesFuture =  getCategories();
        final CompletableFuture<List<Order>> ordersFuture = getOrders(start, end);
        //cind in ambele o sa fie valoare
        return categoriesFuture.thenCombine(ordersFuture, this::getTotalPerCategory);
    }
    private List<Category> getTotalPerCategory(List<Category> categories, List<Order> orders) {
        getTotalPerCategory(categories, orders, null);
        return categories;
    }

    private double getTotalPerCategory(List<Category> categories, List<Order> orders, Category parent) {
        final double total = getTotalPerCategory(parent, orders)
                + categories.stream()
                .filter(child -> parent == null
                        ? child.categoryId == null
                        : child.categoryId != null && parent.id == child.categoryId)
                )


    // toata logica - filtreaza toate categorrile asa ca id-ul pairentelui sa corespunda cu id-ul copilului si category
    // Category parent
}
