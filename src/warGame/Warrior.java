package warGame;

public abstract class Warrior {
	
	public Warrior() {
		this.price = getPrice();
		this.HP = getHP();
		this.AP = getAP();
		this.movement = getMovement();
	}
	protected Player player;
	protected String type;
	protected int price;
	protected int HP;
	protected int AP;
	protected int movement;
	
	public abstract int getPrice();
	
	public abstract int getHP();
	
	public abstract int getAP();
	
	public abstract int getMovement();
	
	public abstract String getType();
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public abstract String toString();
}
