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

import Board.Board;


import Board.Board;
import Board.Game;

import Board.Player;

public class CluedoFrame extends JFrame implements WindowListener {

	public JFrame cluedoFrame;
	public JButton assumption,accusation,leave,cards,notes;
	public JTextField textField1;
	public JTextArea textArea1;
	public JPanel selectionPanel;
	public JPanel[][] tiles;
	public JRadioButton missScarlett, colonelMustard, professorPlum, reverendGreen, mrsWhite, mrsPeacock;
	public Game game;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar;
	private JMenuItem exit;
	private JMenuItem showCards;
	private JMenuItem showHelp;

	public CluedoFrame() {
		// Creates Frame.
		super("CLUEDO");
		this.cluedoFrame = this;
		this.setLayout(new BorderLayout());
		JOptionPane.showMessageDialog(null,  "Welcome to Cluedo!\nMade by Connor Moot and Callum Crosby");

		this.setSize(600, 600);

		this.setSize(500, 500);

		this.setLocationRelativeTo(null);
		
		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		// Panel stuff.
		JPanel topPanel = new JPanel(); // is a "Section" on the frame
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new GridLayout(4,1));
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		
		JLabel labelOne = new JLabel("<html>hello world blah blah<br> more shit down here</html>");
		labelOne.setToolTipText("This shows when hovering");

		// Buttons.
		assumption = new JButton("Assumption");
		assumption.setContentAreaFilled(false);
		assumption.setText("Assumption");
		assumption.setToolTipText("Click here to make an assumption");
		
		accusation = new JButton("Accusation");
		accusation.setContentAreaFilled(false);
		accusation.setText("Accusation");
		accusation.setToolTipText("Click here to make an accusation");
		
		leave = new JButton("Leave");
		leave.setContentAreaFilled(false);
		leave.setText("Leave");
		leave.setToolTipText("Click here to Leave a room");
		
		cards = new JButton("Card");
		cards.setContentAreaFilled(false);
		cards.setText("Card");
		cards.setToolTipText("Click here to display your cards");
		
		notes = new JButton("Notes");
		notes.setContentAreaFilled(false);
		notes.setText("Notes");
		notes.setToolTipText("Click here to display your notes");
		
		ListenForButton lForButton = new ListenForButton();
		assumption.addActionListener(lForButton);
		accusation.addActionListener(lForButton);
		leave.addActionListener(lForButton);
		

		// RadioButtons.
		setupRadioButtons(lForButton);
		
		// Menu.
		setUpMenu();
		
		// Connecting Components.bottomPanel
		bottomLeftPanel.add(assumption);
		bottomLeftPanel.add(accusation);
		bottomLeftPanel.add(leave);

		bottomPanel.add(cards);
		bottomPanel.add(notes);

		bottomLeftPanel.add(cards);
		JTextArea notes = new JTextArea(10,10);
		notes.setText("Notes...");
		notes.setLineWrap(true);
		JScrollPane notesScroll = new JScrollPane(notes,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bottomPanel.add(notesScroll, BorderLayout.EAST);
		bottomPanel.add(bottomLeftPanel,BorderLayout.WEST);

		
		//Create Board
		if(game == null){
			System.out.println("fuck you");
		}
		/*drawBoard(game.board);
		JPanel gridPanel = new JPanel(new GridLayout(25, 25, 1, 1));
    	for(int i = 0; i <25 ; i++){
    		for(int j = 0; j < 25; j++){
    			gridPanel.add(tiles[i][j]);
    		}
    	}
		centerPanel.add(gridPanel);*/
		// Connecting Components.topPanel
		topPanel.add(labelOne); // adds label to "Section" one
		this.add(topPanel,BorderLayout.NORTH);
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.add(bottomLeftPanel,BorderLayout.SOUTH);
		this.add(leftPanel,BorderLayout.EAST);
		this.add(rightPanel,BorderLayout.WEST);
		this.add(centerPanel,BorderLayout.CENTER);
		this.setVisible(true); // makes current window visable
		ListenForMouse lForMouse = new ListenForMouse();
		centerPanel.addMouseListener(lForMouse);

	}

