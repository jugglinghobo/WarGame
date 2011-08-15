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
						default: {
							MenuChooseAction = true;
						}
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
					// trading route
					case 3: {
						map.buildTradingRoute();
					}
					default: {
						markedAll = true;
					}
					}
				}
				}break;
			case 3: {
				Output.println(activePlayer.listStats());
				} break;
			case 4: {
				boolean gotInfo = false;
				while (!gotInfo) {
				int info = listInfo();
					switch(info) {
					// City info
					case 1: {
						Output.println(new City(null, null).getInfo());
						} break;
					// Soldiers info
					case 2: {
						Output.println(new Soldier(null).getInfo());
						} break;
					case 3: {
						Output.println(new Knight(null).getInfo());
						} break;
					case 4: {
						Output.println(Building.CASERN.getInfo());
						} break;
					case 5: {
						Output.println(Building.FORGE.getInfo());
					}
						
					default: {
						gotInfo = true;
					}
					}
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
		sb.append("1: Cities\n2: Soldiers\n3: Knights\n4: Caserns\n5: Forges\n0: i have no more questions");
		int input = askForNumber("what would you like to know:\n" + sb.toString());
		return input;
	}

	private int askForNumber(String question) {
		Output.println(question);
		return Input.nextInt();
	}

	private int offerMarkedSquaresActions() {
		Output.println("What would you like to do with those Squares?");
		Output.println("0: do nothing, back to main menu\n1: build a Defense Wall\n2: build Farming Land\n3: build Trading Route");
		int action = Input.nextInt();
		return action;
	}

	private int offerActions() {
		Output.println("what would you like to do? please enter a number:");
		Output.println("0: end turn\n1: manage my cities\n2: build something on the Map\n3: list your Stats\n4: get some Informations");
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
		Output.println("Welcome to Wargame!\nplease register the Players");
		while (!done) {
			Output.println("Add a Player:");
			String name = Input.nextString();
			this.players.add(new Player(name));
			Output.println("Player " + name.toUpperCase() + " added");
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
