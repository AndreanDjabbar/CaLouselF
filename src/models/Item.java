package models;

import java.math.BigDecimal;

public class Item {

	private int itemId;
    private int sellerId;
    private String itemName;         
    private String itemSize;         
    private BigDecimal itemPrice;    
    private String itemCategory;     

    public Item() {
    }

    public Item(int itemId, int sellerId, String itemName, String itemSize, BigDecimal itemPrice, String itemCategory) {
    	this.itemId = itemId;
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }
    
    public int getItemId() {return itemId; }
    public String getItemName() { return itemName; }
    public String getItemSize() { return itemSize; }
    public BigDecimal getItemPrice() { return itemPrice; }
    public String getItemCategory() { return itemCategory; }
    
    public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getSellerId() { return sellerId; }
}
