package models;

import java.math.BigDecimal;

public class Item {

    private int sellerId;
    private String name;         
    private String size;         
    private BigDecimal price;    
    private String category;     

    public Item() {
    }

    public Item(int sellerId, String name, String size, BigDecimal price, String category) {
        this.sellerId = sellerId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public String getCategory() {
        return category;
    }
}
