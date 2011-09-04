package warGame;

import ch.aplu.jgamegrid.Location;

public class TradingRoute extends MapObject {

	public TradingRoute(Map map, Location location) {
		super("sprites/tradingRoute.png", map, location);
		this.price = 4;
		super.setName("Trading Route");
	}

	@Override
	public MapObject copy() {
		return new TradingRoute(this.map, getLocation());
	}
}
