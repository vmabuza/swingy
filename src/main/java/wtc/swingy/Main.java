package wtc.swingy;

import wtc.swingy.database.ConnString;
import wtc.swingy.database.GameDB;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Main {
	public static Configuration<?> config = Validation.byDefaultProvider().configure();
	public static ValidatorFactory factory = config.buildValidatorFactory();

	public static volatile boolean gameMode;

	private static void		printUsage() {
		System.out.println("Usage: java -jar swingy.jar [console || gui || delete]");
	}

	public static void		printError(String message) {
		System.err.println(message);
		System.exit(2);
	}

	public static void		main(String[] args) {
		ConnString.createDB();
		GameDB.createHeroesTable();

		if (args.length == 1 && args[0].equals("console")) {
			gameMode = false;
			Game.mainLoop();
		} else if (args.length == 1 && args[0].equals("gui")) {
			gameMode = true;
			Game.mainLoop();
		} else
			printUsage();
		factory.close();
	}
}
