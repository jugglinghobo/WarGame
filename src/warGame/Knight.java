package warGame;

import warGame.City.Building;

public class Knight extends Warrior {
	
	public Knight(Player player, Map map, City city) {
		super("sprites/knight.png", map, city);
		this.player = player;
		setName("Knight");
		setPrice(10);
		setMovement(6);
		setHP(2);
		setAP(3);
		setRequiredBuilding(Building.FORGE);
	}

	@Override
	public Warrior copy() {
		return new Knight(this.player, this.map, getCity());
	}
}
