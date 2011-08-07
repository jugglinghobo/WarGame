package warGame;

import java.awt.Color;
import java.util.ArrayList;

import ch.aplu.jgamegrid.Location;

public class Player {
	
	private String name;
	private int money;
	private Color color;
	private ArrayList<Location> coloredLocs = new ArrayList<Location>();

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

}
