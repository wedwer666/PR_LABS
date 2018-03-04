package model;

public class Category {
    //    id,name,category_id
    public final int id;
    public final String name;
    public final Integer categoryId;
    public double total = 0.0;

    public Category(final int id, final String name, final Integer categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public static Category fromCSV(String csv, String delimiter) {
        final String[] dates = csv.split(delimiter);

        final int id = Integer.parseInt(dates[0]);
        final String name = dates[1];
        final Integer categoryId = dates.length == 2 ? null : Integer.parseInt(dates[2]);

        return new Category(id, name, categoryId);
    }

    @Override public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", total=" + total +
                '}';
    }
}
