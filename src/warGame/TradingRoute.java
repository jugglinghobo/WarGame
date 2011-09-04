package warGame;

import ch.aplu.jgamegrid.Location;

public class TradingRoute extends MapObject {

	public TradingRoute(Map map, Location location) {
		super("sprites/tradingRoute.png", map, location);
		setPrice(5);
		super.setName("Trading Route");
	}

	@Override
	public MapObject copy() {
		return new TradingRoute(this.map, getLocation());
	}
}
