package controllers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public BigDecimal getHighestOffer(int userId, int itemId) {
        String query = "SELECT MAX(offer_price) FROM offers WHERE user_id = ? AND item_id = ?";
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setInt(1, userId); 
            ps.setInt(2, itemId); 
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1); 
            }
        } catch (SQLException e) {
            System.out.println("Error fetching highest offer: " + e.getMessage());
        }
        return BigDecimal.ZERO; 
    }

    public String makeOffer(int userId, int sellerId, int itemId, BigDecimal offerPrice) {
        String query = "INSERT INTO offers (user_id, seller_id, item_id, offer_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, sellerId);
            ps.setInt(3, itemId);
            ps.setBigDecimal(4, offerPrice);
            ps.executeUpdate();
            return "Offer successfully recorded!";
        } catch (SQLException e) {
            return "Error recording offer: " + e.getMessage();
        }
    }
    
    public boolean hasExistingOffer(int userId, int itemId) {
        String query = "SELECT COUNT(*) FROM offers WHERE user_id = ? AND item_id = ?";
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            System.out.println("Error checking existing offer: " + e.getMessage());
        }
        return false; 
    }
    
    public String updateOffer(int userId, int itemId, BigDecimal newOffer) {
        String query = "UPDATE offers SET offer_price = ? WHERE user_id = ? AND item_id = ?";
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setBigDecimal(1, newOffer);
            ps.setInt(2, userId);
            ps.setInt(3, itemId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return "Offer successfully updated!";
            }
        } catch (SQLException e) {
            System.out.println("Error updating offer: " + e.getMessage());
            return "Failed to update offer: " + e.getMessage();
        }
        return "Failed to update offer."; 
    }

}
