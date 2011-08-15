package warGame;

import ch.aplu.jgamegrid.Location;


public class FarmingLand extends MapObject {

	public FarmingLand() {
		super("sprites/farmingLand.png");
		this.price = 4;
	}

	@Override
	public int offerActions() {
		return 0;
	}

	public Location getLocation() {
		return this.location;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public MapObject copy() {
		return new FarmingLand();
	}
	
	public String toString() {
		return "Farming Land";
	}

	@Override
	public String getInfo() {
		String info = "Farming Land provides you with Food every round.";
		return info;
	}

}
