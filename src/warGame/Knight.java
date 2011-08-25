package warGame;

import ch.aplu.jgamegrid.Location;
import warGame.City.Building;

public class Knight extends Warrior {
	
	private int price = 12;
	private int HP = 2;
	private int AP = 3;
	private int movement = 10;
	
	public Knight(Player player, Location loc) {
		super("sprites/knight.png", loc);
//		this.location = loc;
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
		return new Knight(getPlayer(), location);
	}

	@Override
	public Building requiredBuilding() {
		return Building.FORGE;
	}

	@Override
	public String toString() {
		return "WARRIOR";
	}
}
