package wtc.swingy.view;

import wtc.swingy.view.gui.FrontUI;
import wtc.swingy.view.shell.Shell;

public class View {
	private FrontUI frontGui;
	private Shell shell;

	public View(FrontUI frontGui, Shell shell) {
		this.frontGui = frontGui;
		this.shell = shell;
	}

	public FrontUI getSwingGui() {
		return frontGui;
	}

	public Shell getShellGui() {
		return shell;
	}
}
