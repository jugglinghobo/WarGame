package warGame;

import java.awt.Color;
import java.util.ArrayList;

import ch.aplu.jgamegrid.Location;

public class Player {
		
	private String name;
	private int money = 30;
	private Color color;
	private ArrayList<Location> coloredLocs = new ArrayList<Location>();
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	private ArrayList<MapObject> mapObjects = new ArrayList<MapObject>();

	public Player(String name) {
		this.setName(name);
	}
	
	public String toString() {
		return getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isWinner() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canPay(int price) {
		if (money - price > 0) {
			money -= price;
			return true;
		}
		Output.println("You have not enough Money");
		return false;
		
	}
	
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void storeColoredLocs(ArrayList<Location> coloredLocs) {
		this.coloredLocs = coloredLocs;
	}

	public ArrayList<Location> getColoredLocs() {
		return this.coloredLocs;
	}

	public void addCity(City chosenCity) {
		this.cities.add(chosenCity);
	}

	public void listCities(ArrayList<City> cities) {
		int i = 1;
		for (City c : cities) {
			Output.println(i + ": " + c.toString());
			i++;
		}
	}	

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public City chooseCity(ArrayList<City> cities) {
		Output.println(this.name + ", choose a City");
		listCities(cities);
		int chosen = Input.nextInt()-1;
		while (chosen > cities.size()-1) {
			chosen = Input.nextInt()-1;
		}
		return cities.get(chosen);
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

	public String listStats() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name.toUpperCase() + "\n");
		for (City c : this.cities) {
			sb.append(c.toString() + ": \n");
			sb.append(c.listStats() + "\n");
		}
		return sb.toString();
	}

}
