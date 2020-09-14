package hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.ConnString;
import swingy.ClearScreen;
import swingy.Menu;

public class CreateHero {
	public static void createHero(String tag) {
		Scanner scanner = new Scanner(System.in);
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			ClearScreen.clearScreen();
			System.out.print("CREATE YOUR HERO\n\nHero name: ");
			String heroName = scanner.nextLine();
			
			System.out.println("Please choose a class for your hero: \n1. Ninja\n2. Samorai\n3. Martial art\n4. Military\n5. Hood");
			String heroClass = scanner.nextLine();
			int convertClass = Integer.parseInt(heroClass.trim());
			String reConvertClass = Integer.toString(convertClass);
			if (convertClass == 1) 
				reConvertClass = "Ninja";
			else if (convertClass == 2)
				reConvertClass = "Samorai";
			else if (convertClass == 3)
				reConvertClass = "Martial arts";
			else if (convertClass == 4)
				reConvertClass = "Military";
			else if (convertClass == 5)
				reConvertClass = "Hood";
			else {
				System.out.println("Error, invalid selection");
				createHero(tag);
			}
			System.out.println("Please choose start up attack level: \n1. 1\n2. 2\n3. 3");
			String heroAttack = scanner.nextLine();
			int convertAttack = Integer.parseInt(heroAttack.trim());
			
			System.out.println("Please choose start up defense level: \n1. 1\n2. 2\n3. 3");
			String heroDefense = scanner.nextLine();
			int convertDefense = Integer.parseInt(heroDefense.trim());
			
			ClearScreen.clearScreen();
			System.out.println("You have chosen the following attributes for your new hero");
			System.out.println("Hero name: " + heroName + "\nHero class: " + reConvertClass + "\nHero attack: " + heroAttack + "\nHero defense: " + heroDefense);
			System.out.println("\n1. Confirm\n2. Cancel");
			String confirmNewHero = scanner.nextLine();
			int cNewHero = Integer.parseInt(confirmNewHero.trim());
			
			if (cNewHero == 1) {
				String insert = "INSERT INTO swingy.dbo.heroes (heroName, heroClass, heroAttack, heroDefense, owner) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement SQLsave = con.prepareStatement(insert);
		
				SQLsave.setString(1, heroName);
				SQLsave.setString(2, reConvertClass);
				SQLsave.setLong(3, convertAttack);
				SQLsave.setLong(4, convertDefense);
				SQLsave.setString(5, tag);
				SQLsave.executeUpdate();
				
				System.out.println("Your hero has been successfuly created...");
				Menu.menu_page(tag);
			}
			else
				Menu.menu_page(tag);
		}  catch (NumberFormatException nfe) {
			System.out.println("InvalidNumberFormatException: " + nfe.getMessage());
			}catch (SQLException e) {
	            e.printStackTrace();
	            }finally {scanner.close();}
	}
}
