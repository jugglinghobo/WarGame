package warGame;

import java.util.ArrayList;

public class Map {
	
	/*
	 * This is the Game map, which is basically a GUI that shows the Players actions in real time.
	 * The GUI will be done with help of the GameGrid Library provided by Stšfe (and other dudes from PHBern).
	 */
	
	private ArrayList<City> cities;

	public Map(ArrayList<Player> players) {
		initializeCities();
	}

	private void initializeCities() {
		this.cities.add(new City("Bern"));
		
	}

}
