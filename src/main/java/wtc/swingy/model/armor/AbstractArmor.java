package wtc.swingy.model.armor;

import wtc.swingy.model.InventoryObjectInterface;
import wtc.swingy.model.InventoryObjects;

public abstract class AbstractArmor implements InventoryObjectInterface {
	private int					defence;
	private String				name;
	private InventoryObjects	type;

	public AbstractArmor() {
		type = InventoryObjects.ARMOR;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getDefence() {
		return defence;
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
