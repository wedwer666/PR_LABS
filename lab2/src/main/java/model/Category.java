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
    public static Category fromCSV( String csv, String delimiter)
    {
        final String[] dates = csv.split(delimiter);
        final String name = dates[1];
        final Integer categoryId = dates.length == 2 ? null : Integer.parseInt(dates[2]);
        return new Category(id, name, categoryId);
    }
    @Override public String toString()
    {
        return "Category {" +
                "id = " + id + " , name = " + '\'' + "categoryId = " + categoryId + ", total = " + total + '}';
    }
}
