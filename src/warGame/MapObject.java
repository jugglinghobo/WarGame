package warGame;

import ch.aplu.jgamegrid.*;

/**
 * This interface provides methods for all Objects that are present on the Map. 
 *
 */
public abstract class MapObject extends Actor{
	
	Player player;
	String name;
	Location location;
	int price;
	
	public MapObject() {
		super();
		this.name = setName();
		this.location = location;
		setCollisionImage();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Class<? extends MapObject> type() {
		return this.getClass();
	}

	public abstract int offerActions();

	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
}
