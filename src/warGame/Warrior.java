package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.Location;
import warGame.City.Building;

public abstract class Warrior extends MapObject{
	
	City city;

	public Warrior(String imgPath, City city) {
		super(imgPath, city.getLocation());
		location = city.getLocation();
		initInputPanel();
	}
	
	public void initInputPanel() {
		inputPanel = new JPanel();
		initButtons();
	}

	private void initButtons() {
		JButton moveWarriorButton = new JButton("move");
		moveWarriorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		inputPanel.add(moveWarriorButton);
	}

	@Override
	public Location getLocation() {
		return super.location;
	}

	@Override
	public abstract Warrior copy();

	public abstract Building requiredBuilding();

	public abstract int getMovement();

	public abstract int getAP();
	
	public abstract int getHP();
}
