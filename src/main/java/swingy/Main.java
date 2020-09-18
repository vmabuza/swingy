package swingy;

import java.util.Scanner;

import dbConnection.CreateVillain;
import dbConnection.GameState;
import registration.SignUp;
import login.Login;

public class Main {
	public static void main(String[] args) {
		invoke();
	}
	
	public static void invoke() {
		CreateVillain.createVillain();
		GameState.createGameState();
		ClearScreen.clearScreen();
		String welcome_msg = "Welcome to Swingy\n\nPlease sign up or login bellow.\n1. Login\n2. Sign up\n\n";;
		
		//Read input
		Scanner reader = new Scanner(System.in);
		
		String input;
		try {
			System.out.println(welcome_msg);
			input = reader.nextLine();
			int convert = Integer.parseInt(input.trim());
			
			if (!input.isEmpty() && convert == 1) {
				Login.login();
			}
			else if (!input.isEmpty() && convert == 2) {
				SignUp.Register();
			}
			else {
				System.out.println("Invalid input, please start the game and try again");
				System.exit(0);
				}
			} catch (NumberFormatException nfe) {
					System.out.println("InvalidNumberFormatException: " + nfe.getMessage());
					} finally {reader.close(); }
	}
	}
