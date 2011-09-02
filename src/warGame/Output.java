package warGame;

import javax.swing.JPanel;

public class Output {
	
	private static GUI gui;


	public Output(GUI gui) {
		Output.gui = gui;
	}
	
	
	public static void println(String s) {
		gui.sendOutput("\n" + s + "\n");
	}

	
	public static void setInputPanel(JPanel panel) {
		gui.setActionPanel(panel);
	}


	public static void clearPanel() {
		Output.println("");
		gui.setActionPanel(new JPanel());
	}


	public static void refreshMap() {
		gui.refreshMap();
	}
}