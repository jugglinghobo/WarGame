package warGame;

import ch.aplu.jgamegrid.Location;

public class TradingRoute extends MapObject {

	public TradingRoute() {
		super("sprites/tradingRoute.png");
		this.price = 4;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public int offerActions() {
		return 0;
	}

	@Override
	public MapObject copy() {
		return new TradingRoute();
	}

	@Override
	public String getInfo() {
		String info = "";
		return info;
	}

	@Override
	public String toString() {
		return "Trading Route";
	}

}
