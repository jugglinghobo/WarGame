package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import warGame.City.Building;

public abstract class Warrior extends MapObject{
	
	protected int movement;
	private int HP;
	private int AP;
	

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
				map.refresh();
			}
		});
		actionPanel.add(moveWarriorButton);
	}

	protected void moveWarrior() {
		this.move();
	}

	@Override
	public abstract Warrior copy();

	public abstract Building requiredBuilding();

	public int getMovement() {
		return this.movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getAP() {
		return this.AP;
	}
	
	public void setAP(int AP) {
		this.AP = AP;
	}
}
