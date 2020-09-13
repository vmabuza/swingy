package dbConnection;

public class ConnString {
	public static String conn() {
		String JDBC_URL = "jdbc:sqlserver://MSI-BEAST:1433;databaseName=swingy;user=dev;password=Joker@thejoke";
		return JDBC_URL;
	}
}
