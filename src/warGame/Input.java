package warGame;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 * This class is responsible for prompting input from the user.
 * At the moment, this is achieved by using JOptionPanes
 *
 */
public class Input {
	
	public static Object promptStringInput(String string, String iconPath) {
		Object input = null;
		while (input == null) {
			input = JOptionPane.showInputDialog(null, string, "NEW PLAYER", JOptionPane.QUESTION_MESSAGE, new ImageIcon(iconPath), null, null);
		}
		return input;
	}
	
	public static int promptIntegerInput(String question) {
		String answer = "";
		boolean ok = false;
		while (!ok) {
			answer = JOptionPane.showInputDialog(question);
			if (answer == null) {
				return 0;
			}
			try {
				Integer.parseInt(answer);
				ok = true;
			} catch (NumberFormatException e) {
				Output.println("Please enter a number");
			}
		}
		return Integer.parseInt(answer);
	}
	
	public static Object promptChooseInput(Object[] objects, String info, String title) {
		int input = JOptionPane.showOptionDialog(null, info, title,
				JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, objects, null);
		if (input == JOptionPane.CLOSED_OPTION) {
			return null;
		}
		return objects[input];
	}
	
	public static boolean getBooleanInput(String string, String iconPath) {
		int option = JOptionPane.showConfirmDialog(null, string, null, JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, new ImageIcon(iconPath));
		if (option == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

}