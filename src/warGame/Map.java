package warGame;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import ch.aplu.jgamegrid.*;

public class Map extends GameGrid implements GGMouseListener{
	
	/*
	 * This is the Game map, which is basically a GUI that shows the Players actions in real time.
	 * The GUI will be done with help of the GameGrid Library provided by Stšfe (and other dudes from PHBern).
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Location> coloredLocs = new ArrayList<Location>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private GGBackground bg;	

	public Map(ArrayList<Player> players) {
		super(80, 50, 10, Color.LIGHT_GRAY, "sprites/map.jpg", false, true);
		this.players = players;
		this.bg = getBg();
		this.setTitle("WarGame");
		initializeCities();
		addMouseListener(this, GGMouse.lClick | GGMouse.lDrag | GGMouse.rClick);
		show();
	}

	private void initializeCities() {
		this.cities.add(new City("Bern", new Location(20, 20)));
		this.cities.add(new City("Basel", new Location(40, 40)));
		for (City c : cities) {
			this.addActor(c, c.getLocation());
		}
		refresh();
		
	}

	@Override
	public boolean mouseEvent(GGMouse mouse) {
		Location clickLoc = toLocationInGrid(mouse.getX(), mouse.getY());
		if (mouse.getEvent() == GGMouse.lClick) {
			MapObject clicked = (MapObject) getOneActorAt(clickLoc);
			if (clicked == null) {
				offerActions();
			} else {
				clicked.offerActions();
			}
		}
		if (mouse.getEvent() == GGMouse.lDrag) {
			if (!coloredLocs.contains(clickLoc)) {
				coloredLocs.add(clickLoc);
			}
			bg.fillCell(clickLoc, Color.red, false);
		}
		if (mouse.getEvent() == GGMouse.rClick) {
			coloredLocs.remove(clickLoc);
			bg.clear();
			for (Location loc : coloredLocs) {
				bg.fillCell(loc, Color.red, false);
			}
		}
		refresh();
		return true;
	}

	private void offerActions() {
		Output.println("would you like to build a defense wall?");
		
	}
}
