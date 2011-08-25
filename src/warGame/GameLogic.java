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
		initMapActionPanel();
	}

	private void initMapActionPanel() {
		this.mapActionPanel = new JPanel();
		JButton defWallButton = new JButton(new ImageIcon("sprites/wall.png"));
		defWallButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.build(new DefenseWall(null));
			}
		});
		JButton farmLandButton = new JButton(new ImageIcon("sprites/farmingLand.png"));
		farmLandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.build(new FarmingLand(null));
			}
		});
		JButton tradingRouteButton = new JButton(new ImageIcon("sprites/tradingRoute.png"));
		tradingRouteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.buildTradingRoute();
			}
		});
		JButton clearMapButton = new JButton(new ImageIcon("sprites/mapIcon.png"));
		clearMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.clearMap();
			}
		});
		mapActionPanel.add(defWallButton);
		mapActionPanel.add(farmLandButton);
		mapActionPanel.add(tradingRouteButton);
		mapActionPanel.add(clearMapButton);
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
		this.namePanel.setText(activePlayer.getName() + "    " + activePlayer.getMoney() + "$");
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
			if (!usedNames.contains(name)) {
				usedNames.add(name);
				i++;
				this.players.add(new Player(name));
				Output.println("Player " + name.toUpperCase() + " added");
				if (players.size() > 1) {
					done = !getBooleanInput("Would you like to add another Player?", "sprites/player.png");
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
			City chosenCity = chooseInput(cities, activePlayer + " , please choose a City", "sprites/city.png");
			Output.println(activePlayer + " chose " + chosenCity);
			cities.remove(chosenCity);
			activePlayer.addCity(chosenCity);
			this.players.add(activePlayer);
		}
		Output.println("");
	}
	
	
	private Object getInput(String string, String iconPath) {
		Object input = null;
		while (input == null) {
			input = JOptionPane.showInputDialog(null, string, "NEW PLAYER", JOptionPane.QUESTION_MESSAGE, new ImageIcon(iconPath), null, null);
		}
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
