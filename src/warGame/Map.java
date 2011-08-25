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
		super(39, 53, 15, Color.LIGHT_GRAY, "sprites/map2_2.jpg", false, true);
		this.bg = getBg();
		initializeCities();
		this.activeateMouseListener(true);
	}

	private void initializeCities() {
		this.cities.add(new City(this, "Bern", new Location(20, 20)));
		this.cities.add(new City(this, "Basel", new Location(10, 10)));
		this.cities.add(new City(this, "Winti", new Location(30, 30)));
		this.cities.add(new City(this, "ZŸrich", new Location(10, 20)));
		this.cities.add(new City(this, "Biel", new Location(15, 25)));
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
			if (isEmptyToDraw(clickLoc) && !coloredLocs.contains(clickLoc)) {
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
	
	private boolean isEmptyToDraw(Location clickLoc) {
		ArrayList<Actor> actorsAtLoc = getActorsAt(clickLoc);
		if (actorsAtLoc.isEmpty()) {
			return true;
		}
		for (Actor a : actorsAtLoc) {
			if (a.isVisible()) {
				return false;
			}
		}
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
					if (activePlayer.getMapObjects().contains(clicked)) {
						clicked.offerActions();
					} else {
						Output.clearPanel();
					}
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
				ArrayList<Actor> actors = getActorsAt(loc);
				if (!actors.isEmpty()) {
					for (Actor a : actors) {
						a.show();
					}
				} else {
					this.activeateMouseListener(false);
					MapObject newObject = mapObj.copy();
					newObject.setLocation(loc);
					activePlayer.addMapObject(newObject);
					if (mapObj.getClass().equals(TradingRoute.class)) {
						activePlayer.addTradingRoute(loc);
					}
					newObject.addMouseTouchListener(this, GGMouse.lClick, true);
					this.addActor(newObject, newObject.getLocation());
					this.activeateMouseListener(true);
				}
			}
			Output.println("you just created " + coloredLocs.size() + " new " + mapObj.toString());
			clearMap();
		}
	}

	public void buildTradingRoute() {
		build(new TradingRoute(null));
		ArrayList<Location> existingTradingRoutes = new ArrayList<Location>(activePlayer.getTradingRoutes());
		checkTradingConnection(existingTradingRoutes);
		
	}

	private void checkTradingConnection(ArrayList<Location> existingTradingRoutes) {
		ArrayList<City> cities = new ArrayList<City>(activePlayer.getCities());
		for (City c : cities) {
			c.checkTradingConnection(existingTradingRoutes, cities);
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
			getBg().clear();
		}
		refresh();
	}

	public void prepareFor(Player activePlayer) {
		coloredLocs.clear();
		setActivePlayer(activePlayer);
		for (MapObject mapObj : activePlayer.getMapObjects()) {
			mapObj.show();
		}
		refresh();
	}

	public void activeateMouseListener(boolean bool) {
		if (bool) {
			this.addMouseListener(this, GGMouse.lClick | GGMouse.lDrag | GGMouse.rClick | GGMouse.rDrag);
		} else {
			removeMouseListener(this);
		}
		
	}
}