	/**
	 * initializes radio buttons and adds listeners
	 * @param lForButton
	 */
	private void setupRadioButtons(ListenForButton lForButton) {
		
		missScarlett = new JRadioButton("missScarlett");
		professorPlum = new JRadioButton("professorPlum");
		colonelMustard = new JRadioButton("colonelMustard");
		mrsPeacock = new JRadioButton("mrsPeacock");
		reverendGreen = new JRadioButton("reverendGreen");
		mrsWhite = new JRadioButton("mrsWhite");
		ButtonGroup group = new ButtonGroup();
		group.add(missScarlett);
		group.add(professorPlum);
		group.add(colonelMustard);
		group.add(mrsPeacock);
		group.add(reverendGreen);
		group.add(mrsWhite);
		missScarlett.addActionListener(lForButton);
		professorPlum.addActionListener(lForButton);
		colonelMustard.addActionListener(lForButton);
		mrsPeacock.addActionListener(lForButton);
		reverendGreen.addActionListener(lForButton);
		mrsWhite.addActionListener(lForButton);
		selectionPanel = new JPanel();
		Border selectionBorder = BorderFactory.createTitledBorder("Character");
		selectionPanel.setBorder(selectionBorder);
		selectionPanel.add(missScarlett);
		selectionPanel.add(professorPlum);
		selectionPanel.add(colonelMustard);
		selectionPanel.add(mrsPeacock);
		selectionPanel.add(reverendGreen);
		selectionPanel.add(mrsWhite);
		
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
				JPanel helpPaneltop = new JPanel();
				JPanel helpPanelbottom = new JPanel();
				helpFrame.setLayout(new BorderLayout());
				//sets up button
				JButton close = new JButton("Close");
				close.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event) {
						helpFrame.dispose();
					}
				});
				
				JLabel text = new JLabel("<html>Move around the board using the arrow keys<br><br>"
						+ "You cannot make an assumption if you are<br>"
						+ " not in a room. If you make an accusation<br>"
						+ " and you are wrong you will be removed<br>"
						+ " from the game. However if you are correct<br>"
						+ " Then you win the game.<br><br> To leave a room"
						+ " press the leave button.</html>");
				
				//set to pop up
				helpPaneltop.add(text);
				helpPanelbottom.add(close);
				helpFrame.add(helpPaneltop,BorderLayout.NORTH);
				helpFrame.add(helpPanelbottom,BorderLayout.SOUTH);
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
	
	//2d array of jpanels
	//make each cell a new jpanel
	//give each panel a jlabel
	//give it an image icon based on the room tile
	//
	
	public void drawBoard(Board board){
		JPanel[][] tiles = new JPanel[25][25];
		for(int x = 0; x < 25; x++){
			for(int y = 0; y < 25; y++){
				tiles[x][y] = new JPanel();
				JLabel label = new JLabel();
				label.setBackground(Color.DARK_GRAY);
				tiles[x][y].add(label);
				label.setIcon(board.getTiles()[x][y].getIcon());
				
				label.setVisible(true);
			}
		}
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
			System.out.println(("Xpos: " + x + " Ypos:" + y + " "));

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
				JOptionPane.showMessageDialog(CluedoFrame.this, "Select cards for assumption", "Assumption",
						JOptionPane.PLAIN_MESSAGE);
			}
			else if(e.getSource()==accusation){
				JOptionPane.showMessageDialog(CluedoFrame.this, "Select cards for accusation", "Accusation",
						JOptionPane.PLAIN_MESSAGE);
			}
			else if(e.getSource()==leave){
				JOptionPane.showMessageDialog(CluedoFrame.this, "Select a door to leave from", "Leave",
						JOptionPane.PLAIN_MESSAGE);
			}
			else if(e.getSource()==cards){
				JOptionPane.showMessageDialog(CluedoFrame.this, "Your cards are:", "Card",
						JOptionPane.PLAIN_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(CluedoFrame.this, "not a button?", "hello?",
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
