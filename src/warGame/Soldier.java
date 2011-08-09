package warGame;

public class Soldier extends Warrior {
	
	public Soldier() {
		super();
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
	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		
		return this.player + this.getType();
	}
}
