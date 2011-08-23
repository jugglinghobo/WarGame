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

	private static boolean willPrintout = true;

	public abstract void output(String s);
	
	public abstract void updateInputPanel(JPanel panel);

	public static void mute() {
		willPrintout = false;
	}

	public static void unmute() {
		willPrintout = true;
	}

}

class StdOutput extends Output {
	
	
	public void output(String s) {
		System.out.println(s);
	}

	@Override
	public void updateInputPanel(JPanel panel) {
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
	
}
