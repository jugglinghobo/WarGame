package warGame;

import warGame.City.Building;

public class Soldier extends Warrior {

	
	public Soldier(Player player, Map map, City city) {
		super("sprites/soldier.png", map, city);
		this.player = player;
		setName("Soldier");
		setPrice(5);
		setMovement(4);
		setHP(1);
		setAP(1);
		setRequiredBuilding(Building.CASERN);
	}

	@Override
	public Warrior copy() {
		return new Soldier(player, this.map, getCity());
	}
}
