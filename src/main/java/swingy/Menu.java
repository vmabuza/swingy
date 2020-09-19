package swingy;

import java.util.Scanner;

import hero.ChooseHero;
import hero.CreateHero;

public class Menu {
	public static void menu_page(String tag) {
    	Scanner scanner = new Scanner(System.in);
		
    	try {
    		ClearScreen.clearScreen();
    		System.out.println("MENU\n\nHi " + tag + ", the following options are available to you:\n1. Choose Hero\n2. Create Hero\n\n");
    		String option = scanner.nextLine();
    		int convert = Integer.parseInt(option.trim());
    		
    		if (!option.isEmpty() && convert == 1) {
    			ChooseHero.chooseHero(tag);
    		} else if (!option.isEmpty() && convert == 2) {
    			CreateHero.createHero(tag);
    		}
    	} catch (NumberFormatException nfe) {System.out.println("InvalidNumberFormatException: " + nfe.getMessage());} 
    	finally {scanner.close(); }
    	}
	}
