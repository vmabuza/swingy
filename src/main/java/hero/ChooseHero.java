package hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.ConnString;

public class ChooseHero {
	public static void chooseHero(String tag) {
		String query = "SELECT heroName FROM swingy.dbo.heroes WHERE  owner LIKE ?";
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			// Read user input from command line
//			Scanner scanner = new Scanner(System.in);
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + tag + "%");
			ResultSet rs = SQL.executeQuery();
			
			 // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("heroName"));
//                System.out.println("Hello bitches");
            }
		} catch (SQLException e) {
            e.printStackTrace();
            }
	}
}
