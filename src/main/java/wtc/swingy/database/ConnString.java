package wtc.swingy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnString {
	public static String init() {
		String JDBC_URL = "jdbc:sqlserver://MSI-BEAST:1433;user=dev;password=Joker@thejoke";
		return JDBC_URL;
	}
	
	public static void createDB() {
		try (Connection con = DriverManager.getConnection(ConnString.init());) {
			
			String sql = "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'swingy')\r\n" + 
					"BEGIN\r\n" + 
					"  CREATE DATABASE swingy;\r\n" + 
					"END;\r\n";
			
			System.out.println("Creating database...");
		      
		    PreparedStatement stmt = con.prepareStatement(sql);
		      
		    stmt.executeUpdate();
		    System.out.println("Database created successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String conn() {
		String JDBC_URL = "jdbc:sqlserver://MSI-BEAST:1433;databaseName=swingy;user=dev;password=Joker@thejoke";
		return JDBC_URL;
	}
}
