package warGame;

import warGame.City.Building;

public class Soldier extends Warrior {
	
	private int price = 5;
	private int HP = 1;
	private int AP = 1;
	private int movement = 4;
	
	public Soldier(Player player, City city) {
		super("sprites/soldier.png", city);
		name = "Soldier";
		this.player = player;
		this.city = city;
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
		return new Soldier(player, this.city);
	}

	@Override
	public Building requiredBuilding() {
		return Building.CASERN;
	}
}
