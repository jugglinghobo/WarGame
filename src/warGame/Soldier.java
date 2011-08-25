package warGame;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.Location;
import warGame.City.Building;

public class Soldier extends Warrior {
	
	private int price = 5;
	private int HP = 1;
	private int AP = 1;
	private int movement = 4;
	
	public Soldier(Player player, Location loc) {
		super("sprites/soldier.png", loc);
		this.location = loc;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public int getHP() {
		return this.HP;
	}

	@Override
	public int getAP() {
		return this.AP;
	}

	@Override
	public int getMovement() {
		return this.movement;
	}

	@Override
	public Warrior copy() {
		return new Soldier(player, this.location);
	}

	@Override
	public Building requiredBuilding() {
		return Building.CASERN;
	}

	@Override
	public String toString() {
		return "SOLDIER";
	}

	@Override
	public void initInputPanel() {
		inputPanel = new JPanel();
		inputPanel.add(new JButton("FFOOOLLSS!!!!"));
		Output.println("woo");
	}
}
