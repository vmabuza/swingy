package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import models.ConnString;

public class LevelOne {
	public static void levelOne(String tag, String selectedHero, int hLevel, int hDefense, int hAttack) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		//Map formula
		int formula = (1-1)*5+10-(1%2);
		//update hero XP formula
		int f = (int) Math.pow((1 - 1), 2);
		int xpFormula = 1 * 1000 + f * 450;
		
		String v1= "Rugal", v2 = "Poison Ivy", v3 = "Madara", v4 = "Joker", v5 = "Friezza", v6 = "Xerxes";
		int v1Attack = 3, v2Attack = 1, v3Attack = 3, v4Attack = 1, v5Attack = 2, v6Attack= 2;
		int v1Defense = 2, v2Defense = 0, v3Defense = 4, v4Defense = 1, v5Defense = 2, v6Defense = 1;
		
		String query1 = "UPDATE swingy.dbo.heroes SET experience = ? WHERE heroName LIKE ? AND owner LIKE ?";
		String query2 = "UPDATE swingy.dbo.heroes SET heroLevel = 1 WHERE heroName LIKE ? AND owner LIKE ?";
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			System.out.println("Yeah");
		} catch (SQLException e) { e.printStackTrace(); }
	}
}
