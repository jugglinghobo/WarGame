package warGame;

import javax.swing.JButton;
import javax.swing.JPanel;

import warGame.City.Building;

public class DefaultWarrior extends Warrior {

	public DefaultWarrior() {
		super(null, null);
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getHP() {
		return 0;
	}

	@Override
	public int getAP() {
		return 0;
	}

	@Override
	public int getMovement() {
		return 0;
	}

	@Override
	public Warrior copy() {
		return null;
	}

	@Override
	public Building requiredBuilding() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void initInputPanel() {
		inputPanel = new JPanel();
		inputPanel.add(new JButton("FFOOOLLSS!!!!"));
		Output.println("woo");
	}

}
