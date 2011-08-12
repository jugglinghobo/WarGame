package warGame;

abstract class Output {

	private static Output instance = new StdOutput();

	public static void println(String s) {
		if (willPrintout)
			instance.output(s);
	}

	public static void setOutput(Output o) {
		instance = o;
	}

	private static boolean willPrintout = true;

	public abstract void output(String s);

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
}

class GuiOutput extends Output {
	private GUI gui;

	GuiOutput(GUI gui) {
		this.gui = gui;
	}

	public void output(String s) {
		gui.sendOutput(s + "\n");
	}
}
