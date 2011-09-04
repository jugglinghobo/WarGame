package warGame;

import ch.aplu.jgamegrid.Location;


public class FarmingLand extends MapObject {

	public FarmingLand(Map map, Location location) {
		super("sprites/farmingLand.png", map, location);
		this.price = 4;
		super.setName("Farming Land");
	}

	@Override
	public MapObject copy() {
		return new FarmingLand(this.map, getLocation());
	}
}
