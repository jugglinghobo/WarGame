package warGame;

import java.util.ArrayList;

import ch.aplu.jgamegrid.*;

public class City extends MapObject{
	
	public enum Buildings implements Building {
		CASERN(10), FORGE(15);

		private int price;
		
		private Buildings(int price) {
			this.price = price;
		}
		
		@Override
		public int getPrice() {
			return price;
		}
	}
	
	private ArrayList<Building> buildings = new ArrayList<Building>();

	public City(String name, Location location) {
		super("sprites/tower.png", name, location);
		this.setName(name);
		this.setLocation(location);
		show();
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
		}
	}

	@Override
	public int offerActions() {
		Output.println("what would you like to do in " + this.name + "?");
		Output.println("0: do nothing\n1: Build a casern\n2: Build a Forge\n3: create a Soldier\n4: create a Knight");
		int action = Input.nextInt();
		return action;
	}
}
