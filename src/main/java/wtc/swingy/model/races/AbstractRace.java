package wtc.swingy.model.races;

public abstract class AbstractRace {
	private String	name;
	private int		plusHP;
	private int		plusDefence;
	private int		plusAttack;

	public int getPlusHP() {
		return plusHP;
	}

	public int getPlusDefence() {
		return plusDefence;
	}

	public int getPlusAttack() {
		return plusAttack;
	}

	public String getName() {
		return name;
	}

	public AbstractRace(int plusHP, int plusDefence, int plusAttack, String name) {
		this.plusHP = plusHP;
		this.plusDefence = plusDefence;
		this.plusAttack = plusAttack;
		this.name = name;
	}
}
