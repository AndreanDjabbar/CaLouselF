package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

public class WishlistController {

	private Database db;

    public WishlistController() {
        db = Database.getInstance();
    }
    
    public boolean isItemInWishlist(int itemId, int userId) {
        try {
            String query = "SELECT COUNT(*) FROM wishlists WHERE item_id = ? AND user_id = ?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setInt(1, itemId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking wishlist: " + e.getMessage());
        }
        return false;
    }

    public String addToWishlist(int itemId, int userId) {
        if (isItemInWishlist(itemId, userId)) {
            return "Item already in wishlist!";
        }
        try {
            String query = "INSERT INTO wishlists (item_id, user_id) VALUES (?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setInt(1, itemId);
            ps.setInt(2, userId);
            ps.executeUpdate();
            return "Item successfully added to wishlist!";
        } catch (SQLException e) {
            return "Error while saving Item: " + e.getMessage();
        }
    }
}
