package warGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class GameLogic {
	
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player activePlayer;
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
		boolean done= false;
		while (!done) {
			Output.println(activePlayer.toString() + " is playing");
			done = askIfDone();
		}
		prepareForNextPlayer();
	}

	private void prepareForNextPlayer() {
		map.storeColoredLocsOf(activePlayer);
		players.add(activePlayer);
		activePlayer = players.remove(0);
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
		activePlayer = players.remove(0);
	}
	
	private Color getRandomColor() {
		Random gen = new Random();
		return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
	}

	private void initMap() {
		this.map = new Map();
		map.setActivePlayer(activePlayer);
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
