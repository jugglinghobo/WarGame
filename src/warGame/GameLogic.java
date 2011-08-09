package warGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import warGame.City.Buildings;


public class GameLogic {
	
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Map map;
	
	
	public GameLogic() {
		init();
	}
	
	public static void main(String[] args) {
		GameLogic game = new GameLogic();
		while (!game.isOver()) {
			game.play();
		}
	}

	private void play() {
		Player activePlayer = players.remove(0);
		map.setActivePlayer(activePlayer);
		executeActions(activePlayer);
		prepareForNextPlayer(activePlayer);
		players.add(activePlayer);
	}

	private void executeActions(Player activePlayer) {
		boolean done= false;
		while (!done) {
			Output.println(activePlayer.toString() + " is playing");
			int action = offerActions();
			switch (action) {
			case 1: { //manage Cities
				City chosenCity = activePlayer.chooseCity(activePlayer.getCities());
				int cityAction = chosenCity.offerActions();
				switch (cityAction) {
				case 1: {
					chosenCity.build(Buildings.CASERN);
					} break;
				case 2: {
					chosenCity.build(Buildings.FORGE);
				} break;
				case 3: {
					activePlayer.create(new Soldier());
				} break;
				case 4: {
					activePlayer.create(new Knight());
				}
				}
				executeActions(activePlayer);
				} break;
			case 2: { // manage map squares
				boolean marked = false;
				map.activateMouseListener();
				map.prepareFor(activePlayer);
				while (!marked) {
					Output.println("mark the squares you want");
					marked = askIfDone();
				}
				map.storeColoredLocsOf(activePlayer);
				map.deactivateMouseListener();
				int markedAction = offerMarkedSquaresActions();
				switch (markedAction) {
				case 1: { // defenseWall
					activePlayer.buildDefenseWall();
					executeActions(activePlayer);
					} break;
				case 2: { // farming land
					activePlayer.buildFarmingLand();
					executeActions(activePlayer);
					} break;
				}
				} break;
			}
			Output.println("end of turn");
			done = askIfDone();
		}
	}

	private int offerMarkedSquaresActions() {
		Output.println("What would you like to do with those Squares?");
		Output.println("0: do nothing, back to main menu\n1: build a defense Wall\n2: build farming land");
		int action = Input.nextInt();
		return action;
	}

	private int offerActions() {
		Output.println("what would you like to do? please enter a number:");
		Output.println("0: do nothing\n1:manage my cities\n2: build something on the Map");
		int action = Input.nextInt();
		return action;
	}

	private void prepareForNextPlayer(Player activePlayer) {
		map.prepareFor(activePlayer);
	}

	private boolean isOver() {
		for (Player p : this.players) {
			if (p.isWinner()) {
				displayWinner(p);
				return true;
			}
		}
		return false;
	}

	private void displayWinner(Player p) {
		Output.println("The winner is "+ p.toString());
		
	}

	private void init() {
		initPlayers();
		initMap();
		distributeCities();
		map.show();
	}

	private void distributeCities() {
		ArrayList<City> cities = new ArrayList<City>(map.getCities());
		while (!cities.isEmpty()) {
			Player activePlayer = this.players.remove(0);
			City chosenCity = activePlayer.chooseCity(cities);
			cities.remove(chosenCity);
			activePlayer.addCity(chosenCity);
			chosenCity.setPlayer(activePlayer);
			this.players.add(activePlayer);
		}
	}

	public void listCities(ArrayList<City> cities) {
		int i = 1;
		for (City c : cities) {
			Output.println(i + ": " + c.toString());
			i++;
		}
	}

	private void initPlayers() {
		boolean done = false;
		Output.println("Welcome, please register the Players");
		while (!done) {
			Output.println("Add a Player:");
			String name = Input.nextString();
			this.players.add(new Player(name));
			if (players.size() > 1) {
				done = askIfDone();
			}
		}
		for (Player p : players) {
			Color color = getRandomColor();
			p.setColor(color);
		}
	}
	
	private Color getRandomColor() {
		Random gen = new Random();
		return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
	}

	private void initMap() {
		this.map = new Map();
	}

	private boolean askIfDone() {
		Output.println("Done?");
		String answer = Input.nextString();
		if (answer.contains("y")) {
			return true;
		}
		return false;
	}
}
