package model;

public class Category {
    public final int id;
    public final String name;
    public final Integer categoryId;
    public double total = 0.0;

    public Category(final int id, final String name, final Integer categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }
}
