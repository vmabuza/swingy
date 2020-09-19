package registration;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.ConnString;
import swingy.ClearScreen;
import swingy.Main;

public class SignUp {
    
    public static void Register() {

    	String query = "SELECT username FROM swingy.dbo.users WHERE  username LIKE ?";
		
    	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			Scanner scanner = new Scanner(System.in);
			
			ClearScreen.clearScreen();
			System.out.print("REGISTRATION PAGE\n\nEnter a unique username: ");
			String userName = scanner.nextLine();
						
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + userName + "%");
			ResultSet rs = SQL.executeQuery();
			
			//If user doesn't exist save them
			if (!rs.next()) {
				String insert = "INSERT INTO swingy.dbo.users (username) VALUES (?)";
				PreparedStatement SQLsave = con.prepareStatement(insert);
		
				SQLsave.setString(1, userName);
				
				SQLsave.executeUpdate();
                System.out.println("Your information has been saved to our database, you can now login and enjoy the game.");
                Main.invoke();
            } else {
				System.out.println("Username already exists, please try again");
				Main.invoke();
			}
		    scanner.close();
		} catch (SQLException e) { e.printStackTrace();}
    }
}
