package warGame;

import java.awt.Color;
import java.util.ArrayList;

import ch.aplu.jgamegrid.*;

public class Map extends GameGrid implements GGMouseListener{
	
	/*
	 * This is the Game map, which is basically a GUI that shows the Players actions in real time.
	 * The GUI will be done with help of the GameGrid Library provided by Stšfe (and other dudes from PHBern).
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<City> cities = new ArrayList<City>();

	public Map(ArrayList<Player> players) {
		super(80, 50, 10, Color.LIGHT_GRAY, "sprites/map.jpg", false, true);
		this.setTitle("WarGame");
		initializeCities();
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
	public boolean mouseEvent(GGMouse arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
