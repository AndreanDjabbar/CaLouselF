package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import models.User;
import validators.UserValidator;

public class UserController {

    private Database db;

    public UserController() {
        db = Database.getInstance();
    }

    public String registerUser(String username, String password, String phoneNumber, String address, String role) {
    	if (isUserExists(username)) {
    		return "Username Already Exist";
    	}
    	
        try {
            String query = "INSERT INTO users (username, password, phone_number, address, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, phoneNumber);
            ps.setString(4, address);
            ps.setString(5, role);
            ps.executeUpdate();
            return "User successfully registered!";
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return "Username already exists. Please choose another one.";
            }
            return "Error while saving user: " + e.getMessage();
        }
    }
    
    public boolean isUserExists(String username) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
