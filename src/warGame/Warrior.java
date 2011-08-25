package warGame;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.Location;
import warGame.City.Building;

public abstract class Warrior extends MapObject{
	
	Location location;

	public Warrior(String imgPath, Location location) {
		super(imgPath, location);
		super.location = location;
		initInputPanel();
	}
	
	public void initInputPanel() {
		inputPanel = new JPanel();
		inputPanel.add(new JButton("FFOOOLLSS!!!!"));
	}

	@Override
	public Location getLocation() {
		return super.location;
	}

	@Override
	public abstract Warrior copy();

	@Override
	public abstract String toString();

	public abstract Building requiredBuilding();

	public abstract int getMovement();

	public abstract int getAP() ;
	
	public abstract int getHP();
}
