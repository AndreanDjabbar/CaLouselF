package controllers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;

public class TransactionController {
	private Database db;

    public TransactionController() {
        db = Database.getInstance();
    }
    
    public String recordTransactions(int userId, int sellerId, int itemId, String itemName, String itemSize, BigDecimal itemPrice, String itemCategory) {
        String query = "INSERT INTO transactions (user_id, seller_id, item_id, item_name, item_size, item_price, item_category) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, sellerId);
            ps.setInt(3, itemId);
            ps.setString(4, itemName);      
            ps.setString(5, itemSize);      
            ps.setBigDecimal(6, itemPrice); 
            ps.setString(7, itemCategory);
            
            ps.executeUpdate();
            return "Transaction recorded successfully!";
        } catch (SQLException e) {
            return "Error while recording transaction: " + e.getMessage();
        }
    }



}
