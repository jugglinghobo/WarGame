package warGame;

import java.awt.*;

import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JTextPane outputPane;
	private JPanel interactionPanel;
	protected String input = "";
	private GameLogic gameLogic;
	private JPanel actionPanel;
	private Map map;
	
	
	/*
	 * This is the Deal:
	 * We have one Frame
	 * this frame contains Two things:
	 *  - the Map
	 *  - the Interaction Panel
	 *  
	 *  The interactionPanel contains two Things:
	 *   - the Output
	 *   - the cityActionPanel, which is by default empty and changed if the user clicks on a city.
	 *   
	 *   The cityActionPanel contains two Things: 
	 *    - the City Stats
	 *    - the city Buttons
	 *   
	 *   That's all!
	 * 
	 */

	public GUI(GameLogic gameLogic) {
		new Output(this);
		this.gameLogic = gameLogic;
		initFrame();
		gameLogic.init();
		gameLogic.playNextPlayer();
	}

	private void initFrame() {
		this.frame = new JFrame();
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(5, 5));
		frame.setResizable(true);
		initMapPanel();
		initInteractionPanel();
		frame.setVisible(true);
	}

	private void initMapPanel() {
		map = gameLogic.getMap();
		frame.add(map, BorderLayout.LINE_END);
	}
	
	private void initInteractionPanel() {
		this.interactionPanel = new JPanel();
		interactionPanel.setLayout(new GridLayout(4, 1, 5, 5));
		initOutputPanel();
		initCityActionPanel();
		frame.add(interactionPanel, BorderLayout.WEST);
	}
	
	private void initOutputPanel() {
		this.outputPane = new JTextPane();
		outputPane.setPreferredSize(new Dimension(100, 100));
		outputPane.setEditable(false);
		outputPane.setOpaque(false);
		interactionPanel.add(outputPane);
	}
	
	/*
	 * This is the Panel in which the different Cities display their offers
	 */
	private void initCityActionPanel() {
		this.actionPanel = new JPanel();
		interactionPanel.add(actionPanel);
	}
	

	public void sendOutput(String string) {
		outputPane.setText(string);
	}

	public void setActionPanel(JPanel panel) {
		interactionPanel.remove(actionPanel);
		this.actionPanel = panel;
		interactionPanel.add(this.actionPanel);
		this.actionPanel.setVisible(true);
		frame.validate();
		frame.repaint();
	}

	public void resetInteractionPanel() {
		this.actionPanel.setVisible(false);
		frame.validate();
		frame.repaint();
		
	}

	public void refreshMap() {
		this.map.refresh();
	}
}

