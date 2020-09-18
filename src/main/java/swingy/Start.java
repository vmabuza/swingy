package swingy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.ConnString;

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
							levelOne(tag, selectedHero, hLevel, hDefense, hAttack);
						else if (hLevel == 1)
							levelTwo(tag, selectedHero, hLevel);
						else if (hLevel == 2)
							levelThree(tag, selectedHero, hLevel);
						else if (hLevel == 3)
							levelFour(tag, selectedHero, hLevel);
						else if (hLevel == 4)
							finishGame(tag, selectedHero, hLevel);
				}
			} catch (SQLException e) {
                e.printStackTrace();
                }
			System.out.println();
			
	}

	private static void levelOne(String tag, String selectedHero, int hLevel, int hDefense, int hAttack) {
		Scanner scanner = new Scanner(System.in);
		int formula = (1-1)*5+10-(1%2);

		String v1= "Rugal", v2 = "Poison Ivy", v3 = "Madara", v4 = "Joker", v5 = "Friezza", v6 = "Xerxes";
		int v1Attack = 3, v2Attack = 1, v3Attack = 3, v4Attack = 1, v5Attack = 2, v6Attack= 2;
		int v1Defense = 2, v2Defense = 0, v3Defense = 4, v4Defense = 1, v5Defense = 2, v6Defense = 1;
		
		String query1 = "UPDATE swingy.dbo.heroes SET experience = ? WHERE heroName LIKE ? AND owner LIKE ?";
		String query2 = "UPDATE swingy.dbo.heroes SET heroLevel = 1 WHERE heroName LIKE ? AND owner LIKE ?";
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			System.out.println("You are at the center of the map, in this Level you have a total of" + formula + " nine moves, your goal is to reach the edges of the map within the allocated moves.");

			System.out.println("You have the following options\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
			int uInput = scanner.nextInt();
			int moves = 0;
			int sum = 0;

			if (hAttack == 1 && hDefense == 1) { //Algorithm for heroes with low defense and low attack
				moves = moves + 1;
				sum =  9 - moves;
				System.out.println("\nMoves left = 9 - 1 = " + sum);
				
				if (uInput == 1) {//Big East
					System.out.println("You have stumbled accross the villain " + v2 + " with attack level of " + v2Attack + " and a defense level of " + v2Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
					System.out.println("1. Fight villain\n2. Run away\n\n");
					int uInput2 = scanner.nextInt();
						
					if (uInput2 == 1) {
						moves = moves + 1;
						sum =  9 - moves;
						System.out.println("\nMoves left = 9 - 2 = " + sum);
						
						if (v2Attack <= hAttack || v2Defense < hDefense) {
							System.out.println("You have defeated " + v2 + ". You are 1 - 2 right moves away from reaching the map boarders.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
							int uInput3 = scanner.nextInt();
							if (uInput3 == 3) {//winner
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 3 = " + sum);
								
								System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
								System.out.println("1. Fight villain\n2. Run away");
								int uInput4 = scanner.nextInt();
								if (uInput4 == 1) {
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									if (v4Attack <= hAttack && v4Defense <= hDefense) {
										System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
										System.out.println("Level completed in " + sum + " moves.");
										//Save hero level	
										PreparedStatement SQL2 = con.prepareStatement(query2);
										SQL2.setString(1, "%" + selectedHero + "%");
										SQL2.setString(2, "%" + tag + "%");
											
										SQL2.executeUpdate();
										//update hero XP
										int f = (int) Math.pow((1 - 1), 2);
										int xpFormula = 1 * 1000 + f * 450;
										
										PreparedStatement SQL1 = con.prepareStatement(query1);
										SQL1.setInt(1, xpFormula);
										SQL1.setString(2, "%" + selectedHero + "%");
										SQL1.setString(3, "%" + tag + "%");
											
										SQL1.executeUpdate();
										startGame(tag, selectedHero);
									} 
								} else if (uInput4== 2) {//after run away from joker
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput44 = scanner.nextInt();
									if (uInput44 == 1) {//east a
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput45 = scanner.nextInt();
										if (uInput45 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput45 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput46 = scanner.nextInt();
										if (uInput46 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput46 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 3) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 4) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
								}
							} if (uInput3 == 4) {//Looser
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 3 = " + sum);
								
								System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
								System.out.println("1. Fight villain\n2. Run away");
								int uInput4 = scanner.nextInt();
								if (uInput4 == 1) {
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("Loose");
									Menu.menu_page(tag);
								} else if (uInput4== 2) {//after run away from madara
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput44 = scanner.nextInt();
									if (uInput44 == 1) {//east a
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput45 = scanner.nextInt();
										if (uInput45 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput45 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput46 = scanner.nextInt();
										if (uInput46 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput46 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 3) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 4) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
								}
							}else if (uInput3 == 2) {//Looser
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 3 = " + sum);
								
								System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
								System.out.println("1. Fight villain\n2. Run away");
								int uInput4 = scanner.nextInt();
								if (uInput4 == 1) {
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("Loose");
									Menu.menu_page(tag);
								} else if (uInput4== 2) {//after run away from madara
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput44 = scanner.nextInt();
									if (uInput44 == 1) {//east a
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput45 = scanner.nextInt();
										if (uInput45 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput45 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput46 = scanner.nextInt();
										if (uInput46 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput46 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 3) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 4) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
								}
							}else if (uInput3== 1) {//winner path2
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 3 = " + sum);
								
								System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
								System.out.println("1. Fight villain\n2. Run away");
								int uInput4 = scanner.nextInt();
								if (uInput4 == 1) {
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									if (v4Attack <= hAttack && v4Defense <= hDefense) {
										System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
										System.out.println("Level completed in " + sum + " moves.");
										//Save hero level	
										PreparedStatement SQL2 = con.prepareStatement(query2);
										SQL2.setString(1, "%" + selectedHero + "%");
										SQL2.setString(2, "%" + tag + "%");
											
										SQL2.executeUpdate();
										//update hero XP
										int f = (int) Math.pow((1 - 1), 2);
										int xpFormula = 1 * 1000 + f * 450;
										
										PreparedStatement SQL1 = con.prepareStatement(query1);
										SQL1.setInt(1, xpFormula);
										SQL1.setString(2, "%" + selectedHero + "%");
										SQL1.setString(3, "%" + tag + "%");
											
										SQL1.executeUpdate();
										startGame(tag, selectedHero);
									} 
								} else if (uInput4== 2) {//after run away from joker
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 4 = " + sum);
									
									System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput44 = scanner.nextInt();
									if (uInput44 == 1) {//east a
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput45 = scanner.nextInt();
										if (uInput45 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput45 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput46 = scanner.nextInt();
										if (uInput46 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput46 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 3) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
									if (uInput44 == 4) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 5 = " + sum);
										System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										
										int uInput47 = scanner.nextInt();
										if (uInput47 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 6 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										}
										else if (uInput47 == 2) {
											System.out.println("Lose");
											Menu.menu_page(tag);
										}
									}
								}
							}
						}//AD
						} else if (uInput2 == 2) {//After running away from ivy
							moves = moves + 1;
							sum =  9 - moves;
							System.out.println("\nMoves left = 9 - 2 = " + sum);
							
							if (v2Attack <= hAttack || v2Defense < hDefense) {
								System.out.println("You have chosen to run away from " + v2 + ", you had the advantage... but chose to run away. What would you like to do next?.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
								int uInput3 = scanner.nextInt();
								if (uInput3 == 2) {//west
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 3 = " + sum);
									
									System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
									System.out.println("1. Fight villain\n2. Run away\n\n");
									int uInput4 = scanner.nextInt();
									if (uInput4 == 1) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("Looser");
										Menu.menu_page(tag);
									}
									else if (uInput4 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("Unfortunately you cannot out run " + v3 + ", your only option is to face him... try your best mate.");
										System.out.println("1. Fight villain\n\n");
										int uInput5 = scanner.nextInt();
										if (uInput5 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 5 = " + sum);
											
											System.out.println("Looser");
											Menu.menu_page(tag);
										}
									}	
								}
								else if (uInput3 == 3) {//North
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 3 = " + sum);
									
									System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
									System.out.println("1. Fight villain\n2. Run away\n\n");
									int uInput61 = scanner.nextInt();
									if (uInput61 == 1) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
									}
									else if (uInput61 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("Unfortunately you cannot out run " + v5 + ", your only option is to face him... try your best mate.");
										System.out.println("1. Fight villain\n\n");
										int uInput5 = scanner.nextInt();
										if (uInput5 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 5 = " + sum);
											
											System.out.println("Looser");
											Menu.menu_page(tag);
										}
									}
								}
								else if (uInput3 == 1) {//east
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 3 = " + sum);
									
									System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
									System.out.println("1. Fight villain\n2. Run away\n\n");
									int uInput6 = scanner.nextInt();
									if (uInput6 == 1) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("Looser");
										Menu.menu_page(tag);
									}
									else if (uInput6 == 2) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("Unfortunately you cannot out run " + v1 + ", your only option is to face him... try your best mate.");
										System.out.println("1. Fight villain\n\n");
										int uInput5 = scanner.nextInt();
										if (uInput5 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 5 = " + sum);
											
											System.out.println("Looser");
											Menu.menu_page(tag);
										}
									}
								}
								else if (uInput3 == 4) {//south
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 3 = " + sum);
									
									System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
									System.out.println("1. Fight villain\n2. Run away\n\n");
									int uInput6 = scanner.nextInt();
									if (uInput6 == 1) {
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 4 = " + sum);
										
										System.out.println("v6 is slightly stronger than you, hower it is still possible to defeat him... The Gods have found you worthy to receive LUCK.\n1. Use Luck\n2. Decline Luck");
										int uInput7 = scanner.nextInt();
										if (uInput7 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("You have defeated " + v6 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
											System.out.println("Level completed in " + sum + " moves.");
											//Save hero level	
											PreparedStatement SQL2 = con.prepareStatement(query2);
											SQL2.setString(1, "%" + selectedHero + "%");
											SQL2.setString(2, "%" + tag + "%");
												
											SQL2.executeUpdate();
											//update hero XP
											int f = (int) Math.pow((1 - 1), 2);
											int xpFormula = 1 * 1000 + f * 450;
											
											PreparedStatement SQL1 = con.prepareStatement(query1);
											SQL1.setInt(1, xpFormula);
											SQL1.setString(2, "%" + selectedHero + "%");
											SQL1.setString(3, "%" + tag + "%");
												
											SQL1.executeUpdate();
											startGame(tag, selectedHero);
										}
										else if (uInput6 == 2) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Unfortunately you cannot out run " + v6 + ", your only option is to face him... try your best mate.");
											System.out.println("1. Fight villain\n\n");
											int uInput5 = scanner.nextInt();
											if (uInput5 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have lost");
												System.out.println("Looser");
												Menu.menu_page(tag);
												}
											}
										}
										else {
											System.out.println("You have lost");
											System.out.println("Looser");
											Menu.menu_page(tag);
										}
									}
								}
							}
						} else if (uInput == 4) {//Big South
							System.out.println("You have stumbled accross the villain " + v2 + " with attack level of " + v2Attack + " and a defense level of " + v2Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
							System.out.println("1. Fight villain\n2. Run away\n\n");
							int uInput2 = scanner.nextInt();
								
							if (uInput2 == 1) {
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 2 = " + sum);
								
								if (v2Attack <= hAttack || v2Defense < hDefense) {
									System.out.println("You have defeated " + v2 + ". You are 1 - 2 right moves away from reaching the map boarders.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput3 = scanner.nextInt();
									if (uInput3 == 3) {//winner
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									} if (uInput3 == 4) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3 == 2) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3== 1) {//winner path2
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}//AD
								} else if (uInput2 == 2) {//After running away from ivy
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 2 = " + sum);
									
									if (v2Attack <= hAttack || v2Defense < hDefense) {
										System.out.println("You have chosen to run away from " + v2 + ", you had the advantage... but chose to run away. What would you like to do next?.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
										int uInput3 = scanner.nextInt();
										if (uInput3 == 2) {//west
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput4 = scanner.nextInt();
											if (uInput4 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput4 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v3 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}	
										}
										else if (uInput3 == 3) {//North
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput61 = scanner.nextInt();
											if (uInput61 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput61 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v5 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 1) {//east
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput6 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v1 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 4) {//south
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("v6 is slightly stronger than you, hower it is still possible to defeat him... The Gods have found you worthy to receive LUCK.\n1. Use Luck\n2. Decline Luck");
												int uInput7 = scanner.nextInt();
												if (uInput7 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("You have defeated " + v6 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
													System.out.println("Level completed in " + sum + " moves.");
													//Save hero level	
													PreparedStatement SQL2 = con.prepareStatement(query2);
													SQL2.setString(1, "%" + selectedHero + "%");
													SQL2.setString(2, "%" + tag + "%");
														
													SQL2.executeUpdate();
													//update hero XP
													int f = (int) Math.pow((1 - 1), 2);
													int xpFormula = 1 * 1000 + f * 450;
													
													PreparedStatement SQL1 = con.prepareStatement(query1);
													SQL1.setInt(1, xpFormula);
													SQL1.setString(2, "%" + selectedHero + "%");
													SQL1.setString(3, "%" + tag + "%");
														
													SQL1.executeUpdate();
													startGame(tag, selectedHero);
												}
												else if (uInput6 == 2) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("Unfortunately you cannot out run " + v6 + ", your only option is to face him... try your best mate.");
													System.out.println("1. Fight villain\n\n");
													int uInput5 = scanner.nextInt();
													if (uInput5 == 1) {
														moves = moves + 1;
														sum =  9 - moves;
														System.out.println("\nMoves left = 9 - 5 = " + sum);
														System.out.println("You have lost");
														Menu.menu_page(tag);
														}
													}
												}
												else {
													System.out.println("You have lost");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}else if (uInput == 3) {//Big North
							System.out.println("You have stumbled accross the villain " + v2 + " with attack level of " + v2Attack + " and a defense level of " + v2Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
							System.out.println("1. Fight villain\n2. Run away\n\n");
							int uInput2 = scanner.nextInt();
								
							if (uInput2 == 1) {
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 2 = " + sum);
								
								if (v2Attack <= hAttack || v2Defense < hDefense) {
									System.out.println("You have defeated " + v2 + ". You are 1 - 2 right moves away from reaching the map boarders.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput3 = scanner.nextInt();
									if (uInput3 == 3) {//winner
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									} if (uInput3 == 4) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3 == 2) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3== 1) {//winner path2
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}//AD
								} else if (uInput2 == 2) {//After running away from ivy
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 2 = " + sum);
									
									if (v2Attack <= hAttack || v2Defense < hDefense) {
										System.out.println("You have chosen to run away from " + v2 + ", you had the advantage... but chose to run away. What would you like to do next?.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
										int uInput3 = scanner.nextInt();
										if (uInput3 == 2) {//west
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput4 = scanner.nextInt();
											if (uInput4 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput4 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v3 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}	
										}
										else if (uInput3 == 3) {//North
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput61 = scanner.nextInt();
											if (uInput61 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput61 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v5 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 1) {//east
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput6 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v1 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 4) {//south
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("v6 is slightly stronger than you, hower it is still possible to defeat him... The Gods have found you worthy to receive LUCK.\n1. Use Luck\n2. Decline Luck");
												int uInput7 = scanner.nextInt();
												if (uInput7 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("You have defeated " + v6 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
													System.out.println("Level completed in " + sum + " moves.");
													//Save hero level	
													PreparedStatement SQL2 = con.prepareStatement(query2);
													SQL2.setString(1, "%" + selectedHero + "%");
													SQL2.setString(2, "%" + tag + "%");
														
													SQL2.executeUpdate();
													//update hero XP
													int f = (int) Math.pow((1 - 1), 2);
													int xpFormula = 1 * 1000 + f * 450;
													
													PreparedStatement SQL1 = con.prepareStatement(query1);
													SQL1.setInt(1, xpFormula);
													SQL1.setString(2, "%" + selectedHero + "%");
													SQL1.setString(3, "%" + tag + "%");
														
													SQL1.executeUpdate();
													startGame(tag, selectedHero);
												}
												else if (uInput6 == 2) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("Unfortunately you cannot out run " + v6 + ", your only option is to face him... try your best mate.");
													System.out.println("1. Fight villain\n\n");
													int uInput5 = scanner.nextInt();
													if (uInput5 == 1) {
														moves = moves + 1;
														sum =  9 - moves;
														System.out.println("\nMoves left = 9 - 5 = " + sum);
														System.out.println("You have lost");
														Menu.menu_page(tag);
														}
													}
												}
												else {
													System.out.println("You have lost");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}else if (uInput == 2) {//Big West
							System.out.println("You have stumbled accross the villain " + v2 + " with attack level of " + v2Attack + " and a defense level of " + v2Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
							System.out.println("1. Fight villain\n2. Run away\n\n");
							int uInput2 = scanner.nextInt();
								
							if (uInput2 == 1) {
								moves = moves + 1;
								sum =  9 - moves;
								System.out.println("\nMoves left = 9 - 2 = " + sum);
								
								if (v2Attack <= hAttack || v2Defense < hDefense) {
									System.out.println("You have defeated " + v2 + ". You are 1 - 2 right moves away from reaching the map boarders.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
									int uInput3 = scanner.nextInt();
									if (uInput3 == 3) {//winner
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									} if (uInput3 == 4) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3 == 2) {//Looser
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("Loose");
											Menu.menu_page(tag);
										} else if (uInput4== 2) {//after run away from madara
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}else if (uInput3== 1) {//winner path2
										moves = moves + 1;
										sum =  9 - moves;
										System.out.println("\nMoves left = 9 - 3 = " + sum);
										
										System.out.println("You have stumbled accross the villain " + v4 + " with attack level of " + v4Attack + " and a defense level of " + v4Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
										System.out.println("1. Fight villain\n2. Run away");
										int uInput4 = scanner.nextInt();
										if (uInput4 == 1) {
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											if (v4Attack <= hAttack && v4Defense <= hDefense) {
												System.out.println("You have defeated " + v4 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
												System.out.println("Level completed in " + sum + " moves.");
												//Save hero level	
												PreparedStatement SQL2 = con.prepareStatement(query2);
												SQL2.setString(1, "%" + selectedHero + "%");
												SQL2.setString(2, "%" + tag + "%");
													
												SQL2.executeUpdate();
												//update hero XP
												int f = (int) Math.pow((1 - 1), 2);
												int xpFormula = 1 * 1000 + f * 450;
												
												PreparedStatement SQL1 = con.prepareStatement(query1);
												SQL1.setInt(1, xpFormula);
												SQL1.setString(2, "%" + selectedHero + "%");
												SQL1.setString(3, "%" + tag + "%");
													
												SQL1.executeUpdate();
												startGame(tag, selectedHero);
											} 
										} else if (uInput4== 2) {//after run away from joker
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 4 = " + sum);
											
											System.out.println("1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
											int uInput44 = scanner.nextInt();
											if (uInput44 == 1) {//east a
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput45 = scanner.nextInt();
												if (uInput45 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput45 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput46 = scanner.nextInt();
												if (uInput46 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput46 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 3) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
											if (uInput44 == 4) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 5 = " + sum);
												System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense  + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
												System.out.println("1. Fight villain\n2. Run away");
												
												int uInput47 = scanner.nextInt();
												if (uInput47 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 6 = " + sum);
													
													System.out.println("Loose");
													Menu.menu_page(tag);
												}
												else if (uInput47 == 2) {
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}//AD
								} else if (uInput2 == 2) {//After running away from ivy
									moves = moves + 1;
									sum =  9 - moves;
									System.out.println("\nMoves left = 9 - 2 = " + sum);
									
									if (v2Attack <= hAttack || v2Defense < hDefense) {
										System.out.println("You have chosen to run away from " + v2 + ", you had the advantage... but chose to run away. What would you like to do next?.\n1. Move east\n2. Move west \n3. Move north\n4. Move south\n\n");
										int uInput3 = scanner.nextInt();
										if (uInput3 == 2) {//west
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v3 + " with attack level of " + v3Attack + " and a defense level of " + v3Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput4 = scanner.nextInt();
											if (uInput4 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput4 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v3 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}	
										}
										else if (uInput3 == 3) {//North
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v5 + " with attack level of " + v5Attack + " and a defense level of " + v5Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput61 = scanner.nextInt();
											if (uInput61 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput61 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v5 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 1) {//east
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v1 + " with attack level of " + v1Attack + " and a defense level of " + v1Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Lose");
												Menu.menu_page(tag);
											}
											else if (uInput6 == 2) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("Unfortunately you cannot out run " + v1 + ", your only option is to face him... try your best mate.");
												System.out.println("1. Fight villain\n\n");
												int uInput5 = scanner.nextInt();
												if (uInput5 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 5 = " + sum);
													
													System.out.println("Lose");
													Menu.menu_page(tag);
												}
											}
										}
										else if (uInput3 == 4) {//south
											moves = moves + 1;
											sum =  9 - moves;
											System.out.println("\nMoves left = 9 - 3 = " + sum);
											
											System.out.println("You have stumbled accross the villain " + v6 + " with attack level of " + v6Attack + " and a defense level of " + v6Defense + ".\nYour attack level is " + hAttack + " and your defense level is " + hDefense);
											System.out.println("1. Fight villain\n2. Run away\n\n");
											int uInput6 = scanner.nextInt();
											if (uInput6 == 1) {
												moves = moves + 1;
												sum =  9 - moves;
												System.out.println("\nMoves left = 9 - 4 = " + sum);
												
												System.out.println("v6 is slightly stronger than you, hower it is still possible to defeat him... The Gods have found you worthy to receive LUCK.\n1. Use Luck\n2. Decline Luck");
												int uInput7 = scanner.nextInt();
												if (uInput7 == 1) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("You have defeated " + v6 + " and have reached the map boarders.\nYou will now be teleported to level 2...");
													System.out.println("Level completed in " + sum + " moves.");
													//Save hero level	
													PreparedStatement SQL2 = con.prepareStatement(query2);
													SQL2.setString(1, "%" + selectedHero + "%");
													SQL2.setString(2, "%" + tag + "%");
														
													SQL2.executeUpdate();
													//update hero XP
													int f = (int) Math.pow((1 - 1), 2);
													int xpFormula = 1 * 1000 + f * 450;
													
													PreparedStatement SQL1 = con.prepareStatement(query1);
													SQL1.setInt(1, xpFormula);
													SQL1.setString(2, "%" + selectedHero + "%");
													SQL1.setString(3, "%" + tag + "%");
														
													SQL1.executeUpdate();
													startGame(tag, selectedHero);
												}
												else if (uInput6 == 2) {
													moves = moves + 1;
													sum =  9 - moves;
													System.out.println("\nMoves left = 9 - 4 = " + sum);
													
													System.out.println("Unfortunately you cannot out run " + v6 + ", your only option is to face him... try your best mate.");
													System.out.println("1. Fight villain\n\n");
													int uInput5 = scanner.nextInt();
													if (uInput5 == 1) {
														moves = moves + 1;
														sum =  9 - moves;
														System.out.println("\nMoves left = 9 - 5 = " + sum);
														System.out.println("You have lost");
														Menu.menu_page(tag);
														}
													}
												}
												else {
													System.out.println("You have lost");
													Menu.menu_page(tag);
												}
											}
										}
									}
								}
					}
			}catch (SQLException e) {
				e.printStackTrace();
				}
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
