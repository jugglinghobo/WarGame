package warGame;

import warGame.City.Building;

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

	@Override
	public Building requiredBuilding() {
		return Building.FORGE;
	}

	@Override
	public String getInfo() {
		String info = "Knight:\nPrice: 12/3\nHP: 2\nAP: 3\nMov: 10\nKnights are strong Warriors" +
				" capable of bringing sheer terror into enemy lines. They can move long distances and are hard to kill, " +
				"and even few Knights can turn a battle into slaughter!";
		return info;
	}
}
