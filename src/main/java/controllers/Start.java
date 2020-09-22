package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ConnString;
import views.ClearScreen;
import views.LevelOne;

public class Start {
	
	public static void startGame(String tag, String selectedHero) {
			ClearScreen.clearScreen();
			String query2 = "SELECT * FROM swingy.dbo.heroes WHERE  owner LIKE ? AND heroName LIKE ?";
			
			try (Connection con = DriverManager.getConnection(ConnString.conn());) {
				PreparedStatement SQL2 = con.prepareStatement(query2);
				SQL2.setString(1, "%" + tag + "%");
				SQL2.setString(2, "%" + selectedHero + "%");
				
				ResultSet rs2 = SQL2.executeQuery();
				
				if (rs2.next()) {
						int hLevel = rs2.getInt("heroLevel");
						int hAttack = rs2.getInt("heroAttack");
						int hDefense = rs2.getInt("heroDefense");
						if (hLevel == 0) 
							LevelOne.levelOne(tag, selectedHero, hLevel, hDefense, hAttack);
						else if (hLevel == 1)
							levelTwo(tag, selectedHero, hLevel);
						else if (hLevel == 2)
							levelThree(tag, selectedHero, hLevel);
						else if (hLevel == 3)
							levelFour(tag, selectedHero, hLevel);
						else if (hLevel == 4)
							finishGame(tag, selectedHero, hLevel);
						}
				} catch (SQLException e) { e.printStackTrace();}
			}

	
	
	private static void levelTwo(String tag, String selectedHero, int value) {
		System.out.println("Welcome to level 2");
	}
	
	private static void levelThree(String tag, String selectedHero, int value) {
		
	}
	
	private static void levelFour(String tag, String selectedHero, int value) {
		
	}
	
	private static void finishGame(String tag, String selectedHero, int value) {
		
	}



	
}
