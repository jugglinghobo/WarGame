package warGame;

import ch.aplu.jgamegrid.Location;

public class DefenseWall extends MapObject {


	public DefenseWall(Map map, Location location) {
		super("sprites/wall.png", map, location);
		this.location = location;
		this.price = 6;
		super.setName("Defense Wall");
	}

	@Override
	public MapObject copy() {
		return new DefenseWall(this.map, this.location);
	}

	@Override
	public Location getLocation() {
		return this.location;
	}
}
