package main;

import model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        Model model = new Model();
        // i wanna extract categories from specific dates
        List<Category> categories = model.getTotalPerCategories(LocalDate.of(2010,1,1), LocalDate.of(2016,1,1))
                .get();
    }
}
