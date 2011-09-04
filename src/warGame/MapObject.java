package warGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import sun.awt.SubRegionShowable;

import ch.aplu.jgamegrid.*;

/**
 * This abstract Class provides methods for all non-movable Objects that are present on the Map,
 * i. e. City, DefenseWall, FarmingLand, TradingRoute. 
 *
 */
public abstract class MapObject extends Actor{
	
	protected Player player;
	protected Map map;
	private City city;
	private Location location;
	private int price;
	private int HP;
	private JPanel actionPanel;
	protected String name;
	
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
				map.refresh();
			}
		});
		actionPanel.add(destroyButton);
	}
	
	public void offerActions() {
		map.activateMouseListener(false);
		Output.setInputPanel(actionPanel);
	}
	
	public abstract MapObject copy();

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public void setPrice(int price) {
		this.price = price;
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
	
	@Override
	public void setLocation(Location loc) {
		this.location = loc;
		super.setLocation(this.location);
	}
	
	@Override
	public Location getLocation() {
		return location;
	}
	
	public void setActionPanel(JPanel panel) {
		this.actionPanel = panel;
	}
	
	public JPanel getActionPanel() {
		return this.actionPanel;
	}

	public void hit(int attackPoints) {
		Output.println(name + " is attacked");
		subtractFromHP(attackPoints);
		if (this.HP <= 0) {
			Output.println(name + " was destroyed!");
			removeSelf();
		}
		map.refresh();
	}
	
	public void subtractFromHP(int attack) {
		this.HP -= attack;
	}
}
