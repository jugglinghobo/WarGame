package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.jgamegrid.*;

/**
 * This abstract Class provides methods for all non-movable Objects that are present on the Map,
 * i. e. City, DefenseWall, FarmingLand, TradingRoute. 
 *
 */
public abstract class MapObject extends Actor{
	
	protected Player player;
	protected City city;
	protected Location location;
	protected int price;
	protected int HP;
	protected JPanel actionPanel;
	protected String name;
	
	public MapObject(String imgPath, Location loc) {
		super(imgPath);
		this.location = loc;
		this.HP = 1;
		initActionPanel();
		show();
	}
	
	private void initActionPanel() {
		actionPanel = new JPanel();
		JButton destroyButton = new JButton("Destroy");
		destroyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelf();
				Output.refreshMap();
			}
		});
		actionPanel.add(destroyButton);
	}
	
	public void offerActions() {
		Output.setInputPanel(actionPanel);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public abstract MapObject copy();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}

	public boolean isAlwaysVisible() {
		return false;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public City getCity() {
		return this.city;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public void setActionPanel(JPanel panel) {
		this.actionPanel = panel;
	}
}
