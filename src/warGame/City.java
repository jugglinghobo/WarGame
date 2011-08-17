package warGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import ch.aplu.jgamegrid.*;

public class City extends MapObject{
	
	public enum Building{
		CASERN(10, "Caserns are used for creating and training Soldiers"), FORGE(15, "Forges are used for creating nd training Knights");

		private int price;
		private String info;
		
		private Building(int price, String info) {
			this.price = price;
			this.info = info;
		}
		
		public int getPrice() {
		return price;
		}

		public String getInfo() {
			return this.info;
		}
	}
	
	private String name;
	private JPanel inputPanel;
	private JTextPane outputPane;
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	private JPanel interactionPanel;

	public City(String name, Location location) {
		super("sprites/city.png");
		this.name = name;
		this.HP = 5;	// Cities are harder to destroy/overtake
		initInteractionPanel();
		
		setLocation(location);
	}


	private void initInteractionPanel() {
		this.interactionPanel = new JPanel(new GridLayout(2, 0, 5, 5));
		initInputPanel();
		initOutputPane();
		interactionPanel.add(outputPane);
		interactionPanel.add(inputPanel);
	}


	private void initInputPanel() {
		this.inputPanel = new JPanel();
		initButtons();
		
	}
	
	private void initButtons() {
		// TODO Auto-generated method stub
		
	}


	private void initOutputPane() {
		this.outputPane = new JTextPane();
		StringBuffer sb = new StringBuffer();
		sb.append(this.toString());
		sb.append(listStats());
		outputPane.setText(sb.toString());
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
	public int offerActions() {
		Output.setInteractionPanel(this.interactionPanel);
		return 0;
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
	
	public String toString() {
		return this.name.toUpperCase();
	}

	@Override
	public String getInfo() {
		String info = "Cities are the base of your empire. Every City is able to store your Money and Food " +
				"as well as build and train new Warriors. You can connect Cities with trading routes, so they are able to share " +
				"Money and Food stored there. You can also store as many warriors as you like in a city without them needing Food ";
		return info;
	}
}
