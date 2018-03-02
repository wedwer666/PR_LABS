package model;

import java.time.LocalDate;

public class Order {
    public final String id;
    public final double total;
    public final LocalDate createdAt;
    public final int categoryId;

    private Order(final String id, final double total, final LocalDate createdAt, final int categoryId) {
        this.id = id;
        this.total = total;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

}
