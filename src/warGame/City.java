package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import ch.aplu.jgamegrid.*;

public class City extends MapObject {

	public enum Building {
		CASERN(10, "Caserns are used for creating and training Soldiers"), FORGE(
				15, "Forges are used for creating nd training Knights");

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
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();

	public City(String name, Location location) {
		super("sprites/city.png");
		this.name = name;
		this.HP = 5; // Cities are harder to destroy/overtake
		initInputPanel();

		setLocation(location);
	}

	private void initInputPanel() {
		this.inputPanel = new JPanel();
		initButtons();

	}

	private void initButtons() {
		/*
		 * needs: - Casern - Forge - Soldier - Knight - leave Warriors
		 */
		JButton casernButton = new JButton("Casern");
		casernButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				build(Building.CASERN);
			}
		});
		JButton forgeButton = new JButton("Forge");
		forgeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				build(Building.FORGE);
			}
		});
		JButton soldierButton = new JButton("Soldier");
		soldierButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = getIntegerInput("How many do you want to make?");
				create(new Soldier(null), number);	
			}
		});
		JButton knightButton = new JButton("Knight");
		knightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = getIntegerInput("How many do you want to make?");
				create(new Knight(null), number);
			}
		});
		JButton leaveWarriorButton = new JButton("leave Warriors");
		leaveWarriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Warrior w = chooseWarrior();
				int number = getIntegerInput("How many warriors do you want to leave?");
				leaveWarriors(w, number);
			}
		});

		inputPanel.add(casernButton);
		inputPanel.add(forgeButton);
		inputPanel.add(soldierButton);
		inputPanel.add(knightButton);
		inputPanel.add(leaveWarriorButton);
	}

	
	protected void leaveWarriors(Warrior warrior, int number) {
		ArrayList<Warrior> warriors = new ArrayList<Warrior>(this.warriors);
		ArrayList<Warrior> possibleCheckOut = new ArrayList<Warrior>();
		ArrayList<Warrior> realCheckOut = new ArrayList<Warrior>();
			for (Warrior w : warriors) {
				if (w.getClass().equals(warrior.getClass())) {
					possibleCheckOut.add(w);
				}
			}
			if (possibleCheckOut.size() < number) {
				Output.println("you want to leave " + number + " but have only " + possibleCheckOut.size() + warrior.toString());
				realCheckOut.clear();
			} else {
				for (int i = 0; i < number; i++) {
					realCheckOut.add(possibleCheckOut.remove(0));
				}
				for (Warrior w : realCheckOut) {
					this.warriors.remove(w);
				}
				Output.println("so many " + realCheckOut.get(0).toString() + " have left town: " + realCheckOut.size());
				realCheckOut.clear();
			}
		
	}

	//TODO need to fix CLOSE_OPERATION exception
	protected Warrior chooseWarrior() {
		Object[] warriors = {new Soldier(null), new Knight(null)};
		int input = JOptionPane.showOptionDialog(null, "what kind of warriors want you to leave the city?", "Leave Warriors",
				JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, warriors, null);
		return (Warrior) warriors[input];
	}

	private int getIntegerInput(String question) {
		String answer = "";
		boolean ok = false;
		while (!ok) {
			answer = JOptionPane.showInputDialog(question);
			try {
				Integer.parseInt(answer);
				ok = true;
			} catch (NumberFormatException e) {
				Output.println("Please enter a number");
			}
		}
		return Integer.parseInt(answer);
	}

	public void build(Building building) {
		if (player.canPay(building.getPrice())) {
			this.buildings.add(building);
			Output.println("you just built a brand new " + building.toString());
		}
	}

	public void create(Warrior warrior, int number) {
		if (number > 0) {
			if (this.buildings.contains(warrior.requiredBuilding())) {
				if (player.canPay(number * warrior.getPrice())) {
					for (int i = 0; i < number; i++) {
						Warrior newWarrior = warrior.copy();
						this.warriors.add(newWarrior);
						player.addWarrior(newWarrior);
					}
					Output.println("you just created: " + number + " "
							+ warrior.toString() + "S. Real size of warriors:" + this.warriors.size());
				}
			} else {
				Output.println("you need to build a " + warrior.requiredBuilding()
						+ " first!");
			}
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
		Output.setInputPanel(this, this.inputPanel);
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
	public boolean isAlwaysVisible() {
		return true;
	}

	@Override
	public String getInfo() {
		String info = "Cities are the base of your empire. Every City is able to store your Money and Food "
				+ "as well as build and train new Warriors. You can connect Cities with trading routes, so they are able to share "
				+ "Money and Food stored there. You can also store as many warriors as you like in a city without them needing Food ";
		return info;
	}
}
