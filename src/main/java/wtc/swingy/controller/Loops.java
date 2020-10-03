package wtc.swingy.controller;

import wtc.swingy.Main;
import wtc.swingy.database.GameDB;
import wtc.swingy.view.gui.FrontUI;
import wtc.swingy.model.map.Map;
import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.view.shell.Shell;
import wtc.swingy.view.View;

import javax.validation.*;
import java.util.ArrayList;
import java.util.Set;

public class Loops {
	static private TypeOfCharacter hero;
	static public View view;

	public static void mainLoop() {
		menuLoop();
		if (gameLoop()) {
			if (!Main.gameMode) {
				view.getShellGui().drawWin();
			} else
				view.getSwingGui().drawWin();
		} else if (!Main.gameMode) {
			view.getShellGui().drawDie();
		} else
			view.getSwingGui().drawDie();
		if (!Main.gameMode)
			Controller.getInput();
		System.exit(0);
	}

	private static boolean	gameLoop() {
		Map map = new Map(hero);
		view.getSwingGui().setMap(map);
		view.getShellGui().setMap(map);
		view.getSwingGui().drawMap();
		while (true) {
			map.checkEmptyMap();
			if (!Main.gameMode)
				view.getShellGui().drawMap();
			if (Controller.playerMovement(hero, map)) {
				if (map.getMapCell(hero.getPosY(), hero.getPosX()) == 'E')
					if (!BattleHandler.startFight(hero, map))
						return false;
					else
						map.setMapCell(hero.getPosY(), hero.getPosX(), '.');
			}
			else {
				if (!Main.gameMode) {
					view.getShellGui().drawPlayer();
					Controller.getInput();
				}
			}
			if (hero.getLevel() == 5)
				break;
			view.getSwingGui().updateMap(map);
		}
		return true;
	}

	private static void menuLoop() {
		hero = null;
		view = new View(new FrontUI(), new Shell());
		view.getSwingGui().drawMenu();
		view.getSwingGui().setVisibility(Main.gameMode);
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawMenu();
			if (Controller.getInput())
				break;
		}
		ArrayList<TypeOfCharacter> players = GameDB.getDB();
		view.getSwingGui().drawChoosePlayer(players);
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChoosePlayer(players);
			TypeOfCharacter t = Controller.chooseHero(players);
			if (t != null) {
				if (t.getName() == null) {
					hero = t;
					view.getShellGui().setPlayer(hero);
					view.getSwingGui().setPlayer(hero);
					break;
				} else {
					hero = t;
					view.getShellGui().setPlayer(hero);
					view.getSwingGui().setPlayer(hero);
					return;
				}
			}
		}
		view.getSwingGui().drawChooseRace();
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChooseRace();
			if (Controller.getRace(hero))
				break;
		}
		view.getSwingGui().drawChooseClass();
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChooseClass();
			if (Controller.getClass(hero))
				break;
		}
		view.getSwingGui().drawChooseName();
		Set<ConstraintViolation<TypeOfCharacter>> violations = null;
		while (true) {
			Validator validator = Main.factory.getValidator();
			if (!Main.gameMode)
				view.getShellGui().drawChooseName();
			if (violations != null && !Main.gameMode)
				for (ConstraintViolation<TypeOfCharacter> violation : violations)
					System.out.println(violation.getMessage());
			Controller.getName(hero);
			validator.validate(hero);
			violations = validator.validate(hero);
			if (violations.size() == 0 || view.getSwingGui().isExitLoop())
				break;
		}
		GameDB.insertHero(hero);
	}
}
