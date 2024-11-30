package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;

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
}
