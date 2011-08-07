package warGame;

import ch.aplu.jgamegrid.*;

/**
 * This interface provides methods for all Objects that are present on the Map. 
 *
 */
public abstract class MapObject extends Actor{
	public MapObject(String imgPath) {
		super(imgPath);
	}

	public void offerActions() {
		Output.println("these are available ");
	}
}
