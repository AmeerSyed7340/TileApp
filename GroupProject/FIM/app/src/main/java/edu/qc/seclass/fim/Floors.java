package edu.qc.seclass.fim;

public class Floors {
    private int id;
    private String category;
    private String type;
    private String size;
    private String price;

    public Floors(int id, String category, String type, String size, String price) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}
