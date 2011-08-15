package warGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUI implements KeyListener{
	
	private JFrame frame;
	private Map map;
	private JPanel mapPanel;
	private JPanel interactionPanel;
	private JTextArea outputArea;
	private JTextField inputField;
	protected String input = "";

	public GUI(Map map) {
		this.map = map;
		this.frame = new JFrame("WarGame");
		frame.setSize(frame.getMaximumSize());
		frame.setLayout(new GridLayout(0, 2));
		frame.setResizable(true);
		init();
	}

	private void init() {
		initMapPanel();
		initInteractionPanel();
		frame.add(interactionPanel);
		frame.add(mapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initInteractionPanel() {
		this.interactionPanel = new JPanel();
		interactionPanel.setLayout(new GridLayout(2, 0, 5, 5));
		this.outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		JScrollPane scrollOutput = new JScrollPane(outputArea);
		inputField = new JTextField(4);
		inputField.addKeyListener(this);
		
		
		interactionPanel.add(scrollOutput);
		interactionPanel.add(inputField);
	}

	private void initMapPanel() {
		mapPanel = new JPanel();
		mapPanel.add(map);
	}

	public void sendOutput(String string) {
		outputArea.append(string);
		outputArea.setCaretPosition(outputArea.getDocument().getLength());
	}

	public String getInput() {
		String inputCpy = input.trim();
		input = "";
		return inputCpy;
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		int key = evt.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			this.input = inputField.getText();
			inputField.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {
	}

	@Override
	public void keyTyped(KeyEvent evt) {
	}

}
