package warGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import warGame.City.Building;

public class GameLogic{
	
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Map map;
	private GUI gui;
	
	
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
		map.prepareFor(activePlayer);
		boolean done= false;
		while (!done) {
			Output.println(activePlayer.toString() + " is playing");
			int action = offerActions();
			switch (action) {
			//manage Cities
			case 1: {
				boolean MenuchooseCity = false;
				while (!MenuchooseCity) {
					City chosenCity = activePlayer.chooseCity(activePlayer.getCities());
					boolean MenuChooseAction = false;
					while (!MenuChooseAction) {
						int cityAction = chosenCity.offerActions();
						switch (cityAction) {
						// Casern
						case 1: {
							chosenCity.build(Building.CASERN);
							} break;
						// Forge
						case 2: {
							chosenCity.build(Building.FORGE);
							} break;
						 // Soldiers
						case 3: {
							int number = askForNumber("how many new Soldiers would you like to have?");
							chosenCity.create(new Soldier(activePlayer), number);
							} break;
						 // Knights
						case 4: {
							int number = askForNumber("how many new Knights would you like to have?");
							chosenCity.create(new Knight(activePlayer), number);
							} break;
						default: MenuChooseAction = true;
						}
					}
					MenuchooseCity = !askQuestion("would you like to manage another City?");
				}
				} break;
			// manage map squares
			case 2: {
				boolean markedAll = false;
				while (!markedAll) {
					boolean marked = false;
					map.activateMouseListener();
					map.prepareFor(activePlayer);
					while (!marked) {
						marked = askQuestion("have you marked all the Squares you want?");
					}
					map.storeColoredLocsOf(activePlayer);
					map.deactivateMouseListener();
					int markedAction = offerMarkedSquaresActions();
					switch (markedAction) {
					// defenseWall
					case 1: {
						map.build(new DefenseWall());
						marked = true;
						} break;
					// farming land
					case 2: {
						map.build(new FarmingLand());
						marked = true;
						} break;
					default: {
						markedAll = true;
					}
					}
				}
				} break;
			case 3: {
				Output.println(activePlayer.listStats());
			} break;
			case 4: {
				int info = listInfo();
				switch(info) {
				
				}
			} break;
			default: {
				done = askQuestion("do you really want to end your turn?\n");
				}
			}
		}
	}

	private int listInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("1: Cities\n2: Soldiers\n3: Knights\n4: Caserns\n5: Forges");
		int input = askForNumber("what would you like to know:\n" + sb.toString());
		return input;
	}

	private int askForNumber(String question) {
		Output.println(question);
		return Input.nextInt();
	}

	private int offerMarkedSquaresActions() {
		Output.println("What would you like to do with those Squares?");
		Output.println("0: do nothing, back to main menu\n1: build a defense Wall\n2: build farming land\n");
		int action = Input.nextInt();
		return action;
	}

	private int offerActions() {
		Output.println("what would you like to do? please enter a number:");
		Output.println("0: end turn\n1: manage my cities\n2: build something on the Map\n3: list your stats\n4: get some informations");
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
		initMap();
		initGUI();
		initPlayers();
		distributeCities();
	}

	private void initGUI() {
		this.gui = new GUI(map);
		Output.setOutput(new GuiOutput(gui));
		Input.setInput(new GuiInput(gui));
		
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
				done = askQuestion("is that all?");
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

	private boolean askQuestion(String question) {
		Output.println(question);
		String answer = Input.nextString();
		Output.println(answer);
		if (answer.contains("y")) {
			return true;
		}
		return false;
	}
}
