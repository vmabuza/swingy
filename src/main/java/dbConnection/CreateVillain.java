package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateVillain {

	public static void createVillain() {
		try (Connection con = DriverManager.getConnection(ConnString.conn());) {
			
//			String v1Name = "Rugal";
//			String v2Name = "Joker";
//			String v3Name = "Alien";
//			String v4Name = "The evil queen";
//			String v5Name = "Terminator";
//			String v6Name = "Zamasu";
//			String v7Name = "Friezza";
//			String v1Name = "Poison Ivy";
			String createTable = "CREATE TABLE villains (\r\n" + 
					"    villainID INT PRIMARY KEY IDENTITY,\r\n" + 
					"    villainName VARCHAR (100) NOT NULL,\r\n" +  
					"	villainAttack INT NOT NULL,\r\n" + 
					"	villainDefense INT NOT NULL,\r\n" + 
					"	villainLevel INT DEFAULT 2 NOT NULL,\r\n" + 
					"	experience INT DEFAULT 1 NOT NULL,\r\n" + 
					"	artifact VARCHAR (100) NOT NULL,\r\n" + 
					")";
			
			String createVillain = "INSERT INTO swingy.dbo.villains (villainName, villainAttack, villainDefense, villainLevel, experience, artifact) "
					+ "VALUES ('Poison Ivy', 1, 1, 1, 1, ''), ('Joker', 2, 2, 1, 1, 'Joker Card')";
			
			PreparedStatement SQLsave = con.prepareStatement(createTable);
	
//			SQLsave.setString(1, userName);
//			SQLsave.setString(2, passWord);
			
			SQLsave.executeUpdate();
			
			
			PreparedStatement SQL2save = con.prepareStatement(createVillain);
			SQL2save.executeUpdate();
		}catch (SQLException e) {
            }
	}
}
