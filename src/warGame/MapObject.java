package warGame;

import ch.aplu.jgamegrid.*;

/**
 * This abstract Class provides methods for all non-movable Objects that are present on the Map,
 * i. e. City, DefenseWall, FarmingLand, TradingRoute. 
 *
 */
public abstract class MapObject extends Actor{
	
	Player player;
	Location location;
	int price;
	int HP;
	
	public MapObject(String imgPath) {
		super(imgPath);
		setCollisionImage();
		this.HP = 1;
		show();
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	public abstract Location getLocation();
	
	public abstract int offerActions();
	
	public abstract MapObject copy();
	
	public abstract String getInfo();
	
	public abstract String toString();

	public int getPrice() {
		return this.price;
	}

	public boolean isAlwaysVisible() {
		return false;
	}
}
