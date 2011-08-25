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
	
	public static void setInputPanel(JPanel panel) {
		instance.updateInputPanel(panel);
	}
	
	public static void updateStats() {
		instance.setStats();
	}

	private static boolean willPrintout = true;

	public abstract void output(String s);
	
	public abstract void updateInputPanel(JPanel panel);

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

	public static void refreshMap() {
		instance.refresh();
		
	}

	public abstract void refresh();
}

class StdOutput extends Output {
	
	
	public void output(String s) {
		System.out.println(s);
	}

	@Override
	public void updateInputPanel(JPanel panel) {
	}

	@Override
	public void setStats() {
	}

	@Override
	public void resetPanel() {
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
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
	
	public void updateInputPanel(JPanel panel) {
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

	@Override
	public void refresh() {
		gui.refreshMap();
		
	}
	
}
