package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.*;

import warGame.City.Building;

public abstract class Warrior extends MapObject{
	
	protected int movement;
	private int AP;
	private JPanel actionPanel;
	private Building requiredBuilding;
	private boolean hasAttacked;
	

	public Warrior(String imgPath, Map map, City city) {
		super(imgPath, map, city.getLocation());
		setCity(city);
		initInputPanel();
	}
	
	public void initInputPanel() {
		actionPanel = new JPanel();
		initButtons();
		setActionPanel(actionPanel);
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
		this.actionPanel.add(moveWarriorButton);
	}

	protected void moveWarrior() {
		move();
		checkForAttack();
	}

	private void checkForAttack() {
		ArrayList<Location> neighbors = new ArrayList<Location>(getLocation().getNeighbourLocations(0.5));
		ArrayList<MapObject> possibleEnemies = new ArrayList<MapObject>();
		//this adds all neighboring mapObjects to possibleEnemies
		for (Location loc : neighbors) {
			ArrayList<Actor> castToMapObject = map.getActorsAt(loc);
			for (Actor a : castToMapObject) {
				possibleEnemies.add((MapObject) a);
			}
		}
		for (MapObject enemy : possibleEnemies) {
			if (!player.getMapObjects().contains(enemy) && !hasAttacked) {
				attack(enemy);
				this.hasAttacked = true;
			}
		}
	}

	private void attack(MapObject enemy) {
		int attackPoints = getAP();
		enemy.hit(attackPoints);
	}
	
	@Override
	public void hit(int attackPoints) {
		System.out.println(name + " ARE ATTACKED!");
		subtractFromHP(attackPoints);
		System.out.println("HP: " + getHP());
		if (getHP() <= 0) {
			Output.println("you destroyed " + name + "!");
			removeSelf();
			map.refresh();
		}
	}

	@Override
	public abstract Warrior copy();
	
	public void setRequiredBuilding(Building required) {
		this.requiredBuilding = required;
	}

	public Building getRequiredBuilding() {
		return this.requiredBuilding;
	}

	public int getMovement() {
		return this.movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	public int getAP() {
		return this.AP;
	}
	
	public void setAP(int AP) {
		this.AP = AP;
	}
}
