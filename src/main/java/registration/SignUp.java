package registration;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.ConnString;
import swingy.ClearScreen;

public class SignUp {
    
    public static void Register() {

    	String query = "SELECT username FROM swingy.dbo.users WHERE  username LIKE ?";
		
    	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			// Read user input from command line
			Scanner scanner = new Scanner(System.in);
			
			ClearScreen.clearScreen();
			System.out.print("REGISTRATION PAGE\n\nEnter your first name: ");
			String userName = scanner.nextLine();
			
			System.out.print("Enter your password: ");
			String passWord = scanner.nextLine();
						
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + userName + "%");
			ResultSet rs = SQL.executeQuery();
			
			//If user doesn't exist save them
			if (!rs.next()) {
				String insert = "INSERT INTO swingy.dbo.users (username, password) VALUES (?, ?)";
				PreparedStatement SQLsave = con.prepareStatement(insert);
		
				SQLsave.setString(1, userName);
				SQLsave.setString(2, passWord);
				SQLsave.executeUpdate();
                System.out.println("Your information has been saved to our database, you can now login and enjoy the game.");
            }
			else
				System.out.println("Username already exists, please try again");
		    scanner.close();
		} catch (SQLException e) {
            e.printStackTrace();
            }
    }
}
    
    
//    public static void select() {
//
//        try (Connection con = DriverManager.getConnection(JDBC_URL); Statement stmt = con.createStatement();) {
//            String SQL = "SELECT TOP 10 * FROM swingy.dbo.employees";
//            ResultSet rs = stmt.executeQuery(SQL);
//
//            // Iterate through the data in the result set and display it.
//            while (rs.next()) {
//                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
//                System.out.println("Hello bitches");
//            }
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//            }
//    }
//}
