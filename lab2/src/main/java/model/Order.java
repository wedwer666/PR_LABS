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
    public static Order fromCSV(String csv, String delimiter)
    {
        final String[] dates = csv.split(delimiter);

        final String id = dates[0];
        final double total = Double.parseDouble(dates[1]);
        final int categoryId = Integer.parseInt(dates[2]);
        final LocalDate createdAt = LocalDate.parse(dates[3]);

        return new Order(id, total, createdAt, categoryId);
    }
    @Override public String toString()
    {
        return "Order{" +
                "id = " + id + '\'' + ", total = " + total + ", createdAt = " + createdAt + ", categoryId = " + categoryId + '}';
    }
}
