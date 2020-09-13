package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import swingy.ClearScreen;
import swingy.Menu;
import dbConnection.ConnString;

public class Login {
	public static void login() {

    	String query = "SELECT username FROM swingy.dbo.users WHERE  username LIKE ?";
		
    	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			// Read user input from command line
			Scanner scanner = new Scanner(System.in);
			
			ClearScreen.clearScreen();
			System.out.print("LOGIN PAGE\n\nEnter your first name: ");
			String userName = scanner.nextLine();
			
			System.out.print("Enter your password: ");
			String passWord = scanner.nextLine();
						
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + userName + "%");
			ResultSet rs = SQL.executeQuery();
			
			//If user exist sign them in
			if (rs.next()) {
				String tag = userName;
				Menu.menu_page(tag);
            }
			else
				System.out.println("Username already exists, please try again");
		    scanner.close();
		} catch (SQLException e) {
            e.printStackTrace();
            }
    }
}
