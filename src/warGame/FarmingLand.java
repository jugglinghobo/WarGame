package warGame;

import ch.aplu.jgamegrid.Location;


public class FarmingLand extends MapObject {

	public FarmingLand(Location location) {
		super("sprites/farmingLand.png", location);
		this.location = location;
		this.price = 4;
		name = "Farming Land";
	}

	public Location getLocation() {
		return this.location;
	}

	@Override
	public MapObject copy() {
		return new FarmingLand(this.location);
	}
}
