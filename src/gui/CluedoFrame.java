package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import Board.Player;

public class CluedoFrame extends JFrame implements WindowListener {

	public JFrame cluedoFrame;
	public JButton assumption,accusation,leave,cards,notes;
	public JTextField textField1;
	public JTextArea textArea1;
	public JPanel selectionPanel;
	public JRadioButton missScarlett, colonelMustard, professorPlum, reverendGreen, mrsWhite, mrsPeacock;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar;
	private JMenuItem exit;
	private JMenuItem showCards;
	private JMenuItem showHelp;

	public static void main(String[] args) {
		new CluedoFrame();

	}

	public CluedoFrame() {
		// Creates Frame.
		super("CLUEDO");
		this.cluedoFrame = this;
		JOptionPane.showMessageDialog(null,  "Welcome to Cluedo!\nMade by Connor Moot and Callum Crosby");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		
		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		// Panel stuff.
		JPanel panelOne = new JPanel(); // is a "Section" on the frame
		JLabel labelOne = new JLabel("<html>hello world blah blah<br> more shit down here</html>");
		labelOne.setToolTipText("This shows when hovering");

		// Buttons.
		assumption = new JButton("Assumption");
		assumption.setContentAreaFilled(false);
		assumption.setText("Assumption");
		assumption.setToolTipText("Click here to make an assumption");
		ListenForButton lForButton = new ListenForButton();
		assumption.addActionListener(lForButton);

		// Text Field.
		textField1 = new JTextField("TextField", 15);
		textField1.setColumns(10);
		textField1.setText("Type again");

		// Text Area.
		textArea1 = new JTextArea(15, 20);
		textArea1.setText("TextArea");
		textArea1.setLineWrap(true);
		JScrollPane scrollBar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea1.setWrapStyleWord(true);// Words dont span on other lines
		textArea1.requestFocus();// Highlights on open

		// RadioButtons.
		missScarlett = new JRadioButton("missScarlett");
		professorPlum = new JRadioButton("professorPlum");
		colonelMustard = new JRadioButton("colonelMustard");
		mrsPeacock = new JRadioButton("mrsPeacock");
		reverendGreen = new JRadioButton("reverendGreen");
		mrsWhite = new JRadioButton("mrsWhite");
		selectionPanel = new JPanel();
		Border selectionBorder = BorderFactory.createTitledBorder("Character");
		selectionPanel.setBorder(selectionBorder);
		selectionPanel.add(missScarlett);
		selectionPanel.add(professorPlum);
		selectionPanel.add(colonelMustard);
		selectionPanel.add(mrsPeacock);
		selectionPanel.add(reverendGreen);
		selectionPanel.add(mrsWhite);
		
		// Menu.
		setUpMenu();
		
		// Connecting Components.panelOne
		panelOne.add(assumption);
		panelOne.add(textField1);
		panelOne.add(labelOne); // adds label to "Section" one
		panelOne.add(scrollBar1);
		panelOne.add(selectionPanel);// adds radio buttons
		this.add(panelOne);
		this.setVisible(true); // makes current window visable
		ListenForMouse lForMouse = new ListenForMouse();
		panelOne.addMouseListener(lForMouse);

	}
	/**
	 * initializes the Jmenu
	 */
	private void setUpMenu() {
		jMenuBar = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jMenu1.setText("Options");
		exit = new JMenuItem("Exit");
		showCards = new JMenuItem("Cards");
		showHelp = new JMenuItem("Help");
		
		//setup Listener
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int r = JOptionPane.showConfirmDialog(cluedoFrame, new JLabel(
						"Are you sure you wish to exit?"), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		
		//setup Listener
		showHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFrame helpFrame = new JFrame("HELP");
				JPanel helpPanel = new JPanel();
				//sets up button
				JButton close = new JButton("Close");
				close.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event) {
						helpFrame.dispose();
					}
				});
				
				JLabel text = new JLabel("<html>Move around the board using the arrow keys<br>"
						+ "You cannot make an assumption if you are<br>"
						+ " not in a room. If you make an accusation<br>"
						+ " and you are wrong you will be removed<br>"
						+ " from the game. However if you are correct<br>"
						+ " Then you win the game. To leave a room<br>"
						+ " press the leave button.</html>");
				
				//set to pop up
				helpPanel.add(text);
				helpPanel.add(close);
				helpFrame.add(helpPanel);
				helpFrame.pack();
				helpFrame.setLocationRelativeTo(cluedoFrame);
				helpFrame.setVisible(true);
			}
		});
		
		//add to JMenus
		jMenu1.add(exit);
		jMenu1.add(showHelp);
		jMenuBar.add(jMenu1);
		this.setJMenuBar(jMenuBar);
		
	}

	/**
	 * returns the number of the user enters
	 * 
	 * @return
	 */
	public int numPlayers() {
		Object[] nums = { 3, 4, 5, 6 };
		int num = (int) JOptionPane.showInputDialog(this, "Select Number of players", "Number of players",
				JOptionPane.QUESTION_MESSAGE, null, nums, nums[0]);
		System.out.println(num);
		return num;

	}

	/**
	 * 
	 * brings up a JWindow with selection via radio button, when one is selected the option is greyed out.
	 */
	public void selectCharacters(List<Player> players) {
		for (Player p : players) {
			JOptionPane.showInputDialog(null,selectionPanel);
			p.setName("New name");
		}

	}

	// *------------------LISTENERS----------------------*//

	// MOUSE
	class ListenForMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			textArea1.append("Xpos: " + x + " Ypos:" + y + " ");

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// BUTTON
	class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == assumption) {
				textArea1.append("button1 pressed");
				JOptionPane.showMessageDialog(CluedoFrame.this, "you have pressed the button", "title of message",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	// WINDOW
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// Ask the user to confirm they wanted to do this
		int r = JOptionPane.showConfirmDialog(this, new JLabel("Exit Cluedo?"), "Confirm Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
}
