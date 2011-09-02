package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.Location;
import warGame.City.Building;

public abstract class Warrior extends MapObject{
	
	protected int movement;
	

	public Warrior(String imgPath, Map map, City city) {
		super(imgPath, map, city.getLocation());
		initInputPanel();
	}
	
	public void initInputPanel() {
		actionPanel = new JPanel();
		initButtons();
	}

	private void initButtons() {
		JButton moveWarriorButton = new JButton("move");
		moveWarriorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moveWarrior();
			}
		});
		actionPanel.add(moveWarriorButton);
	}

	protected void moveWarrior() {
		move();
	}

	@Override
	public abstract Warrior copy();

	public abstract Building requiredBuilding();

	public abstract int getMovement();

	public abstract int getAP();
	
	public abstract int getHP();

	public void setName(String string) {
		super.setName(string);
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	public void setCity(City city) {
		super.setCity(city);
	}
	
	public void setMovement(int movement) {
		this.movement = movement;
	}
}
