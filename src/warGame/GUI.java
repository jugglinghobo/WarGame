package warGame;

import java.awt.*;

import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel inputPanel;
	private JTextPane outputPane;
	private JPanel interactionPanel;
	protected String input = "";
	private GameLogic gameLogic;
	private JPanel statsPanel;
	private JPanel mapObjectPanel;
	private Map map;

	public GUI(GameLogic gameLogic) {
		Output.setOutput(new GuiOutput(this));
		Input.setInput(new GuiInput(this));
		this.gameLogic = gameLogic;
		initFrame();
		gameLogic.init();
		gameLogic.playNextPlayer();
	}

	private void initFrame() {
		this.frame = new JFrame("WarGame");
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(5, 5));
		frame.setResizable(true);
		initStatsPanel();
		initMapPanel();
		initInteractionPanel();
		frame.setVisible(true);
	}
	
	private void initStatsPanel() {
		this.statsPanel = gameLogic.getStatsPanel();
		this.frame.add(statsPanel, BorderLayout.PAGE_START);
	}

	private void initMapPanel() {
		map = gameLogic.getMap();
		frame.add(map, BorderLayout.LINE_END);
	}
	
	private void initInteractionPanel() {
		this.interactionPanel = new JPanel();
		interactionPanel.setLayout(new GridLayout(4, 1, 5, 5));
		initOutputPanel();
		initMapObjectPanel();
		initMapActionPanel();
		frame.add(interactionPanel, BorderLayout.LINE_START);
	}
	
	private void initOutputPanel() {
		this.outputPane = new JTextPane();
		outputPane.setPreferredSize(new Dimension(100, 100));
		outputPane.setEditable(false);
		outputPane.setOpaque(false);
		interactionPanel.add(outputPane);
	}
	
	/*
	 * This is the Panel in which the different MapObjects display their offers
	 */
	private void initMapObjectPanel() {
		this.mapObjectPanel = new JPanel();
		initInputPanel();
		interactionPanel.add(mapObjectPanel);
	}
	
	private void initInputPanel() {
		this.inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout(5, 5));
		mapObjectPanel.add(inputPanel, BorderLayout.SOUTH);
	}
	
	
	private void initMapActionPanel() {
		JPanel mapActionPanel = gameLogic.getMapActionPanel();
		interactionPanel.add(mapActionPanel);
	}
	

	public void sendOutput(String string) {
		outputPane.setText(string);
	}

	public String getInput() {
		String inputCpy = input.trim();
		input = "";
		return inputCpy;
		
	}

	public void setInputPanel(JPanel panel) {
		mapObjectPanel.remove(inputPanel);
		this.inputPanel = panel;
		mapObjectPanel.add(this.inputPanel);
		this.inputPanel.setVisible(true);
		frame.validate();
		frame.repaint();
	}

	public void updateStats() {
		gameLogic.setStatsPanel();
		frame.validate();
		frame.repaint();
	}

	public void resetInteractionPanel() {
		this.inputPanel.setVisible(false);
		frame.validate();
		frame.repaint();
		
	}

	public void refreshMap() {
		this.map.refresh();
		
	}
}

