package warGame;

import java.util.ArrayList;

import ch.aplu.jgamegrid.*;

public class City extends MapObject{
	
	public enum Building{
		CASERN(10), FORGE(15);

		private int price;
		
		private Building(int price) {
			this.price = price;
		}
		
		public int getPrice() {
		return price;
		}
	}
	
	private String name;
	private ArrayList<Building> buildings = new ArrayList<Building>();

	public City(String name, Location location) {
		super("sprites/tower.png");
		this.name = name;
		setLocation(location);
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
			Output.println("you just built a brand new " + building.toString());
		}
	}

	@Override
	public int offerActions() {
		Output.println("what would you like to do in " + this.name + "?");
		Output.println("0: back one menu\n1: Build a casern\n2: Build a Forge\n3: create some Soldiers\n4: create some Knights");
		int action = Input.nextInt();
		return action;
	}
	
	public String toString() {
		return this.name;
	}

	
	// should never be called!
	@Override
	public MapObject copy() {
		return null;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}
}
