package warGame;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ch.aplu.jgamegrid.*;

public class City extends MapObject {

	public enum Building {
		CASERN(10), FORGE(15);

		private int price;

		private Building(int price) {
			this.price = price;
		}

		public int getPrice() {
			return price;
		}
	}

	private int money;
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	private ArrayList<City> connectedCities = new ArrayList<City>();
	private Location spawnLocation;
	private CityActionPanel actionPanel;

	public City(Map map, String name, Location loc) {
		super("sprites/city.png", map, loc);
		this.spawnLocation = new Location(loc.x, loc.y-1);
		this.money = 20000;
		this.actionPanel = new CityActionPanel(this);
		setName(name);
		setHP(5);
		initActionPanel();
		show();
	}

	private void initActionPanel() {
		setActionPanel(actionPanel.getPanel());
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
				Output.println("you want to leave " + number + " but have only " + possibleCheckOut.size() +" " + warrior.toString());
				possibleCheckOut.clear();
			} else {
				for (int i = 0; i < number; i++) {
					realCheckOut.add(possibleCheckOut.remove(0));
				}
				for (Warrior w : realCheckOut) {
					leaveTown(w);
				}
				Output.println("so many " + realCheckOut.get(0).toString() + "s have left town: " + realCheckOut.size());
				realCheckOut.clear();
			}
	}

	private void leaveTown(Warrior w) {
		this.warriors.remove(w);
		w.setLocation(spawnLocation);
		player.addMapObject(w);
		map.addMapObjectActor(w);
	}

	public void buildBuilding(Building building) {
		if (canPay(building.getPrice())) {
			this.buildings.add(building);
			Output.println("you just built a brand new " + building.toString());
		}
	}
	
	public void build(MapObject mapObj) {
		ArrayList<Location> coloredLocs = map.getColoredLocs();
		if (canPay(coloredLocs.size() * mapObj.getPrice())) {
			for (Location loc : coloredLocs) {
				ArrayList<Actor> actors = map.getActorsAt(loc);
				if (!actors.isEmpty()) {
					for (Actor a : actors) {
						a.show();
					}
				} else {
					MapObject newObject = mapObj.copy();
					newObject.setLocation(loc);
					player.addMapObject(newObject);
					if (mapObj.getClass().equals(TradingRoute.class)) {
						player.addTradingRoute(loc);
					}
					map.addMapObjectActor(newObject);
				}
			}
			Output.println("you just created " + coloredLocs.size() + " new " + mapObj.toString());
			map.clearMap();
		}
	}
	
	public void buildTradingRoute() {
		build(new TradingRoute(map, getLocation()));
		ArrayList<Location> existingTradingRoutes = new ArrayList<Location>(player.getTradingRoutes());
		checkTradingConnection(existingTradingRoutes);
		
	}

	public void create(Warrior warrior, int number) {
		if (number > 0) {
			if (this.buildings.contains(warrior.getRequiredBuilding())) {
				if (canPay(number * warrior.getPrice())) {
					for (int i = 0; i < number; i++) {
						Warrior newWarrior = warrior.copy();
						this.warriors.add(newWarrior);
						player.addWarrior(newWarrior);
					}
					Output.println("you just created: " + number + " "
							+ warrior.toString() + "s");
				}
			} else {
				Output.println("you need to build a " + warrior.getRequiredBuilding()
						+ " first!");
			}
		}
	}
	
	private void checkTradingConnection(ArrayList<Location> existingTradingRoutes) {
		ArrayList<City> cities = new ArrayList<City>(player.getCities());
		for (City c : cities) {
			c.checkTradingConnection(existingTradingRoutes, cities);
		}
	}

	public void checkTradingConnection(ArrayList<Location> existingTradingRoutes, ArrayList<City> allCities) {
		ArrayList<Location> uncheckedTradingRoutes = new ArrayList<Location>(existingTradingRoutes);
		ArrayList<Location> cityNeighbours = getLocation().getNeighbourLocations(0.5);
		for (Location loc : cityNeighbours) {
			if (existingTradingRoutes.contains(loc)) {
				uncheckedTradingRoutes.remove(loc);
				int oldNetworkSize = this.connectedCities.size();
				findPathFrom(loc, uncheckedTradingRoutes, allCities);
				int difference = this.connectedCities.size() - oldNetworkSize;
				printConnection(difference);
			}
		}
	}

	private void printConnection(int difference) {
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

	
	// recursive Method, tracks the colored path
	private void findPathFrom(Location loc, ArrayList<Location> uncheckedTradingRoutes, ArrayList<City> playerCities) {
		for (Location neighbour : loc.getNeighbourLocations(0.5)) {
			for (City c : playerCities) {
				if (c.getLocation().equals(neighbour)) {
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
	
	//paying method
	public boolean canPay(int price) {
		int option = JOptionPane.showConfirmDialog(null, "This will cost you " + price + " money", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null);
		if (option == JOptionPane.OK_OPTION) {
			if (money - price >= 0) {
				money -= price;
				Output.setInputPanel(getActionPanel());
				return true;
			} else {
			Output.println("You have not enough Money");
			return false;
			}
		} else {
			return false;
		}
	}

	public ArrayList<City> getConnectedCities() {
		return this.connectedCities;
	}

	public Map getMap() {
		return this.map;
	}
	
	// should never be called!
	@Override
	public MapObject copy() {
		return null;
	}

	public String toString() {
		return this.name.toUpperCase();
	}

	@Override
	public boolean isAlwaysVisible() {
		return true;
	}

	public String getStats() {
		StringBuffer sb = new StringBuffer();
		int soldierCount = 0;
		int knightCount = 0;
		sb.append(this.name + "\n");
		sb.append("Money: " + this.money + "\n");
		for (Warrior w : this.warriors) {
			if (w.getClass().equals(Soldier.class)) {
				soldierCount++;
			}
			if (w.getClass().equals(Knight.class)) {
				knightCount++;
			}
		}
		sb.append("Soldiers: " + soldierCount + "\n");
		sb.append("Knights: " + knightCount + "\n");
		sb.append("Connected Cities: " + this.connectedCities);
		return sb.toString();
	}
	
	public void offerActions() {
		actionPanel.updateStats();
		map.activateMouseListener(true);
		Output.setInputPanel(getActionPanel());
	}
}
