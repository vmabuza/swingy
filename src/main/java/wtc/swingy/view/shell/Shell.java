package wtc.swingy.view.shell;

import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.model.map.Map;
import wtc.swingy.view.UI;

import java.util.ArrayList;

public class Shell implements UI {
	private TypeOfCharacter player;
	private Map map;

	public Shell() {}

	public void setPlayer(TypeOfCharacter player) {
		this.player = player;
	}

	public void drawChoosePlayer(ArrayList<TypeOfCharacter> players) {

		String playerNames[] = new String[4];
		for (int i = 0; i < 4; ++i) {
			if (players.get(i) == null)
				playerNames[i] = "Empty";
			else
				playerNames[i] = players.get(i).getName();
		}
		System.out.println("Type the name of your player in lower case to choose an existing player, or type \"create\" to create a new one.\n\n");
		System.out.println(playerNames[0]);
		System.out.println(playerNames[1]);
		System.out.println(playerNames[2]);
		System.out.println(playerNames[3]);
		System.out.println("\n\n");
	}

	public void drawMenu() {

		System.out.println("WELCOME TO SWINGY\n\nPRESS ENTER TO CONTINUE");
	}

	public void drawChooseRace() {

		System.out.println( "\n\nEater       Naruto       	DBZ"   +
				"\nAttack +        HP +5      Defence +5  	" +
				"\nDefence +1    Attack +5\n" +
				"HP +2");
	}

	public void drawChooseClass() {

		System.out.println(
				"\n\nCHOOSE CLASS\n"
						+ "ARAGAMI               NINJA               SAIYAN   \n"
		);
	}

	public void drawChooseName() {

		System.out.println("\n\nENTER PLAYER NAME");
	}

	public void drawMap() {

		System.out.print("O");
		for (int i = 0; i < map.getSize() * 2; ++i)
			System.out.print("0");
		System.out.println("0");
		for (int i = 0; i < map.getSize(); ++i) {
			System.out.print("0");
			for (int j = 0; j < map.getSize(); ++j) {
				if (player.getPosX() == j && player.getPosY() == i)
					System.out.print("P ");
				else
					System.out.print(map.getMapCell(i, j) + " ");
			}
			System.out.println("0");
		}
		System.out.print("0");
		for (int i = 0; i < map.getSize() * 2; ++i)
			System.out.print("0");
		System.out.println("0");
		System.out.println("Type commands: 'w', 'e', 'n', 's' to move, or 'info' for player information.");
	}

	public void drawCheckPlayerWantsToBattle() {
		System.out.println("Do you want to start the battle?  \n");
		System.out.println("yes, no?");
	}

	public void drawDie() {

		System.out.println("YOU DIED");
		System.out.println("Press enter to continue");
	}

	public void drawWin() {
		System.out.println("YOU WIN");
		System.out.println("Press enter to continue");
	}

	public void drawPlayer() {

		int nextexp = ((player.getLevel() + 1) * 1000) + (int)Math.pow(player.getLevel(), 2) * 450;
		System.out.printf(    "\n\n  Name:     %20s            \n", player.getName());
		System.out.printf(    "  Race:     %20s             \n", player.getPlayerRace().getName());
		System.out.printf(    "  Class:    %20s             \n", player.getPlayerClass());
		System.out.printf(    "  HP:       %20s / %3s       \n", player.getHealthPoints(), player.getMaxHealthPoints());
		System.out.printf(    "  Level:    %20s             \n", player.getLevel());
		System.out.printf(    "  Exp:      %20s /%5s      \n", player.getExpPoints(), player.getLevel() == 5 ? "inf" : nextexp);
		System.out.printf(    "  Attack:   %20s             \n", player.getAttack());
		System.out.printf(    "  Defence:  %20s             \n", player.getDefence());
		System.out.printf(    "  Weapon:   %20s            \n", player.getWeapon() != null ? player.getWeapon().getName() : "-");
		System.out.printf(    "  Armor:    %20s             \n", player.getArmor() != null ? player.getArmor().getName() : "-");
		System.out.printf(    "  Helmet:   %20s             \n\n\n", player.getHelmet() != null ? player.getHelmet().getName() : "-");
		System.out.println("Press enter to continue");
	}

	public int drawGetPrize(String type) {

		System.out.printf(    "Do you want to keep:      \n");
		System.out.printf(    "     %10s?        \n", type);
		System.out.println("yes, no?");
		return 0;
	}

	public void drawGetNothing() {
		System.out.println(   " You got nothing :(  \n");
		System.out.println("Press enter to continue");
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
