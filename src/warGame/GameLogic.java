package warGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class GameLogic{
	
	/* MAIN */
	public static void main(String[] args) {
		new GUI(new GameLogic());
	}
	
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Map map;
	private JPanel statsPanel;
	private JTextArea namePanel;
	private JPanel mapActionPanel;
	private Player activePlayer;
	
	public GameLogic() {
		initPanels();
		initMap();
	}
	
	private void initPanels() {
		initStatsPanel();
	}

	private void initStatsPanel() {
		this.statsPanel = new JPanel(new BorderLayout(5, 5));
		initNamePanel();
		initEndTurnButton();		
	}
	
	private void initNamePanel() {
		namePanel = new JTextArea();
		namePanel.setEditable(false);
		namePanel.setOpaque(false);
		statsPanel.add(namePanel, BorderLayout.CENTER);
	}

	private void initEndTurnButton() {
		JButton endTurnButton = new JButton("END TURN");
		endTurnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prepareForNextPlayer();
				playNextPlayer();
			}
		});
		statsPanel.add(endTurnButton, BorderLayout.LINE_END);
	}
	
	protected void prepareForNextPlayer() {
		Output.println("");
		map.prepareForNextPlayer();
		
	}

	private void initMap() {
		this.map = new Map();
	}

	protected void playNextPlayer() {
		Output.clearPanel();
		activePlayer = players.remove(0);
		map.prepareFor(activePlayer);
		setStatsPanel();
		players.add(activePlayer);
	}

	public void setStatsPanel() {
		this.namePanel.setText(activePlayer + "    " + activePlayer.getMoney() + "$");
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
			String name = (String) Input.promptStringInput("Player " + i + " , please enter your name", "sprites/player.png");
			if (!usedNames.contains(name)) {
				usedNames.add(name);
				i++;
				this.players.add(new Player(name));
				Output.println("Player " + name.toUpperCase() + " added");
				if (players.size() > 1) {
					done = !Input.getBooleanInput("Would you like to add another Player?", "sprites/player.png");
				}
			}
		}
		Output.println("");
		for (Player p : players) {
			Color color = getRandomColor();
			p.setColor(color);
		}
	}

	private void distributeCities() {
		ArrayList<City> cities = new ArrayList<City>(map.getCities());
		while (!cities.isEmpty()) {
			Player activePlayer = this.players.remove(0);
			City chosenCity = (City) Input.promptChooseInput(cities.toArray(), activePlayer.toString() + " , please choose a City", "sprites/city.png");
			Output.println(activePlayer + " chose " + chosenCity);
			cities.remove(chosenCity);
			activePlayer.addCity(chosenCity);
			this.players.add(activePlayer);
		}
		Output.println("");
	}

	private Color getRandomColor() {
		Random gen = new Random();
		return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
	}
	
	public Map getMap() {
		return this.map;
	}

	public JPanel getStatsPanel() {
		return this.statsPanel;
	}

	public JPanel getMapActionPanel() {
		return this.mapActionPanel;
	}
}
