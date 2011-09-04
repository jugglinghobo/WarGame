package warGame;

import warGame.City.Building;

public class Soldier extends Warrior {
	
	private int price = 5;
	private int AP = 1;
	
	public Soldier(Player player, Map map, City city) {
		super("sprites/soldier.png", map, city);
		super.setName("Soldier");
		this.player = player;
		setCity(city);
		setMovement(4);
		setHP(1);
		setAP(4);
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public int getAP() {
		return this.AP;
	}

	@Override
	public Warrior copy() {
		return new Soldier(player, this.map, this.city);
	}

	@Override
	public Building requiredBuilding() {
		return Building.CASERN;
	}
}
