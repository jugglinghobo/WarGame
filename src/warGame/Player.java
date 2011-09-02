package warGame;

import java.awt.Color;
import java.util.ArrayList;
import ch.aplu.jgamegrid.Location;

public class Player {
		
	private String name;
	private int money = 300000;
	private Color color;
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	private ArrayList<MapObject> mapObjects = new ArrayList<MapObject>();
	private ArrayList<Location> tradingRoutes = new ArrayList<Location>();

	public Player(String name) {
		this.setName(name);
	}
	
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public void addCity(City chosenCity) {
		this.cities.add(chosenCity);
		this.mapObjects.add(chosenCity);
		this.tradingRoutes.add(chosenCity.getLocation());
		chosenCity.setPlayer(this);
	}	

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void addMapObject(MapObject mapObj) {
		this.mapObjects.add(mapObj);
	}

	public ArrayList<MapObject> getMapObjects() {
		return this.mapObjects;
	}

	public void addWarrior(Warrior warrior) {
		this.warriors.add(warrior);
	}
	
	public ArrayList<Location> getTradingRoutes() {
		return this.tradingRoutes;
	}

	public void addTradingRoute(Location buildLoc) {
		this.tradingRoutes.add(buildLoc);
		
	}

	public int getMoney() {
		return this.money;
	}
	
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
