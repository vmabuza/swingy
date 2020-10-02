package wtc.swingy.view;

import wtc.swingy.model.TypeOfCharacter;

import java.util.ArrayList;

public interface UI {
	void	drawMenu();
	void	drawChoosePlayer(ArrayList<TypeOfCharacter> players);
	void	drawChooseRace();
	void	drawChooseClass();
	void	drawChooseName();
	void	drawMap();
	void	drawPlayer();
	void	drawCheckPlayerWantsToBattle();
	void	drawDie();
	void	drawWin();
	void	drawGetNothing();
	int		drawGetPrize(String type);
}
