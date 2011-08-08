package warGame;

import java.awt.Color;
import java.util.ArrayList;

import ch.aplu.jgamegrid.Location;

public class Player {
	
	public enum Warriors implements Warrior {
		SOLDIER(4, 1, 1, 4), KNIGHT(10, 2, 3, 12);

		private int price;
		private int HP;
		private int AP;
		private int movement;
		
		private Warriors(int price, int HP, int AP, int movement) {
			this.price = price;
			this.HP = HP;
			this.AP = AP;
			this.movement = movement;
		}
		
		@Override
		public int getPrice() {
			return price;
		}

		@Override
		public int getHP() {
			return this.HP;
		}

		@Override
		public int getAP() {
			return this.AP;
		}

		@Override
		public int getMovement() {
			return this.movement;
		}
		
	}
	
	private String name;
	private int money;
	private Color color;
	private ArrayList<Location> coloredLocs = new ArrayList<Location>();
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Warrior> warriors;

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

	public void listCities() {
		int i = 1;
		for (City c : this.cities) {
			Output.println(i + ": " + c.toString());
		}
		
	}

	public void buildDefenseWall() {
		// TODO Auto-generated method stub
		
	}

	public void buildFarmingLand() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void create(Warrior warrior) {
		if (this.canPay(warrior.getPrice())) {
			this.warriors.add(warrior);
		}
	}

}
