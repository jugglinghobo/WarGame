package warGame;

import warGame.City.Building;

public class DefaultWarrior extends Warrior {

	public DefaultWarrior() {
		super(new Player("null"));
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getHP() {
		return 0;
	}

	@Override
	public int getAP() {
		return 0;
	}

	@Override
	public int getMovement() {
		return 0;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public Warrior copy() {
		return null;
	}

	@Override
	public Building requiredBuilding() {
		return null;
	}

	@Override
	public String getInfo() {
		return null;
	}

}
