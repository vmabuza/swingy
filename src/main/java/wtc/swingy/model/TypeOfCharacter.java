package wtc.swingy.model;

import wtc.swingy.model.armor.AbstractArmor;
import wtc.swingy.model.helmets.AbstractHelmet;
import wtc.swingy.model.hero.HeroClasses;
import wtc.swingy.model.map.Map;
import wtc.swingy.model.races.AbstractRace;
import wtc.swingy.model.weapons.AbstractWeapon;
import wtc.swingy.model.weapons.Fists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Random;

public class TypeOfCharacter {
	@NotNull(message = "Name must not be null.")
	@Size(min=3, max=10, message = "Name size is not acceptable.")
	private String			name;
	private HeroClasses playerClass;
	private AbstractRace playerRace;
	private	int				healthPoints;
	private	int				maxHealthPoints;
	private int				expPoints;
	private int				posX;
	private int				posY;
	private int				lastPosX;
	private int				lastPosY;
	private int				level;
	private int				attack;
	private AbstractHelmet helmet;
	private AbstractArmor armor;
	private AbstractWeapon weapon;

	public TypeOfCharacter() {
		name = null;
		healthPoints = 100;
		maxHealthPoints = 100;
		expPoints = 0;
		level = 0;
		attack = 0;
		playerRace = null;
		playerClass = null;
		helmet = null;
		armor = null;
		weapon = new Fists();
	}

	public void incPosX(Map map) {
		if (posX + 1 < map.getSize()) {
			lastPosX = posX;
			lastPosY = posY;
			posX++;
		}
	}

	public void incPosY(Map map) {
		if (posY + 1 < map.getSize()) {
			lastPosX = posX;
			lastPosY = posY;
			posY++;
		}
	}

	public void decPosX() {
		if (posX - 1 >= 0) {
			lastPosX = posX;
			lastPosY = posY;
			posX--;
		}
	}

	public void decPosY() {
		if (posY - 1 >= 0) {
			lastPosX = posX;
			lastPosY = posY;
			posY--;
		}
	}

	private void	checkDamage(int amount) {
		int luckAmount = new Random().nextInt((level + 1) * 10);
		amount -= (luckAmount + playerRace.getPlusDefence());
		if (amount <= 0)
			return;
		if (healthPoints - amount < 0)
			healthPoints = 0;
		else
			healthPoints -= amount;
	}

	private void	takeDamage(int amount) {
		if (armor != null) {
			if (armor.getDefence() < amount)
				checkDamage(amount);
		} else
			checkDamage(amount);
	}

	public void		attack(TypeOfCharacter enemy) {
		int amount = attack + playerRace.getPlusAttack();
		if (weapon != null)
			amount += weapon.getAttack();
		enemy.takeDamage(amount);
	}

	public void		addExpPoints(int amount, Map map) {
		expPoints += amount;
		checkLevelUp(map);
	}

	private void	checkLevelUp(Map map) {
		if (expPoints >= ((level + 1) * 1000) + (int)Math.pow(level, 2) * 450) {
			level++;
			healthPoints = level * 50 + 100;
			maxHealthPoints = healthPoints;
			map.expandMap(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroClasses getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(HeroClasses playerClass) {
		this.playerClass = playerClass;
	}

	public AbstractRace getPlayerRace() {
		return playerRace;
	}

	public void setPlayerRace(AbstractRace playerRace) {
		this.playerRace = playerRace;
		attack += playerRace.getPlusAttack();
		healthPoints += playerRace.getPlusHP();
		maxHealthPoints = healthPoints;
	}

	public void setPlayerRaceDefault(AbstractRace playerRace) {
		this.playerRace = playerRace;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getExpPoints() {
		return expPoints;
	}

	public void setExpPoints(int expPoints) {
		this.expPoints = expPoints;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
		this.lastPosX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
		this.lastPosY = posY;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttack() {
		if (weapon != null)
			return attack + weapon.getAttack();
		else
			return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public AbstractHelmet getHelmet() {
		return helmet;
	}

	public void setHelmet(AbstractHelmet helmet) {
		this.helmet = helmet;
		healthPoints += helmet.getIncHP();
		maxHealthPoints += helmet.getIncHP();
	}

	public void setHelmetDefault(AbstractHelmet helmet) {
		this.helmet = helmet;
	}

	public int getDefence() {
		if (armor != null)
			return armor.getDefence() + playerRace.getPlusDefence();
		return playerRace.getPlusDefence();
	}

	public AbstractArmor getArmor() {
		return armor;
	}

	public void setArmor(AbstractArmor armor) {
		this.armor = armor;
	}

	public AbstractWeapon getWeapon() {
		return weapon;
	}

	public void setWeapon(AbstractWeapon weapon) {
		this.weapon = weapon;
	}

	public int getLastPosX() {
		return lastPosX;
	}

	public int getLastPosY() {
		return lastPosY;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}
}
