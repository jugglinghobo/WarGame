package warGame;

import warGame.City.Building;

public abstract class Warrior {
	
	protected Player player;
	protected String type;
	protected int price;
	protected int HP;
	protected int AP;
	protected int movement;
	
	public Warrior(Player player) {
		this.player = player;
	}
	
	public abstract int getPrice();
	
	public abstract int getHP();
	
	public abstract int getAP();
	
	public abstract int getMovement();
	
	public abstract String getType();
	
	public abstract Warrior copy(); 
	
	public abstract Building requiredBuilding();
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String toString() {
		return getType();
	}
}
