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
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();

	public City(String name, Location location) {
		super("sprites/tower.png");
		this.name = name;
		setLocation(location);
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
			Output.println("you just built a brand new " + building.toString());
		} else {
			Output.println("you don't have enough Money");
		}
	}
	
	public void create(Warrior warrior, int number) {
		if (this.buildings.contains(warrior.requiredBuilding())) {
			if (player.canPay(number * warrior.getPrice())) {
				for (int i = 0; i < number; i++) {
					Warrior newWarrior = warrior.copy();
					this.warriors.add(newWarrior);
					player.addWarrior(newWarrior);
				}
				Output.println("you just created: " + number + " " + warrior.toString() + "S");
			}
		} else {
			Output.println("you need to build a " + warrior.requiredBuilding() + " first!");
		}
	}

	@Override
	public int offerActions() {
		Output.println("what would you like to do in " + this.name + "?");
		Output.println("0: back one menu\n1: Build a Casern\n2: Build a Forge\n3: create some Soldiers\n4: create some Knights");
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

	public String listStats() {
		StringBuffer sb = new StringBuffer();
		int casernCounter = 0;
		int forgeCounter = 0;
		for (Building b : this.buildings) {
			if (b.equals(Building.CASERN)) {
				casernCounter++;
			}
			if (b.equals(Building.FORGE)) {
				forgeCounter++;
			}
		}
		sb.append("CASERNS: " + casernCounter + "\n");
		sb.append("FORGES: " + forgeCounter + "\n");
		int soldierCounter = 0;
		int knightCounter = 0;
		for (Warrior w : this.warriors) {
			if (w.getType().equals("SOLDIER")) {
				soldierCounter++;
			}
			if (w.getType().equals("KNIGHT")) {
				knightCounter++;
			}
		}
		sb.append("SOLDIERS: " + soldierCounter + "\n");
		sb.append("KNIGHTS: " + knightCounter + "\n");
		return sb.toString();
	}

	@Override
	public String getInfo() {
		String info = "City";
		return info;
	}
}
