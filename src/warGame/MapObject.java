package warGame;

import ch.aplu.jgamegrid.*;

/**
 * This interface provides methods for all Objects that are present on the Map. 
 *
 */
public abstract class MapObject extends Actor{
	
	Player player;
	Location location;
	int price;
	
	public MapObject(String imgPath) {
		super(imgPath);
		setCollisionImage();
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

	public int getPrice() {
		return this.price;
	}
}
