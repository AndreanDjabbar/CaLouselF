package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String USERNAME = "calouseif";
    private String HOST = "localhost:3310";
    private String PASSWORD = "calouseif12";
    private String DB = "calouseif_db";
    private String URL = String.format(
            "jdbc:mysql://%s/%s",
            HOST, DB
    );
    private static Database instance = null;
    
    Connection connection = null;
    
    private Database() {
    	try {
    	    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	    if (connection != null) {
    	        System.out.println("Connection successful!");
    	    } else {
    	        System.out.println("Failed to connect to the database.");
    	    }
    	    createTablesIfNotExist();
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public PreparedStatement prepareStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    private void createTablesIfNotExist() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(100) NOT NULL UNIQUE, "
                + "password VARCHAR(100) NOT NULL, "
                + "phone_number VARCHAR(15) NOT NULL, "
                + "address TEXT NOT NULL, "
                + "role ENUM('seller', 'buyer') NOT NULL)";
        
        String createItemsQueueTable = "CREATE TABLE IF NOT EXISTS itemsQueue ("
                + "item_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "item_name VARCHAR(100) NOT NULL, "
                + "item_size VARCHAR(100) NOT NULL, "
                + "item_price DECIMAL(10, 2) NOT NULL, "
                + "item_category VARCHAR(100) NOT NULL, "
                + "seller_id INT NOT NULL, "
                + "FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE)";
        
        String createItemsTable = "CREATE TABLE IF NOT EXISTs items ("
                + "itemId INT AUTO_INCREMENT PRIMARY KEY, "
                + "item_name VARCHAR(100) NOT NULL, "
                + "item_size VARCHAR(100), "
                + "item_price DECIMAL(10, 2) NOT NULL, "
                + "item_category VARCHAR(100), "
                + "item_status VARCHAR(50) DEFAULT 'exist', "
                + "seller_id INT, "
                + "FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE"
                + ")";
        
        String rejectedItemsTable = "CREATE TABLE IF NOT EXISTS itemsRejected ("
                + "item_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "item_name VARCHAR(100) NOT NULL, "
                + "item_size VARCHAR(100) NOT NULL, "
                + "item_price DECIMAL(10, 2) NOT NULL, "
                + "item_category VARCHAR(100) NOT NULL, "
                + "reason TEXT, "
                + "seller_id INT NOT NULL, "
                + "FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE)";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createUsersTable);
            stmt.executeUpdate(createItemsQueueTable);
            stmt.executeUpdate(createItemsTable);
            stmt.executeUpdate(rejectedItemsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
