package wtc.swingy.model.villain;

import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.model.races.MonsterRace;

import java.util.Random;

public class Villain extends TypeOfCharacter {
	public Villain(TypeOfCharacter player) {
		this.setName("Monster");
		this.setPlayerRace(new MonsterRace());
		this.setLevel(player.getLevel() + (new Random().nextBoolean() ? 1 : 0));
		this.setHealthPoints(this.getLevel() * 50 + 100);
		this.setAttack(2 * this.getLevel() + new Random().nextInt(3));
	}
}
