package warGame;

import ch.aplu.jgamegrid.Location;

public class TradingRoute extends MapObject {

	public TradingRoute(Map map, Location location) {
		super("sprites/tradingRoute.png", map, location);
		this.price = 4;
		this.location = location;
		super.setName("Trading Route");
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public MapObject copy() {
		return new TradingRoute(this.map, this.location);
	}
}
