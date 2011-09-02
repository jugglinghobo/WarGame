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
	protected Map map;
	
	public MapObject(String imgPath, Map map, Location loc) {
		super(imgPath);
		this.location = loc;
		this.map = map;
		this.HP = 1;
		initActionPanel();
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
		map.activateMouseListener(false);
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
	
	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setActionPanel(JPanel panel) {
		this.actionPanel = panel;
	}
}
