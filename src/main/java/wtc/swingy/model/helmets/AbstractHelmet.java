package wtc.swingy.model.helmets;

import wtc.swingy.model.InventoryObjectInterface;
import wtc.swingy.model.InventoryObjects;

public abstract class AbstractHelmet implements InventoryObjectInterface {
	private int					incHP;
	private String				name;
	private InventoryObjects	type;

	public AbstractHelmet() {
		type = InventoryObjects.HELMET;
	}

	public void setIncHP(int incHP) {
		this.incHP = incHP;
	}

	public int getIncHP() {
		return incHP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryObjects returnThis() {
		return type;
	}
}
