package warGame;

import ch.aplu.jgamegrid.Location;

public class TradingRoute extends MapObject {

	public TradingRoute(Location location) {
		super("sprites/tradingRoute.png", location);
		this.price = 4;
		this.location = location;
		name = "Trading Route";
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public MapObject copy() {
		return new TradingRoute(this.location);
	}
}
