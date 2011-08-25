package warGame;

import javax.swing.JPanel;

abstract class Output {

	private static Output instance = new StdOutput();

	public static void println(String s) {
		if (willPrintout)
			instance.output(s);
	}

	public static void setOutput(Output o) {
		instance = o;
	}
	
	public static void setInputPanel(MapObject obj, JPanel panel) {
		instance.updateInputPanel(obj, panel);
	}
	
	public static void updateStats() {
		instance.setStats();
	}

	private static boolean willPrintout = true;

	public abstract void output(String s);
	
	public abstract void updateInputPanel(MapObject obj, JPanel panel);

	public static void mute() {
		willPrintout = false;
	}

	public static void unmute() {
		willPrintout = true;
	}

	public abstract void setStats();

	public static void clearPanel() {
		instance.resetPanel();
		
	}

	public abstract void resetPanel();
}

class StdOutput extends Output {
	
	
	public void output(String s) {
		System.out.println(s);
	}

	@Override
	public void updateInputPanel(MapObject obj, JPanel panel) {
	}

	@Override
	public void setStats() {
	}

	@Override
	public void resetPanel() {
	}
}

class GuiOutput extends Output {
	private GUI gui;

	GuiOutput(GUI gui) {
		this.gui = gui;
	}

	public void output(String s) {
		gui.sendOutput("\n" + s + "\n");
	}
	
	public void updateInputPanel(MapObject obj, JPanel panel) {
		gui.setInputPanel(panel);
	}

	@Override
	public void setStats() {
		gui.updateStats();
	}

	@Override
	public void resetPanel() {
		Output.println("");
		gui.resetInteractionPanel();
		
	}
	
}
