package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import warGame.City.Building;

public class CityActionPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private City city;
	private JPanel panel;
	private Map map;
	private JTextPane statsArea;
	
	public CityActionPanel(City city) {
		this.city = city;
		this.map = city.getMap();
		initPanel();
	}
	
	public JPanel getPanel() {
		return this.panel;
	}

	private void initPanel() {
		this.panel = new JPanel();
		initStats();
		initButtons();
//		panel.setSize(300, 300);
	}
	
	private void initStats() {
		this.statsArea = new JTextPane();
		statsArea.setOpaque(false);
		statsArea.setText(city.getStats());
		panel.add(statsArea);
	}
	
	public void updateStats() {
		statsArea.setText(city.getStats());
	}

	private void initButtons() {
		/*
		 * needs: - Casern - Forge - Soldier - Knight - leave Warriors - Trading Route - Farming Land - Defense Wall
		 */
		JButton casernButton = new JButton("Casern");
		casernButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				city.buildBuilding(Building.CASERN);
				updateStats();
			}
		});
		JButton forgeButton = new JButton("Forge");
		forgeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				city.buildBuilding(Building.FORGE);
				updateStats();
			}
		});
		JButton soldierButton = new JButton("Soldier");
		soldierButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Input.promptIntegerInput("How many do you want to make?");
				if (number != 0) {
					city.create(new Soldier(city.player, city.map, city), number);
				}
				updateStats();
			}
		});
		JButton knightButton = new JButton("Knight");
		knightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Input.promptIntegerInput("How many do you want to make?");
				if (number != 0) {
					city.create(new Knight(city.player, city.map, city), number);
				}
				updateStats();
			}
		});
		JButton leaveWarriorButton = new JButton("leave Warriors");
		leaveWarriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] warriors = {new Knight(city.player, city.map, city), new Soldier(city.player, city.map, city)};
				Warrior w = (Warrior) Input.promptChooseInput(warriors, "what kind of Warriors would do you want to leave?", "leave Warriors");
				if (w != null) {
					int number = Input.promptIntegerInput("How many warriors do you want to leave?");
					if (number > 0) {
						city.leaveWarriors(w, number);
					}
				}
				updateStats();
			}
		});
		JButton defWallButton = new JButton(new ImageIcon("sprites/wall.png"));
		defWallButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				city.build(new DefenseWall(map, city.getLocation()));
				updateStats();
			}
		});
		JButton farmLandButton = new JButton(new ImageIcon("sprites/farmingLand.png"));
		farmLandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				city.build(new FarmingLand(map, city.getLocation()));
				updateStats();
			}
		});
		JButton tradingRouteButton = new JButton(new ImageIcon("sprites/tradingRoute.png"));
		tradingRouteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				city.buildTradingRoute();
				updateStats();
			}
		});
		JButton clearMapButton = new JButton(new ImageIcon("sprites/mapIcon.png"));
		clearMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.clearMap();
				updateStats();
			}
		});
		panel.add(defWallButton);
		panel.add(farmLandButton);
		panel.add(tradingRouteButton);
		panel.add(clearMapButton);
		panel.add(casernButton);
		panel.add(forgeButton);
		panel.add(soldierButton);
		panel.add(knightButton);
		panel.add(leaveWarriorButton);
	}

}
