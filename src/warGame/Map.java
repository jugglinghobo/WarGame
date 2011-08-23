package warGame;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import ch.aplu.jgamegrid.*;

public class Map extends GameGrid implements GGMouseListener, GGMouseTouchListener{
	
	/*
	 * This is the Game map, which is basically a GUI that shows the Players actions in real time.
	 * The GUI will be done with help of the GameGrid Library provided by Stšfe (and other dudes from PHBern).
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Location> coloredLocs = new ArrayList<Location>();
	private Player activePlayer;
	private GGBackground bg;	

	public Map() {
		super(39, 60, 15, Color.LIGHT_GRAY, "sprites/map2_2.jpg", false, true);
		this.bg = getBg();
		initializeCities();
		this.addMouseListener(this, GGMouse.lClick | GGMouse.lDrag | GGMouse.rClick | GGMouse.rDrag);
	}

	private void initializeCities() {
		this.cities.add(new City("Bern", new Location(20, 20)));
		this.cities.add(new City("Basel", new Location(10, 10)));
		this.cities.add(new City("Winti", new Location(30, 30)));
		for (City c : cities) {
			c.addMouseTouchListener(this, GGMouse.lClick, true);
			this.addActor(c, c.getLocation());
		}
		refresh();
	}

	@Override
	public boolean mouseEvent(GGMouse mouse) {
		Location clickLoc = toLocationInGrid(mouse.getX(), mouse.getY());
		// add colored Squares with LMouse
		if (mouse.getEvent() == GGMouse.lClick || mouse.getEvent() == GGMouse.lDrag) {
			if (!coloredLocs.contains(clickLoc) && isEmpty(clickLoc)) {
				bg.fillCell(clickLoc, activePlayer.getColor(), false);
				coloredLocs.add(clickLoc);
			}
		}
		// remove colored Squares with RMouse
		if (mouse.getEvent() == GGMouse.rClick || mouse.getEvent() == GGMouse.rDrag) {
			coloredLocs.remove(clickLoc);
			bg.clear();
			for (Location loc : coloredLocs) {
				bg.fillCell(loc, activePlayer.getColor(), false);
			}
		}
		refresh();
		return true;
	}
	
	public void setActivePlayer(Player p) {
		this.activePlayer = p;
	}

	@Override
	public void mouseTouched(Actor actor, GGMouse mouse, Point spot) {
		switch (mouse.getEvent()) {
			case (GGMouse.lClick): {
				if (actor != null) {
					MapObject clicked = (MapObject) actor;
					clicked.offerActions();
				}
			}
		}
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void build(MapObject mapObj) {
		if (activePlayer.canPay(coloredLocs.size() * mapObj.getPrice())) {
			for (Location loc : coloredLocs) {
				MapObject newObject = mapObj.copy();
				newObject.setLocation(loc);
				this.addActor(newObject, loc);
				activePlayer.addMapObject(newObject);
			}
			Output.println("you just created " + coloredLocs.size() + " new " + mapObj.toString());
			clearMap();
		}
	}

	public void buildTradingRoute() {
		ArrayList<Location> colLocs = new ArrayList<Location>(coloredLocs);
		build(new TradingRoute());
		checkTradingConnection(colLocs);
		
	}

	private void checkTradingConnection(ArrayList<Location> buildLocs) {
		ArrayList<Location> tradingRoutes = new ArrayList<Location>(activePlayer.getTradingRoutes());
		for (Location tradingLoc : tradingRoutes) {
			for (Location buildLoc : buildLocs) {
				if (buildLoc.getNeighbourLocations(1).contains(tradingLoc)) {
					if (!tradingRoutes.contains(buildLoc)) {
						activePlayer.addTradingRoute(buildLoc);
					}
				}
				for (City c : activePlayer.getCities()) {
					if (buildLoc.getNeighbourLocations(1).contains(c.getLocation())) {
						activePlayer.addConnectedCity(c);
					}
				}
			}
		}
	}

	public void clearMap() {
		coloredLocs.clear();
		bg.clear();
		refresh();
	}
	
	public void prepareForNextPlayer() {
		for (MapObject mapObj : activePlayer.getMapObjects()) {
			if (!mapObj.isAlwaysVisible()) {
				mapObj.hide();
			}
		}
		refresh();
	}

	public void prepareFor(Player activePlayer) {
		setActivePlayer(activePlayer);
		for (MapObject mapObj : activePlayer.getMapObjects()) {
			mapObj.show();
		}
		refresh();
	}
}
