package wtc.swingy.controller;

import wtc.swingy.controller.Loops;
import wtc.swingy.Main;
import wtc.swingy.database.GameDB;
import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.model.InventoryObjectInterface;
import wtc.swingy.model.hero.HeroClasses;
import wtc.swingy.controller.Controller;
import wtc.swingy.model.armor.*;
import wtc.swingy.model.villain.Villain;
import wtc.swingy.model.helmets.*;
import wtc.swingy.model.map.Map;
import wtc.swingy.model.weapons.*;

import java.util.Random;

public class BattleHandler {

	static private boolean checkPlayerWantsToBattle() {
		while (true) {
			if (!Main.gameMode)
				Loops.view.getShellGui().drawCheckPlayerWantsToBattle();
			int check = Controller.yesNoInput();
			if (check == 0)
				return true;
			else if (check == 1)
				return new Random().nextBoolean();
		}
	}

	static private InventoryObjectInterface getWeaponPrize(int lvl, HeroClasses pclass) {
		switch (lvl) {
			case 0:
				if (pclass == HeroClasses.ARAGAMI)
					return new Sword();
				else if (pclass == HeroClasses.NINJA)
					return new SteelSword();
				else if (pclass == HeroClasses.SAIYAN)
					return new DiamondSword();
				break;
			case 1:
				if (pclass == HeroClasses.ARAGAMI)
					return new BronzeSword();
				else if (pclass == HeroClasses.NINJA)
					return new BronzeShield();
				else if (pclass == HeroClasses.SAIYAN)
					return new BronzeShield();
				break;
			case 2:
				if (pclass == HeroClasses.ARAGAMI)
					return new SteelSword();
				else if (pclass == HeroClasses.NINJA)
					return new SteelShield();
				else if (pclass == HeroClasses.SAIYAN)
					return new SteelShield();
				break;
			case 3:
				if (pclass == HeroClasses.ARAGAMI)
					return new Fists();
				else if (pclass == HeroClasses.NINJA)
					return new Knife();
				else if (pclass == HeroClasses.SAIYAN)
					return new Knife();
				break;
			case 4:
				if (pclass == HeroClasses.ARAGAMI)
					return new DiamondSword();
				else if (pclass == HeroClasses.NINJA)
					return new DiamondShell();
				else if (pclass == HeroClasses.SAIYAN)
					return new DiamondShell();
				break;
			case 5:
				if (pclass == HeroClasses.ARAGAMI)
					return new Sword();
				else if (pclass == HeroClasses.NINJA)
					return new SuperShell();
				else if (pclass == HeroClasses.SAIYAN)
					return new SuperShell();
				break;
		}
		return null;
	}

	static private void getPrize(TypeOfCharacter player, TypeOfCharacter enemy) {
		InventoryObjectInterface artifact = null;
		Random random = new Random();
		if (random.nextInt(20) == 0) {
			if (Main.gameMode)
				Loops.view.getSwingGui().drawGetNothing();
			if (!Main.gameMode) {
				Loops.view.getShellGui().drawGetNothing();
				Controller.getInput();
			}
			return;
		}
		int rand = random.nextInt(6);
		switch (enemy.getLevel()) {
			case 0:
				if (rand == 0)
					artifact = getWeaponPrize(0, player.getPlayerClass());
				else if (rand == 1)
					artifact = new WoodShield();
				else
					artifact = new WoodHelmet();
				break;
			case 1:
				if (rand == 0)
					artifact = getWeaponPrize(1, player.getPlayerClass());
				else if (rand == 1)
					artifact = new BronzeShield();
				else
					artifact = new BronzeHelmet();
				break;
			case 2:
				if (rand == 0)
					artifact = getWeaponPrize(2, player.getPlayerClass());
				else if (rand == 1)
					artifact = new SteelShield();
				else
					artifact = new SteelHelmet();
				break;
			case 3:
				if (rand == 0)
					artifact = getWeaponPrize(3, player.getPlayerClass());
				else if (rand == 1)
					artifact = new DragonShell();
				else
					artifact = new DragonHelmet();
				break;
			case 4:
				if (rand == 0)
					artifact = getWeaponPrize(4, player.getPlayerClass());
				else if (rand == 1)
					artifact = new DiamondShell();
				else
					artifact = new DiamondHelmet();
				break;
			case 5:
				if (rand == 0)
					artifact = getWeaponPrize(5, player.getPlayerClass());
				else if (rand == 1)
					artifact = new SuperShell();
				else
					artifact = new SuperHelmet();
				break;
		}
		if (artifact != null) {
			while (true) {
				int check;
				if (!Main.gameMode) {
					Loops.view.getShellGui().drawGetPrize(artifact.returnThis().toString());
					check = Controller.yesNoInput();
				} else
					check = Loops.view.getSwingGui().drawGetPrize(artifact.returnThis().toString());
				if (check == 0)
					switch (artifact.returnThis()) {
						case ARMOR:
							player.setArmor((AbstractArmor) artifact);
							return;
						case HELMET:
							player.setHelmet((AbstractHelmet) artifact);
							return;
						case WEAPON:
							player.setWeapon((AbstractWeapon) artifact);
							return;
					}
				else if (check == 1)
					return;
			}
		}
	}

	static private boolean gameRandomizer(TypeOfCharacter player, Map map) {
		TypeOfCharacter enemy = new Villain(player);
		while (true) {
			if (player.getHealthPoints() == 0)
				return false;
			player.attack(enemy);
			if (enemy.getHealthPoints() == 0) {
				player.setHealthPoints(player.getMaxHealthPoints());
				player.addExpPoints(enemy.getLevel() * 300 + 200, map);
				getPrize(player, enemy);
				GameDB.updateHero(player);
				return true;
			}
			enemy.attack(player);
		}
	}

	static public boolean startFight(TypeOfCharacter player, Map map) {
		Loops.view.getSwingGui().drawCheckPlayerWantsToBattle();
		if (!checkPlayerWantsToBattle()) {
			player.setPosX(player.getLastPosX());
			player.setPosY(player.getLastPosY());
			Loops.view.getSwingGui().drawMap();
			return true;
		}
		Loops.view.getSwingGui().drawMap();
		return gameRandomizer(player, map);
	}
}
