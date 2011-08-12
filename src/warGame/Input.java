package warGame;
import java.util.Scanner;


public abstract class Input {
	
	private static Input instance = new StdInput();
	
	public static void setInput(Input i) {
		instance = i;
	}
	
	public static String nextString() {
		return instance.getString();
	}
	
	public static int nextInt() {
		return instance.getInt();
	}
	
	public abstract String getString();
	
	public abstract int getInt();

}

class StdInput extends Input {

	private Scanner scan = new Scanner(System.in);
	
	public String getString() {
		return scan.nextLine();
	}

	public int getInt() {
		@SuppressWarnings("unused") // breaks input loop
		String unused;
		int realInt = 0;
		while (!scan.hasNextInt()) {
		unused = scan.nextLine();
		Output.println("please enter a number.");
		}
		realInt = scan.nextInt();
		unused = scan.nextLine();
		return realInt;
	}
}

class GuiInput extends Input{
	private GUI gui;

	GuiInput(GUI gui) {
		this.gui = gui;
	}

	@Override
	public String getString() {
		String gotIt = "";
		String in = gui.getInput();
		while (in.equals("")) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			in = gui.getInput();
			if (!in.equals("")) {
				gotIt = in;
			}
		}
		return gotIt;
	}

	public int getInt() {
		int gotIt = 0;
		String in = gui.getInput();
		while (in.equals("")) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			in = gui.getInput();
			if (!in.equals("") && Integer.parseInt(in) < 10) {
				gotIt = Integer.parseInt(in);
			}
		}
		return gotIt;
	}
}
