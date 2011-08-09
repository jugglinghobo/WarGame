package warGame;

import ch.aplu.jgamegrid.Location;

public class FarmingLand extends MapObject {

	public FarmingLand(String imgPath, String name, Location location) {
		super(imgPath, name, location);
	}

	@Override
	public int offerActions() {
		return 0;
	}

}
