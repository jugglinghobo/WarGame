package warGame;

import ch.aplu.jgamegrid.Location;

public class DefenseWall extends MapObject {

	public DefenseWall(String imgPath, String name, Location location) {
		super(imgPath, name, location);
	}

	public DefenseWall() {
		super();
	}

	@Override
	public int offerActions() {
		return 0;
	}

}
