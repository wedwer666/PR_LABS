package main;

import model.Category;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Model model = new Model();
//        model.getCategories().thenAccept(list -> list.stream().forEach(System.out::println));
//        model.getOrders(LocalDate.of(2010, 1, 1), LocalDate.now()).thenAccept(System.out::println);
//        List<Category> categories = model.getTotalPerCategories(LocalDate.of(2010, 1, 1), LocalDate.now())
        List<Category> categories = model.getTotalPerCategories(LocalDate.of(2010, 1, 1), LocalDate.of(2016,1,1))
                .thenApply(list -> list.stream().sorted(Comparator.comparing(category -> category.id)).collect(Collectors.toList()))
                .get();
        categories.forEach(System.out::println);
    }
}

