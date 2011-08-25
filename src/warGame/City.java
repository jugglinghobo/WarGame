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
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	private ArrayList<City> connectedCities = new ArrayList<City>();

	public City(String name, Location location) {
		super("sprites/city.png");
		this.name = name;
		this.HP = 5; // Cities are harder to destroy/overtake
		initInputPanel();
		setLocation(location);
	}

	private void initInputPanel() {
		inputPanel = new JPanel();
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
				if (number != 0 ) {
					create(new Soldier(null), number);
				}	
			}
		});
		JButton knightButton = new JButton("Knight");
		knightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = getIntegerInput("How many do you want to make?");
				if (number != 0 ) {
					create(new Knight(null), number);
				}
			}
		});
		JButton leaveWarriorButton = new JButton("leave Warriors");
		leaveWarriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Warrior w = chooseWarrior();
				if (!w.getClass().equals(DefaultWarrior.class)) {
					int number = getIntegerInput("How many warriors do you want to leave?");
					if (number != 0 ) {
						leaveWarriors(w, number);
					}

				}
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
				Output.println("so many " + realCheckOut.get(0).toString() + "s have left town: " + realCheckOut.size());
				realCheckOut.clear();
			}
		
	}

	protected Warrior chooseWarrior() {
		Object[] warriors = {new Soldier(null), new Knight(null)};
		int input = JOptionPane.showOptionDialog(null, "what kind of warriors want you to leave the city?", "Leave Warriors",
				JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, warriors, null);
		if (input == JOptionPane.CLOSED_OPTION) {
			return new DefaultWarrior();
		}
		return (Warrior) warriors[input];
	}

	private int getIntegerInput(String question) {
		String answer = "";
		boolean ok = false;
		while (!ok) {
			answer = JOptionPane.showInputDialog(question);
			if (answer == null) {
				return 0;
			}
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
							+ warrior.toString() + "s");
				}
			} else {
				Output.println("you need to build a " + warrior.requiredBuilding()
						+ " first!");
			}
		}
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

	public void checkTradingConnection(ArrayList<Location> existingTradingRoutes, ArrayList<City> allCities) {
		ArrayList<Location> uncheckedTradingRoutes = new ArrayList<Location>(existingTradingRoutes);
		ArrayList<Location> cityNeighbours = this.location.getNeighbourLocations(0.5);
		for (Location loc : cityNeighbours) {
			if (existingTradingRoutes.contains(loc)) {
				uncheckedTradingRoutes.remove(loc);
				int oldNetworkSize = this.connectedCities.size();
				findPathFrom(loc, uncheckedTradingRoutes, allCities);
				int difference = this.connectedCities.size() - oldNetworkSize;
				ArrayList<City> newAdded = new ArrayList<City>();
				if (difference > 0) {
					newAdded.addAll(connectedCities.subList(connectedCities.size()-difference, connectedCities.size()));
					StringBuffer sb = new StringBuffer();
					sb.append("You successfully connected " );
					for (City c : newAdded) {
						sb.append(c.toString() + ", ");
					}
					sb.delete(sb.length()-2, sb.length());
					Output.println(sb.toString());
				}
			}
		}
	}

	private void findPathFrom(Location loc, ArrayList<Location> uncheckedTradingRoutes, ArrayList<City> playerCities) {
		for (Location neighbour : loc.getNeighbourLocations(0.5)) {
			for (City c : playerCities) {
				if (c.location.equals(neighbour)) {
					if (!this.connectedCities.contains(c)) {
						this.connectedCities.add(c);
					}
				}
			}
			if (uncheckedTradingRoutes.contains(neighbour)) {
				uncheckedTradingRoutes.remove(neighbour);
				findPathFrom(neighbour, uncheckedTradingRoutes, playerCities);
			}
		}
		
	}

	public ArrayList<City> getConnectedCities() {
		return this.connectedCities;
	}
	
}
