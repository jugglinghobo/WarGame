package warGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel inputPanel;
	private JTextPane outputPane;
	private JPanel interactionPanel;
	protected String input = "";
	private JPanel basicActionPanel;
	private GameLogic gameLogic;

	public GUI(GameLogic gameLogic) {
		Output.setOutput(new GuiOutput(this));
		Input.setInput(new GuiInput(this));
		this.gameLogic = gameLogic;
		initFrame();
		gameLogic.setInteractionPanel(this.outputPane, this.inputPanel);
		gameLogic.init();
	}

	private void initFrame() {
		this.frame = new JFrame("WarGame");
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(5, 5)); //GridLayout(0, 2, 5, 5)
		frame.setResizable(true);
		initInteractionPanel();
		initMapPanel();
//		frame.pack();
		frame.setVisible(true);
	}
	
	private void initMapPanel() {
		JPanel mapPanel = new JPanel();
		Map map = gameLogic.getMap();
		mapPanel.add(map);
		frame.add(mapPanel, BorderLayout.EAST);
	}
	
	private void initInteractionPanel() {
		this.interactionPanel = new JPanel();
		interactionPanel.setLayout(new GridLayout(2, 0, 5, 5));
		initOutputPanel();
		initInputPanel();
		frame.add(interactionPanel, BorderLayout.WEST);
	}
	
	private void initOutputPanel() {
		this.outputPane = new JTextPane();
		outputPane.setEditable(false);
		interactionPanel.add(outputPane);
	}
	
	private void initInputPanel() {
		this.inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout(5, 5));
		initBasicActionPanel();
		inputPanel.add(basicActionPanel);
		interactionPanel.add(inputPanel, BorderLayout.SOUTH);
	}
	
	
	private void initBasicActionPanel() {
		this.basicActionPanel = new JPanel();
		JButton cityButton = new JButton(new ImageIcon("sprites/city.png"));
		JButton mapButton = new JButton(new ImageIcon("sprites/mapIcon.png"));
		JButton playerButton = new JButton(new ImageIcon("sprites/player.png"));
		JButton questionButton = new JButton(new ImageIcon("sprites/questionMark.png"));
		JButton endTurn = new JButton("END TURN");
		
		endTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		basicActionPanel.add(cityButton);
		basicActionPanel.add(mapButton);
		basicActionPanel.add(playerButton);
		basicActionPanel.add(questionButton);
		basicActionPanel.add(endTurn);
	}
	

	public void sendOutput(String string) {
		outputPane.setText(string);
	}

	public String getInput() {
		String inputCpy = input.trim();
		input = "";
		return inputCpy;
		
	}

	public int offerBasicActions() {
		return 0;
	}

	public void setInteractionPanel(JPanel panel) {
		frame.remove(interactionPanel);
		this.interactionPanel = panel;
		frame.add(interactionPanel, BorderLayout.WEST);
		frame.validate();
		frame.repaint();
	}
}
