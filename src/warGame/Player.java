package warGame;

import java.awt.Color;
import java.util.ArrayList;

import ch.aplu.jgamegrid.Location;

public class Player {
		
	private String name;
	private int money = 3000;
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

	public void offerMapActions() {
		Output.println(this + ", would you like to build something on those Squares?");
		Output.println("please enter the number:\n0. do nothing\n1.build defense wall\n2.build crap");
		int input = Input.nextInt();
		switch (input) {
		case 1: {
			Output.println("you do nothing");
		} break;
		case 2: {
			Output.println("you built a defense wall");
			offerMapActions();
		}  break;
		case 3: {
			Output.println("you did something else");
			offerMapActions();
		} break;
		}
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

	public void create(Warrior warrior, int number) {
		if (this.canPay(number * warrior.getPrice())) {
			for (int i = 0; i < number; i++) {
				Warrior newWarrior = warrior.copy();
				this.warriors.add(newWarrior);
			}
			Output.println("you just created: " + number + " " + warrior.toString());
		}
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

}
