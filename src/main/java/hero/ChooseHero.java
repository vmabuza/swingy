package hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.ConnString;
import swingy.Start;

public class ChooseHero {
	public static void chooseHero(String tag) {
		String query = "SELECT heroName FROM swingy.dbo.heroes WHERE  owner LIKE ?";
		String query2 = "SELECT * FROM swingy.dbo.heroes WHERE  owner LIKE ? AND heroName LIKE ?";
		int i = 1;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			
			PreparedStatement SQL = con.prepareStatement(query);
			SQL.setString(1, "%" + tag + "%");
			ResultSet rs = SQL.executeQuery();
			
			System.out.println("The following is a collection of your heroes:\nType the name of the hero you wish to choose below.");
			 // Iterate through the heroes in the result set and display it.
            while (rs.next()) {
                System.out.println(i++ + ". " + rs.getString("heroName"));
            }
            
            System.out.println("\n\n");
            String input = scanner.nextLine();
            
            PreparedStatement SQL2 = con.prepareStatement(query2);
			SQL2.setString(1, "%" + tag + "%");
			SQL2.setString(2, "%" + input + "%");
			ResultSet rs2 = SQL2.executeQuery();
            
            if (rs2.next()) {
            	System.out.println("You have chosen the hero " + '\'' + input + "' who has the following Stats: \n");
            	System.out.println("Hero name: " + rs2.getString("heroName"));
            	System.out.println("Hero classification: " + rs2.getString("heroClass"));
            	System.out.println("Attack: " + rs2.getString("heroAttack"));
            	System.out.println("Defense: " + rs2.getString("heroDefense"));
            	System.out.println("Level: " + rs2.getString("heroLevel"));
            	System.out.println("Hitpoints: " + rs2.getString("hitPoints"));
            	System.out.println("Experience: " + rs2.getString("experience"));
            	
            	String selectedHero = rs2.getString("heroName");
            	System.out.println("\n\n1. Start game\n2. Choose a different hero");
            	String input2 = scanner.nextLine();
            	int convert = Integer.parseInt(input2.trim());
            	
            	if (convert == 1)
            		Start.startGame(tag, selectedHero);
            	else
            		chooseHero(tag);
            	} else {
            	System.out.println("You have chosen a non existing hero " + input + "Please try again");
            	chooseHero(tag);
            }
            } catch (SQLException e) { e.printStackTrace();}
		} 
	}
	