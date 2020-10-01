package wtc.swingy.model.map;

import wtc.swingy.model.TypeOfCharacter;

import java.util.Random;

public class Map {
	private int		size;
	private char	map[][];

	private void generateMap() {
		Random random = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (random.nextInt(size * 5) == 0 && i != size / 2 && j != size / 2)
					map[i][j] = 'E';
				else
					map[i][j] = '.';
		checkEmptyMap();
	}

	public void checkEmptyMap() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (map[i][j] != '.')
					return;
		generateMap();
	}

	private void createMap(TypeOfCharacter typeOfCharacter) {
		size = (typeOfCharacter.getLevel() - 1) * 5 + 10 - (typeOfCharacter.getLevel() % 2);
		map = new char[size][size];
		generateMap();
		typeOfCharacter.setPosX(size / 2);
		typeOfCharacter.setPosY(size / 2);
	}

	public Map(TypeOfCharacter typeOfCharacter) {
		createMap(typeOfCharacter);
	}

	public void expandMap(TypeOfCharacter typeOfCharacter) {
		createMap(typeOfCharacter);
	}

	public int getSize() {
		return size;
	}

	public char getMapCell(int i, int j) {
		return map[i][j];
	}

	public void setMapCell(int i, int j, char c) {
		map[i][j] = c;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public char[][] getMap() {
		return map;
	}
}
