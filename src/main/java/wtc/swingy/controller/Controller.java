package wtc.swingy.controller;

import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.Main;
import wtc.swingy.model.hero.HeroClasses;
import wtc.swingy.model.map.Map;
import wtc.swingy.model.races.Dbz;
import wtc.swingy.model.races.Naruto;
import wtc.swingy.model.races.Eater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
	static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int yesNoInput() {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("y"))
					return 0;
				else if (s.toLowerCase().equals("no") || s.toLowerCase().equals("n"))
					return 1;
				else if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return 2;
				}
			}
		} else
			return Loops.view.getSwingGui().getCheckLoop();
		return 2;
	}

	private static void changeInterface() {
		Main.gameMode = !Main.gameMode;
		Loops.view.getSwingGui().setVisibility(Main.gameMode);
	}

	public static boolean getInput() {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return false;
				} else
					return true;
			}
		} else {
			return Loops.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static TypeOfCharacter chooseHero(ArrayList<TypeOfCharacter> players) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				for (TypeOfCharacter player : players) {
					if (player != null) {
						if (player.getName().toLowerCase().equals(s))
							return player;
					}
				}
				if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return null;
				} else if (s.toLowerCase().equals("create")) {
					return new TypeOfCharacter();
				}
			}
		} else {
			switch (Loops.view.getSwingGui().getCheckLoop()) {
				case 0:
					return null;
				case 1:
					return players.get(0);
				case 2:
					return players.get(1);
				case 3:
					return players.get(2);
				case 4:
					return players.get(3);
				case 5:
					return new TypeOfCharacter();
			}
		}
		return null;
	}

	public static boolean getRace(TypeOfCharacter player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("eater")) {
					player.setPlayerRace(new Eater());
					return true;
				} else if (s.toLowerCase().equals("naruto")) {
					player.setPlayerRace(new Naruto());
					return true;
				} else if (s.toLowerCase().equals("dbz")) {
					player.setPlayerRace(new Dbz());
					return true;
				} else if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return false;
				}
			}
		} else {
			return Loops.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static boolean getClass(TypeOfCharacter player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("aragami")) {
					player.setPlayerClass(HeroClasses.ARAGAMI);
					return true;
				} else if (s.toLowerCase().equals("ninja")) {
					player.setPlayerClass(HeroClasses.NINJA);
					return true;
				} else if (s.toLowerCase().equals("saiyan")) {
					player.setPlayerClass(HeroClasses.SAIYAN);
					return true;
				} else if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return false;
				}
			}
		} else {
			return Loops.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static boolean	playerMovement(TypeOfCharacter player, Map map) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("west") || s.toLowerCase().equals("w")) {
					player.incPosX(map);
				} else if (s.toLowerCase().equals("east") || s.toLowerCase().equals("e")) {
					player.decPosX();
				} else if (s.toLowerCase().equals("north") || s.toLowerCase().equals("n")) {
					player.decPosY();
				} else if (s.toLowerCase().equals("south") || s.toLowerCase().equals("s")) {
					player.incPosY(map);
				} else if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
				} else return !s.toLowerCase().equals("info") && !s.toLowerCase().equals("i");
			}
		} else
			return Loops.view.getSwingGui().isExitLoop();
		return true;
	}

	public static boolean getName(TypeOfCharacter player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("change") || s.toLowerCase().equals("c")) {
					changeInterface();
					return false;
				} else
					player.setName(s);
			}
		}
		return true;
	}
}
