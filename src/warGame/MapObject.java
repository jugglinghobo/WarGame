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
	
	Player player;
	Location location;
	int price;
	int HP;
	JPanel inputPanel;
	String name;
	
	public MapObject(String imgPath, Location loc) {
		super(imgPath);
		this.location = loc;
		this.HP = 1;
		initInputPanel();
		show();
	}
	
	private void initInputPanel() {
		inputPanel = new JPanel();
		JButton destroyButton = new JButton("Destroy");
		destroyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelf();
				Output.refreshMap();
			}
		});
		inputPanel.add(destroyButton);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setLocation(Location loc) {
		location = loc;
	}
	
	public abstract Location getLocation();
	
	public void offerActions() {
		Output.setInputPanel(inputPanel);
	}
	
	public abstract MapObject copy();
	
	public String toString() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isAlwaysVisible() {
		return false;
	}
}
