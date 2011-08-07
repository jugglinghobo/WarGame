package warGame;

import java.util.ArrayList;

import ch.aplu.jgamegrid.*;

public class City extends Actor{
	
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
	
	private String name;
	private Location location;
	private Player player;
	private ArrayList<Building> buildings = new ArrayList<Building>();

	public City(String name, Location location) {
		this.setName(name);
		this.setLocation(location);
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

}
