package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import models.ConnString;
import views.ClearScreen;
import views.Menu;

public class Login {
	public static void login() {

    	String query = "SELECT username FROM swingy.dbo.users WHERE  username LIKE ?";
    	Scanner scanner = new Scanner(System.in);
		
    	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			
			ClearScreen.clearScreen();
			System.out.print("LOGIN PAGE\n\nEnter your Username: ");
			String userName = scanner.nextLine();
						
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + userName + "%");
			ResultSet rs = SQL.executeQuery();
			
			//If user exist sign them in
			if (rs.next()) {
				String tag = userName;
				Menu.menu_page(tag);
            } else {
				System.out.println("Invalid username, please try again");
				Main.invoke();
				}
			} catch (SQLException e) { e.printStackTrace(); }
    	finally {scanner.close();}
    	}
	}
