package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameState {
	public static void createGameState() {
		
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			String createTable = "CREATE TABLE gameState (\r\n" + 
					"    stateID INT PRIMARY KEY IDENTITY,\r\n" + 
					"    owner VARCHAR (100) NOT NULL,\r\n" +  
					"	heroName VARCHAR (100) NOT NULL,\r\n" + 
					"	levelNum INT NOT NULL,\r\n" + 
					")";
			
			PreparedStatement SQLsave = con.prepareStatement(createTable);
			SQLsave.executeUpdate();
			//			String createVillain = "INSERT INTO swingy.dbo.villains (villainName, villainAttack, villainDefense, villainLevel, experience, artifact) "
//					+ "VALUES ('Poison Ivy', 1, 1, 1, 1, ''), ('Joker', 2, 2, 1, 1, 'Joker Card')";
			

					
//			PreparedStatement SQL2save = con.prepareStatement(createVillain);
//			SQL2save.executeUpdate();
		}catch (SQLException e) {
            }
	}
}
