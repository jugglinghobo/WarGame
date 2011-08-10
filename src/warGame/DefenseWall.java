package warGame;

import ch.aplu.jgamegrid.Location;

public class DefenseWall extends MapObject {


	public DefenseWall() {
		super("sprites/wall.png");
	}

	@Override
	public int offerActions() {
		return 0;
	}

	@Override
	public MapObject copy() {
		return new DefenseWall();
	}

	@Override
	public Location getLocation() {
		return this.location;
	}
	
	public String toString() {
		return "DefenseWall";
	}

}
