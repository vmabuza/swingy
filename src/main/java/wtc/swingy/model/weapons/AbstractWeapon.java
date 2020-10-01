package wtc.swingy.model.weapons;

import wtc.swingy.model.InventoryObjectInterface;
import wtc.swingy.model.InventoryObjects;

public abstract class AbstractWeapon implements InventoryObjectInterface {
	private int					attack;
	private String				name;
	private InventoryObjects	type;

	public AbstractWeapon() {
		type = InventoryObjects.WEAPON;
	}

	public int getAttack() {
		return attack;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getName() {
		return name;
	}

	public InventoryObjects returnThis() {
		return type;
	}
}
