
import java.awt.*;
import javax.swing.*;

/**
 * The GUI class is responsible for displaying a graphical user interface.
 */
public class GUIExample {
	
//	private  ;
	private JFrame frame;
	private JTextArea textArea;
	private JPanel leftPanel;
	private JScrollPane scrollPane;
	private JButton b1, b2, b3, b4, b5, b6;

	public GUIExample() {
//		this. = ;

		this.frame = new JFrame("Ursuppe");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setExtendedState(Frame.MAXIMIZED_BOTH);  
		this.frame.setVisible(true);
		
		
		this.textArea = new JTextArea();
		this.textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		this.textArea.setEditable(false);
		this.textArea.setVisible(true);
		
		initializeButtons();
//		addActions();
		
		this.leftPanel = new JPanel(new GridLayout(6,0));
		addButtonsToFrame();
		this.leftPanel.setVisible(true);
		double widthPanel = frame.getWidth() / 6;
		this.leftPanel.setPreferredSize(new Dimension((int)widthPanel, 0));
		this.frame.add(leftPanel, BorderLayout.WEST);
		
		this.scrollPane = new JScrollPane(textArea);
		this.scrollPane.setVisible(true);
		double widthScroll = frame.getWidth() / 1.2;
		this.scrollPane.setPreferredSize(new Dimension((int)widthScroll, 0));
		this.frame.add(scrollPane, BorderLayout.EAST);
		this.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.frame.setVisible(true);
		
	}

//	private void addActions() {
//		this.b1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseOne();
//				b1.setEnabled(false);
//				b2.setEnabled(true);
//			}
//		});
//		
//		this.b2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseTwo();
//				b2.setEnabled(false);
//				b3.setEnabled(true);
//			}
//		});
//		
//		this.b3.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseThree();
//				b3.setEnabled(false);
//				b4.setEnabled(true);
//			}
//		});
//		
//		this.b4.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseFour();
//				b4.setEnabled(false);
//				b5.setEnabled(true);
//			}
//		});
////		
////		this.b5.addActionListener(new ActionListener() {
////			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseFive();
//				b5.setEnabled(false);
//				b6.setEnabled(true);
//			}
//		});
//		
//		this.b6.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				playPhaseSix();
//				b6.setEnabled(false);
//				
//				if (IsOver()) {
//					b1.setEnabled(false);
//					Output.println("\n" + getWinner().toString() + " has won the !");
//				} else {
//					b1.setEnabled(true);
//				}
//			}
//		});
//	}

	private void initializeButtons() {
		this.b1 = new JButton("PHASE 1");
		this.b1.setVerticalTextPosition(AbstractButton.CENTER);
		
		this.b2 = new JButton("PHASE 2");
		this.b2.setVerticalTextPosition(AbstractButton.CENTER);
		this.b2.setEnabled(false);
		
		this.b3 = new JButton("PHASE 3");
		this.b3.setVerticalTextPosition(AbstractButton.CENTER);
		this.b3.setEnabled(false);
		
		this.b4 = new JButton("PHASE 4");
		this.b4.setVerticalTextPosition(AbstractButton.CENTER);
		this.b4.setEnabled(false);
		
		this.b5 = new JButton("PHASE 5");
		this.b5.setVerticalTextPosition(AbstractButton.CENTER);
		this.b5.setEnabled(false);
		
		this.b6 = new JButton("PHASE 6");
		this.b6.setVerticalTextPosition(AbstractButton.CENTER);
		this.b6.setEnabled(false);
	}
	
	private void addButtonsToFrame() {
		this.leftPanel.add(b1);
		this.leftPanel.add(b2);
		this.leftPanel.add(b3);
		this.leftPanel.add(b4);
		this.leftPanel.add(b5);
		this.leftPanel.add(b6);		
	}
	
//	protected void playPhaseOne() {
//		.updatePlayerOrder();
//		.phaseOne();	
//	}
//	
//	protected void playPhaseTwo() {
//		.phaseTwo();
//	}
//	
//	protected void playPhaseThree() {
//		.phaseThree();
//	}
//	
//	protected void playPhaseFour() {
//		.phaseFour();
//	}
//	
//	protected void playPhaseFive() {
//		.phaseFive();
//	}
//	
//	protected void playPhaseSix() {
//		.phaseSix();
//	}
//	
//	protected boolean IsOver() {
//		return .isOver();
//	}
//	
//	protected Player getWinner() {
//		return .getWinner();
//	}

	public void sendOutput(String s) {
		textArea.setText(s);
	}

}