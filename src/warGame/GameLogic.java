package warGame;

import java.util.ArrayList;


public class GameLogic {
	
	/*
	 * 1. Player initialisation
	 * 2. Map initialisation
	 * 3. City initialisation
	 */
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Map map;
	
	
	public GameLogic() {
		init();
	}
	
	public static void main(String[] args) {
		GameLogic game = new GameLogic();
		while (game.isOver()) {
			game.play();
		}
	}

	private void play() {
		System.out.println("play");
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
		while (!done) {
			Output.println("Add a Player:");
			String name = Input.nextString();
			this.players.add(new Player(name));
			done = askIfDone();
		}
	}
	
	private void initMap() {
		this.map = new Map(this.players);
	}

	private boolean askIfDone() {
		Output.println("Done?");
		String answer = Input.nextString();
		if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
			return true;
		}
		return false;
	}
}
