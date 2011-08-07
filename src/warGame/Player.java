package warGame;

import java.awt.Color;

public class Player {
	
	private String name;
	private int money;
	private Color color;

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

}
