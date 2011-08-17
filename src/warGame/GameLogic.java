package warGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import warGame.City.Building;

public class GameLogic{
	
	public static void main(String[] args) {
		new GUI(new GameLogic());
	}
	
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Map map;
	private JTextPane outputPane;	
	private JPanel inputPanel;
	
	public GameLogic() {
		initMap();
	}
	
	private void initMap() {
		this.map = new Map();
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
						int cityAction = 0;//chosenCity.offerActions();
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

	private int askForNumber(String question) {
		Output.println(question);
		return Input.nextInt();
	}
	
	private int listInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("1: Cities\n2: Soldiers\n3: Knights\n4: Caserns\n5: Forges\n0: I have no more questions");
		int input = askForNumber("what would you like to know:\n" + sb.toString());
		return input;
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

	public void init() {
		initPlayers();
		distributeCities();
	}
	
	private void initPlayers() {
		boolean done = false;
		ArrayList<String> usedNames = new ArrayList<String>();
		int i = 1;
		while (!done) {
			String name = (String) getInput("Player " + i + " , please enter your name", "sprites/player.png");
			if (name != null && !usedNames.contains(name)) {
				usedNames.add(name);
				i++;
				this.players.add(new Player(name));
				Output.println("Player " + name.toUpperCase() + " added");
				if (players.size() > 1) {
					done = !getBooleanInput("Would you like to add another Player?", "sprites/player.png");
				}
			}
		}
		outputPane.setText("");
		for (Player p : players) {
			Color color = getRandomColor();
			p.setColor(color);
		}
	}

	private void distributeCities() {
		ArrayList<City> cities = new ArrayList<City>(map.getCities());
		while (!cities.isEmpty()) {
			Player activePlayer = this.players.remove(0);
			City chosenCity = chooseInput(cities, activePlayer + " , please choose a City", "sprites/city.png");
			Output.println(activePlayer + " chose " + chosenCity);
			cities.remove(chosenCity);
			activePlayer.addCity(chosenCity);
			chosenCity.setPlayer(activePlayer);
			this.players.add(activePlayer);
		}
		outputPane.setText("");
	}
	
	
	private Object getInput(String string, String iconPath) {
		Object input = JOptionPane.showInputDialog(null, string, "NEW PLAYER", JOptionPane.QUESTION_MESSAGE, new ImageIcon(iconPath), null, null);
		return input;
	}
	
	public boolean getBooleanInput(String string, String iconPath) {
		int option = JOptionPane.showConfirmDialog(null, string, null, JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, new ImageIcon(iconPath));
		if (option == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	private City chooseInput(ArrayList<City> cities, String string, String iconPath) {
		Object[] cityObjects = cities.toArray();
		int input = JOptionPane.CLOSED_OPTION;
		while (input == JOptionPane.CLOSED_OPTION) {
			input = JOptionPane.showOptionDialog(null, string, null, JOptionPane.OK_OPTION, 
					JOptionPane.PLAIN_MESSAGE, new ImageIcon(iconPath), cityObjects, null);
		}
		return cities.get(input);
	}

	private Color getRandomColor() {
		Random gen = new Random();
		return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
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

	public Map getMap() {
		return this.map;
	}

	public void setInteractionPanel(JTextPane outputPane, JPanel inputPanel) {
		this.outputPane = outputPane;
		this.inputPanel = inputPanel;
	}
}
