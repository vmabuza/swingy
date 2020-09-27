package wtc.swingy;

import wtc.swingy.database.ConnString;

public class Main {


	public static volatile boolean gameMode;

	private static void		printUsage() {
		System.out.println("Usage: java -jar swingy.jar [console OR gui ]");
	}

	public static void		printError(String message) {
		System.err.println(message);
		System.exit(2);
	}

	public static void		main(String[] args) {
		ConnString.createDB();
	
		if (args.length == 1 && args[0].equals("console")) {
			gameMode = false;
			Game.iterateMain ();
		} else (args.length == 1 && args[0].equals("gui")) {
			gameMode = true;
			Game.iterateMain();
		}
	}
}