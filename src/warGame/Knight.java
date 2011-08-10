package warGame;

public class Knight extends Warrior {
	
	private int price = 12;
	private int HP = 2;
	private int AP = 3;
	private int movement = 10;
	private String type = "KNIGHT";
	
	public Knight(Player player) {
		super(player);
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
	public Warrior copy() {
		return new Knight(player);
	}
}
