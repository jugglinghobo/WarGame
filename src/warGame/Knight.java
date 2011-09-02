package warGame;

import warGame.City.Building;

public class Knight extends Warrior {
	
	private int price = 12;
	private int HP = 2;
	private int AP = 3;
	private int movement = 10;
	
	public Knight(Player player, City city) {
		super("sprites/knight.png", city);
		super.setName("Knight");
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
		return new Knight(getPlayer(), this.city);
	}

	@Override
	public Building requiredBuilding() {
		return Building.FORGE;
	}
}
