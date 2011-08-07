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

	public City(String name, Location location, Player player) {
		super("sprites/tower.png", name, location, player);
		this.player = player;
		this.setName(name);
		this.setLocation(location);
		show();
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
		}
	}
}
