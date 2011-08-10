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
		super(80, 50, 15, Color.LIGHT_GRAY, "sprites/map.jpg", false, true);
		this.bg = getBg();
		initializeCities();
	}

	private void initializeCities() {
		this.cities.add(new City("Bern", new Location(20, 20)));
		this.cities.add(new City("Basel", new Location(40, 40)));
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
		if (mouse.getEvent() == GGMouse.lClick || mouse.getEvent() == GGMouse.lDrag) {
			if (!coloredLocs.contains(clickLoc)) {
				coloredLocs.add(clickLoc);
			}
			bg.fillCell(clickLoc, activePlayer.getColor(), false);
		}
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

	public void prepareFor(Player activePlayer) {
		setActivePlayer(activePlayer);
		clearMap();
		for (MapObject mapObj : activePlayer.getMapObjects()) {
			mapObj.show();
		}
		refresh();
	}

	private void clearMap() {
		bg.clear();
		for (Actor a : getActors()) {
			a.hide();
		}
		for (Actor c : getActors(City.class)) {
			c.show();
		}
	}

	public void storeColoredLocsOf(Player activePlayer) {
		ArrayList<Location> locs = new ArrayList<Location>(coloredLocs);
		activePlayer.storeColoredLocs(locs);
		coloredLocs.clear();
	}

	public void activateMouseListener() {
		this.addMouseListener(this, GGMouse.lClick | GGMouse.lDrag | GGMouse.rClick | GGMouse.rDrag);
	}
	
	public void deactivateMouseListener() {
		this.removeMouseListener(this);
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void build(MapObject mapObj) {
		ArrayList<Location> buildLocs = activePlayer.getColoredLocs();
		if (activePlayer.canPay(buildLocs.size() * mapObj.getPrice())) {
			for (Location loc : buildLocs) {
				MapObject newObject = mapObj.copy();
				this.addActor(newObject, loc);
				activePlayer.addMapObject(newObject);
			}
			System.out.println("you just created " + buildLocs.size() + " new " + mapObj.toString());
			refresh();
		}
	}
}
