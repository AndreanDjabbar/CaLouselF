package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import models.Item;
import models.ItemQueue;

public class ItemController {
	
	private Database db;

    public ItemController() {
        db = Database.getInstance();
    }

	public String uploadItemToQueue(int sellerId, String itemName, String itemCategory , String itemSize, String itemPrice) {
        try {
            String query = "INSERT INTO itemsQueue (seller_id, item_name, item_category, item_size, item_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setInt(1, sellerId);
            ps.setString(2, itemName);
            ps.setString(3, itemCategory);
            ps.setString(4, itemSize);
            ps.setString(5, itemPrice);
            ps.executeUpdate();
            return "Success Upload Item!.. Please wait for admin approvement";
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return "Item already exist";
            }
            return "Error while Uploading Item: " + e.getMessage();
        }
    }
	
	public List<ItemQueue> getAllItemsQueue() {
        List<ItemQueue> items = new ArrayList<>();
        String query = "SELECT * FROM itemsQueue";
        
        try (ResultSet rs = Database.getInstance().prepareStatement(query).executeQuery()) {
            while (rs.next()) {
                ItemQueue item = new ItemQueue(
                    rs.getInt("item_id"),
                    rs.getInt("seller_id"),
                    rs.getString("item_name"),
                    rs.getString("item_size"),
                    rs.getBigDecimal("item_price"),
                    rs.getString("item_category")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
	
	public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        
        try (ResultSet rs = Database.getInstance().prepareStatement(query).executeQuery()) {
            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("item_id"),
                    rs.getInt("seller_id"),
                    rs.getString("item_name"),
                    rs.getString("item_size"),
                    rs.getBigDecimal("item_price"),
                    rs.getString("item_category")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
	
	public ItemQueue getItemQueueById(int itemId) {
        String query = "SELECT * FROM itemsQueue WHERE item_id = ?";
        ItemQueue item = null;

        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new ItemQueue(
                        rs.getInt("item_id"),
                        rs.getInt("seller_id"),
                        rs.getString("item_name"),
                        rs.getString("item_size"),
                        rs.getBigDecimal("item_price"),
                        rs.getString("item_category")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }
	
	public void approveItem(ItemQueue newItem) {
	    String insertQuery = "INSERT INTO items (seller_id, item_name, item_size, item_price, item_category, item_status) "
	                       + "SELECT seller_id, item_name, item_size, item_price, item_category, 'exist' FROM itemsQueue WHERE item_id = ?";

	    String deleteQuery = "DELETE FROM itemsQueue WHERE item_id = ?";

	    try (PreparedStatement psInsert = db.prepareStatement(insertQuery);
	         PreparedStatement psDelete = db.prepareStatement(deleteQuery)) {

	        psInsert.setInt(1, newItem.getItemId());

	        psInsert.executeUpdate();

	        psDelete.setInt(1, newItem.getItemId());
	        psDelete.executeUpdate();

	        System.out.println("Item " + newItem.getItemId() + " has been approved and moved to items.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void declineItem(ItemQueue targetItem, String reason) {
		 String insertQuery = "INSERT INTO itemsRejected (item_name, item_size, item_price, item_category, seller_id, reason) "
		            + "SELECT item_name, item_size, item_price, item_category, seller_id, ? "
		            + "FROM itemsQueue WHERE item_id = ?";
	    String deleteQuery = "DELETE FROM itemsQueue WHERE item_id = ?";

	    try (PreparedStatement psInsert = db.prepareStatement(insertQuery);
	         PreparedStatement psDelete = db.prepareStatement(deleteQuery)) {

	        psInsert.setString(1, reason);  
	        psInsert.setInt(2, targetItem.getItemId());  

	        psInsert.executeUpdate();
	        psDelete.setInt(1, targetItem.getItemId());  

	        psDelete.executeUpdate();

	        System.out.println("Item " + targetItem.getItemId() + " has been declined and moved to itemsRejected.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
}
