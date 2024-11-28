package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
