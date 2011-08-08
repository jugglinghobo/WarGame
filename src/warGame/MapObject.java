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
	
	public MapObject(String imgPath, String name, Location location) {
		super(imgPath);
		this.name = name;
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

	public void offerActions() {
		Output.println("ACTIONS");
	}
}
