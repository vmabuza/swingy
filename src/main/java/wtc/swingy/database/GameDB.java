package wtc.swingy.database;

import wtc.swingy.Main;
import wtc.swingy.model.Creature;
import wtc.swingy.model.PlayerClasses;
import wtc.swingy.model.races.Naruto;
import wtc.swingy.model.races.DBZ;
import wtc.swingy.model.races.God_Eater;

import java.sql.*;
import java.util.ArrayList;

public class GameDB {
	
	private static final String createTable = "IF NOT EXISTS (\r\n" + 
			"        SELECT 1\r\n" + 
			"        FROM sys.tables\r\n" + 
			"        WHERE name = 'players'\r\n" + 
			"            AND type = 'U'\r\n" + 
			"        )\r\n" + 
			"BEGIN\r\n" + 
			"CREATE TABLE players (\r\n" + 
			"   id INT PRIMARY KEY IDENTITY,\r\n" + 
			"   name VARCHAR (100) NOT NULL,\r\n" +
			"   race VARCHAR (100) NOT NULL,\r\n" + 
			"	class VARCHAR (100) NOT NULL,\r\n" + 
			"	level INT DEFAULT 0 NOT NULL,\r\n" + 
			"	hp INT DEFAULT 0 NOT NULL,\r\n" +
			"	max_hp INT DEFAULT 0 NOT NULL,\r\n" + 
			"	xp INT DEFAULT 0 NOT NULL,\r\n" + 
			"	attack INT DEFAULT 7 NOT NULL,\r\n" +
			"   weapon VARCHAR (100) NOT NULL,\r\n" +
			"   armor VARCHAR (100) NOT NULL,\r\n" +
			"   helmet VARCHAR (100) NOT NULL,\r\n" +  
			"	owner VARCHAR (100) NULL,\r\n" + 
			")\r\n" + 
			"END";
	
	private static final String InsertPlayer = "INSERT INTO swingy.dbo.players (name, race, class, level, hp, max_hp, xp, attack, weapon, armor, helmet) "
	+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GetPlayers = "SELECT * FROM swingy.dbo.players";
	
	private static final String Update_Player = "UPDATE swingy.dbo.players SET level = ?, hp = ?, max_hp = ?, xp = ?, attack = ?, weapon = ?, armor = ?, helmet = ? WHERE name = ?";

	//players table
	public static void createHeroesTable() {
	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
	
	PreparedStatement SQLsave = con.prepareStatement(createTable);
	
	SQLsave.executeUpdate();
	
	}catch (SQLException e) {}
	}
	
	//Unique
	private static boolean isUniquePlayer(Creature player) {
	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
	
	PreparedStatement SQLsave = con.prepareStatement(GetPlayers);
	ResultSet resultSet = SQLsave.executeQuery();
	
	while (resultSet.next())
		if (player.getName().equals(resultSet.getString("name")))
			return false;
	}catch (SQLException e) {}
	return true;
	}
	
	public static void insertHero(Creature player) {
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			if (!isUniquePlayer(player))
				Main.printError("Player already exist.");
			else {
				PreparedStatement preparedStatement = con.prepareStatement(InsertPlayer);
				preparedStatement.setString(1, player.getName());
				preparedStatement.setString(2, player.getPlayerRace().getName());
				preparedStatement.setString(3, player.getPlayerClass().name());
				preparedStatement.setInt(4, player.getLevel());
				preparedStatement.setInt(5, player.getHealthPoints());
				preparedStatement.setInt(6, player.getMaxHealthPoints());
	
				preparedStatement.setInt(7, player.getExpPoints());
				preparedStatement.setInt(8, player.getAttack());
				
				if (player.getWeapon() != null)
					preparedStatement.setString(9, player.getWeapon().getName());
				else
					preparedStatement.setString(9, "-");
				if (player.getArmor() != null)
					preparedStatement.setString(10, player.getArmor().getName());
				else
					preparedStatement.setString(10, "-");
				if (player.getHelmet() != null)
					preparedStatement.setString(11, player.getHelmet().getName());
				else
					preparedStatement.setString(11, "-");
				preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				Main.printError(e.getMessage());
			}
		}
	
	public static ArrayList<Creature> getDB() {
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			ArrayList<Creature> players = new ArrayList<>();
	
			PreparedStatement SQL = con.prepareStatement(GetPlayers);
			ResultSet resultSet = SQL.executeQuery();
	
			while (resultSet.next()) {
				Creature player = new Creature();
				switch (resultSet.getString("race").toLowerCase()) {
				case "god_eater":
					player.setPlayerRaceDefault(new God_Eater());
					break;
				case "dbz":   
					player.setPlayerRaceDefault(new DBZ());
					break;
				case "naruto":
					player.setPlayerRaceDefault(new Naruto());
					break;
					}
				switch (resultSet.getString("class").toLowerCase()) {
				case "fire":
					player.setPlayerClass(PlayerClasses.FIRE);
					break;
				case "earth":
					player.setPlayerClass(PlayerClasses.EARTH);
					break;
				case "water":
					player.setPlayerClass(PlayerClasses.WATER);
					break;
				}
				players.add(player);
			}
			for (int i = players.size(); i < 4; ++i)
				players.add(null);
			return players;
		} catch (SQLException e) {}
	return null;
	}
	
	public static void updateHero(Creature player) {
	try (Connection con = DriverManager.getConnection(ConnString.conn());) {
	
	PreparedStatement preparedStatement = con.prepareStatement(Update_Player);
	preparedStatement.setInt(1, player.getLevel());
	preparedStatement.setInt(2, player.getHealthPoints());
	preparedStatement.setInt(3, player.getMaxHealthPoints());
	preparedStatement.setInt(4, player.getExpPoints());
	preparedStatement.setInt(5, player.getAttack());
	if (player.getWeapon() != null)
		preparedStatement.setString(6, player.getWeapon().getName());
	else
		preparedStatement.setString(6, "-");
	if (player.getArmor() != null)
		preparedStatement.setString(7, player.getArmor().getName());
	else
		preparedStatement.setString(7, "-");
	if (player.getHelmet() != null)
		preparedStatement.setString(8, player.getHelmet().getName());
	else
		preparedStatement.setString(8, "-");
		preparedStatement.setString(9, player.getName());
		preparedStatement.executeUpdate();
	} catch (SQLException e) {
		Main.printError(e.getMessage());
	}
	}
}
