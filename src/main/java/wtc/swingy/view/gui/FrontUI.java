package wtc.swingy.view.gui;

import wtc.swingy.model.TypeOfCharacter;
import wtc.swingy.Main;
import wtc.swingy.model.map.Map;
import wtc.swingy.view.UI;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrontUI extends JFrame implements UI {
	private static final int	SCREEN_HEIGHT = 800;
	private static final int	SCREEN_WIDTH = 800;
	private TypeOfCharacter player;
	private Map map;
	private JTextArea			mapLabel;
	private boolean				exitLoop;
	private int					checkLoop;
    private JButton     		switchButton = new JButton("Switch game mode");
    private JLabel				errorOutput = new JLabel();
    private JTextField			textInput = new JTextField();
	private final JButton		select[] = new JButton[4];
	private final JButton		delete[] = new JButton[4];
	private final JTextArea		areas[] = new JTextArea[4];
	private void clearGUI() {
		this.getContentPane().removeAll();
		this.repaint();
		add(switchButton);
	}

	public void setPlayer(TypeOfCharacter player) {
		this.player = player;
	}

	public FrontUI() {
        super("SWINGY");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		switchButton.addActionListener(new SwitchButtonListener());
		switchButton.setBounds(0, 0, SCREEN_WIDTH, 30);
    }

	public boolean isExitLoop() {
		return exitLoop;
	}

	public void setVisibility(boolean b) {
    	this.setVisible(b);
	}


	public void drawMenu() {
    	exitLoop = false;
    	clearGUI();
	}


	public void drawChoosePlayer(final ArrayList<TypeOfCharacter> players) {

	}

	public void drawChooseRace() {
	}

	public void drawChooseClass() {

	}

	public void drawChooseName() {

	}


	public void drawMap() {
	}

	public void updateMap(Map map) {
	}

	public void drawPlayer() {

	}

	public void drawCheckPlayerWantsToBattle() {

	}

	public void drawDie() {

	}

	public void drawWin() {

	}

	public void drawGetNothing() {

	}

	public int drawGetPrize(String type) {
		return JOptionPane.showConfirmDialog(null,  "" + type, "",
				JOptionPane.YES_NO_OPTION);
	}

    private class SwitchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.gameMode = !Main.gameMode;
			setVisibility(Main.gameMode);
		}
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getCheckLoop() {
		return checkLoop;
	}
}
