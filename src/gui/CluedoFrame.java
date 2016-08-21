package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import Board.Board;


import Board.Game;

import Board.Player;
import cards.RoomCard;

public class CluedoFrame extends JFrame implements WindowListener {

	public JFrame cluedoFrame;
	private int num;
	public JButton assumption, accusation, leave, cards, notes;
	public JTextField textField1;
	public JLabel die = new JLabel();
	public JTextArea textArea1;
	public JPanel selectionPanel;
	public JPanel[][] tiles;
	public JRadioButton missScarlett, colonelMustard, professorPlum, reverendGreen, mrsWhite, mrsPeacock;
	public JLabel labelOne = new JLabel("");
	public Game game;
	public Board board;
	public JPanel topPanel = new JPanel(); // is a "Section" on the frame
	public JPanel bottomPanel = new JPanel();
	public JPanel bottomLeftPanel = new JPanel();
	public JPanel leftPanel = new JPanel();
	public JPanel rightPanel = new JPanel();
	public JPanel centerPanel = new JPanel();
	public int numRoom;
	public int numWeapon;
	public int numCharacter;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar;
	private JMenuItem exit;
	private JMenuItem showCards;
	private JMenuItem showHelp;
	private String pName;
	private String uName;

	public CluedoFrame() {
		// Creates Frame.
		super("CLUEDO");
		this.cluedoFrame = this;
		this.setLayout(new BorderLayout());
		JOptionPane.showMessageDialog(null, "Welcome to Cluedo!\nMade by Connor Moot and Callum Crosby");

		this.setSize(800, 950);

		this.setLocationRelativeTo(null);

		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// Panel stuff.
		bottomPanel.setLayout(new BorderLayout());
		bottomLeftPanel.setLayout(new GridLayout(4, 1));


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

		cards = new JButton("Cards");
		cards.setContentAreaFilled(false);
		cards.setText("Cards");
		cards.setToolTipText("Click here to display your cards");

		notes = new JButton("Notes");
		notes.setContentAreaFilled(false);
		notes.setText("Notes");
		notes.setToolTipText("Click here to display your notes");

		itemListener iListen = new itemListener();
		ListenForButton lForButton = new ListenForButton();
		
		assumption.addActionListener(lForButton);
		accusation.addActionListener(lForButton);
		cards.addActionListener(lForButton);
		leave.addActionListener(lForButton);
		
		keyListener kListen = new keyListener();

		// RadioButtons.
		setupRadioButtons(iListen);

		// Menu.
		setUpMenu();

		// Connecting Components.bottomPanel
		bottomLeftPanel.add(assumption);
		bottomLeftPanel.add(accusation);
		bottomLeftPanel.add(leave);

		bottomPanel.add(cards);
		// bottomPanel.add(notes);

		bottomLeftPanel.add(cards);
		JTextArea notes = new JTextArea(10, 10);
		notes.setText("Notes...");
		notes.setLineWrap(true);
		JScrollPane notesScroll = new JScrollPane(notes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bottomPanel.add(notesScroll, BorderLayout.EAST);
		bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

		// Connecting Components.topPanel
		topPanel.add(labelOne);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);
		this.add(leftPanel, BorderLayout.EAST);
		this.add(rightPanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true); // makes current window visable
		ListenForMouse lForMouse = new ListenForMouse();
		centerPanel.addMouseListener(lForMouse);
		
		bottomPanel.addKeyListener(kListen);
		bottomPanel.setFocusable(true);
		bottomPanel.requestFocus();
	}
	
	public void setGameUp(Game game){
		this.game=game;
	}

	/**
	 * initializes radio buttons and adds listeners
	 * 
	 * @param iListen
	 */
	private void setupRadioButtons(itemListener iListen) {

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
		missScarlett.addItemListener(iListen);
		professorPlum.addItemListener(iListen);
		colonelMustard.addItemListener(iListen);
		mrsPeacock.addItemListener(iListen);
		reverendGreen.addItemListener(iListen);
		mrsWhite.addItemListener(iListen);
		selectionPanel = new JPanel();
		Border selectionBorder = BorderFactory.createTitledBorder("Please pick a character");
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

		// setup Listener
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int r = JOptionPane.showConfirmDialog(cluedoFrame, new JLabel("Are you sure you wish to exit?"),
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		// setup Listener
		showHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFrame helpFrame = new JFrame("HELP");
				JPanel helpPaneltop = new JPanel();
				JPanel helpPanelbottom = new JPanel();
				helpFrame.setLayout(new BorderLayout());
				// sets up button
				JButton close = new JButton("Close");
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						helpFrame.dispose();
					}
				});

				JLabel text = new JLabel("<html>Move around the board using the w,a,s and d keys<br><br>"
						+ "You cannot make an assumption if you are<br>"
						+ " not in a room. If you make an accusation<br>" + " and you are wrong you will be removed<br>"
						+ " from the game. However if you are correct<br>"
						+ " Then you win the game.<br><br> To leave a room" + " press the leave button.<br>"
						+ " Click on the board to find out information about the rooms</html>");

				// set to pop up
				helpPaneltop.add(text);
				helpPanelbottom.add(close);
				helpFrame.add(helpPaneltop, BorderLayout.NORTH);
				helpFrame.add(helpPanelbottom, BorderLayout.SOUTH);
				helpFrame.pack();
				helpFrame.setLocationRelativeTo(cluedoFrame);
				helpFrame.setVisible(true);
			}
		});

		// add to JMenus
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
		 num = (int) JOptionPane.showInputDialog(this, "Select Number of players", "Number of players",
				JOptionPane.QUESTION_MESSAGE, null, nums, nums[0]);
		//System.out.println(num);
		return num;
	}

	// 2d array of jpanels
	// make each cell a new jpanel
	// give each panel a jlabel
	// give it an image icon based on the room tile
	//

	public void drawBoard(Board board) {
		this.board=board;
		centerPanel.removeAll();
		JPanel[][] tiles = new JPanel[25][25];
		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 25; y++) {
				tiles[x][y] = new JPanel();
				JLabel label = new JLabel();
				label.setBackground(Color.DARK_GRAY);
				tiles[x][y].add(label);
				if (board.getTiles()[x][y] != null) {
					if (board.getTiles()[x][y].print().equals("s")) {
						label.setIcon(new ImageIcon("spawn.png"));
					}else{
					label.setIcon(board.getTiles()[x][y].getIcon());
						if(board.getTiles()[x][y].print() == "1"){
							label.setToolTipText("miss Scarlett");
						}else if(board.getTiles()[x][y].print() == "2"){
							label.setToolTipText("professorPlum");
						}else if(board.getTiles()[x][y].print() == "3"){
							label.setToolTipText("mrsPeacock");
						}else if(board.getTiles()[x][y].print() == "4"){
							label.setToolTipText("reverendGreen");
						}else if(board.getTiles()[x][y].print() == "5"){
							label.setToolTipText("colonelMustard");
						}else if(board.getTiles()[x][y].print() == "6"){
							label.setToolTipText("mrsWhite");
						}
					}
				} else {
					label.setIcon(new ImageIcon("wall.png"));
				}

				label.setVisible(true);
			}
		}

		JPanel gridPanel = new JPanel(new GridLayout(25, 25, -5, -5));
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				gridPanel.add(tiles[i][j]);
			}
		}
		centerPanel.add(gridPanel);
		this.add(centerPanel);
		this.setVisible(true);
		
		die.setText("         Moves left: " + String.valueOf(game.roll));
		bottomPanel.add(die);
		die.setVisible(true);
	}

	/**
	 * 
	 * brings up a JWindow with selection via radio button, when one is selected
	 * the option is greyed out.
	 */
	public void selectCharacters(List<Player> players) {
		ArrayList<String> charNames = new ArrayList<String>();
		charNames.add("professorPlum");
		charNames.add("missScarlett");
		charNames.add("colonelMustard");
		charNames.add("mrsWhite");
		charNames.add("mrsPeacock");
		charNames.add("reverendGreen");
		//ArrayList charNames = new ArrayList {"professorPlum","missScarlett","colonelMustard","mrsWhite","mrsPeacock","reverendGreen"}();
		for (int i = 0; i<num;i++) {
			JOptionPane.showMessageDialog(null, selectionPanel);
			if(pName.equals("professorPlum")){
				selectionPanel.remove(professorPlum);
				pName="professorPlum";
			}else if(pName.equals("missScarlett")){
				selectionPanel.remove(missScarlett);
				pName="missScarlett";
			}else if(pName.equals("colonelMustard")){
				selectionPanel.remove(colonelMustard);
				pName="colonelMustard";
			}else if(pName.equals("mrsWhite")){
				selectionPanel.remove(mrsWhite);
				pName="mrsWhite";
			}else if(pName.equals("mrsPeacock")){
				selectionPanel.remove(mrsPeacock);
				pName="mrsPeacock";
			}else if(pName.equals("reverendGreen")){
				selectionPanel.remove(reverendGreen);
				pName="reverendGreen";
			}
			uName = JOptionPane.showInputDialog("Please select a username. Leave this blank if this");
			players.get(i).setUser(uName);
			players.get(i).setName(pName);
			charNames.remove(pName);
		}int x = 0;
		for(int i = num; i<6;i++){
			
			players.get(i).setName(charNames.get(x));
			x++;
		}
	}
	
	public void notInRoom(){
		JOptionPane.showMessageDialog(cluedoFrame, "You need to be in a room to do this");
	}
	public void noHold(){
		JOptionPane.showMessageDialog(cluedoFrame, "No player holds these cards");
	}
	public void holdNotice(String p, String c){
		JOptionPane.showMessageDialog(cluedoFrame, p + " holds "+ c);
	}
	public int getRoomNum(){
		return numRoom;
	}
	public int getWeaponNum(){
		return numWeapon;
	}
	public int getCharaterNum(){
		return numCharacter;
	}
	public CluedoFrame getFrame(){
		return this;
	}
	
	/**
	 * changes the label at the top of the screen
	 */
	public void changeLabel(String name){
		labelOne.setText(name+"'s turn");
	}
	
	public void showLoss(){
		JOptionPane.showMessageDialog(cluedoFrame, "Incorrect Guess, You are out of the game");
	}
	public String pickDoor(String[] doors){
		Object[] nums = doors;
		String num = (String) JOptionPane.showInputDialog(this, "Select door to leave from", "Pick door",
				JOptionPane.QUESTION_MESSAGE, null, nums, nums[0]);
		return num;

	}

	// *------------------LISTENERS----------------------*//
	//----------------------------------------------------//
	//----------------------------------------------------//

	// MOUSE
	class ListenForMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.println(("Xpos: " + x + " Ypos:" + y + " "));
			
			//KITCHEN
			if(x>75 && x<241 && y>10 && y<150){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the kitchen");
			}
			if(x>271 && x<489 && y>62 && y<204){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the Ball Room");
			}
			if(x>548 && x<689 && y>11 && y<129){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the Conservatory");
			}
			if(x>75 && x<263 && y>260 && y<403){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the dining room");
			}
			if(x>547 && x<692 && y>211 && y<326){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the billiard room");
			}
			if(x>75 && x<238 && y>487 && y<627){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the library");
			}
			if(x>291 && x<464 && y>459 && y<629){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the Hall");
			}
			if(x>521 && x<688 && y>535 && y<630){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the Study");
			}
			if(x>525 && x<688 && y>389 && y<453){
				JOptionPane.showMessageDialog(cluedoFrame, "This is the Lounge");
			}

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
	
	class keyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_W){
				//System.out.println("up");
				game.move("up");
				//game.dir="up";
			}else if(e.getKeyCode()==KeyEvent.VK_S){
				game.move("down");
				//game.dir="down";
			}else if(e.getKeyCode()==KeyEvent.VK_D){
				game.move("right");
				//game.dir="right";
			}else if(e.getKeyCode()==KeyEvent.VK_A){
				game.move("left");
				//game.dir="left";
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class itemListener implements ItemListener { 

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getSource() == professorPlum) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Professor Plum. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="professorPlum";
					//selectionPanel.remove(professorPlum);
				} else if (e.getSource() == missScarlett) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Miss Scarlett. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="missScarlett";
					//selectionPanel.remove(missScarlett);
				} else if (e.getSource() == colonelMustard) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Colonel Mustard. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="colonelMustard";
					//selectionPanel.remove(colonelMustard);
				} else if (e.getSource() == mrsWhite) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Mrs White. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="mrsWhite";
					//selectionPanel.remove(mrsWhite);
				} else if (e.getSource() == reverendGreen) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Reverend Green. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="reverendGreen";
					//selectionPanel.remove(reverendGreen);
				} else if (e.getSource() == mrsPeacock) {
				//	JOptionPane.showMessageDialog(CluedoFrame.this, "You've selected Mrs Peacock. Please hit 'OK' to confirm.", "Notice", JOptionPane.PLAIN_MESSAGE);
					pName="mrsPeacock";
					//selectionPanel.remove(mrsPeacock);
				}
			}
		}
	}

	// BUTTON
	class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == assumption) {
				JOptionPane.showMessageDialog(CluedoFrame.this, "Select cards for assumption", "Assumption",
						JOptionPane.PLAIN_MESSAGE);
				
				String[] rooms = {"kitchen","ballRoom","conservatory","diningRoom","billiardRoom","library","lounge","hall","study"};
				String[] weapons={"candleStick","knife", "leadPipe","rope","wrench", "revolver"};
				String[] characters={"missScarlett","professorPlum","mrsPeacock", "reverendGreen","colonelMustard","mrsWhite"};

				
				String weapon = (String) JOptionPane.showInputDialog(cluedoFrame, "Select weapon", "Weapon",
						JOptionPane.QUESTION_MESSAGE, null, weapons, weapons[0]);
				
				if(weapon.equals(weapons[0])){
					numWeapon = 1;
				}else if(weapon.equals(weapons[1])){
					numWeapon = 2;
				}else if(weapon.equals(weapons[2])){
					numWeapon = 3;
				}else if(weapon.equals(weapons[3])){
					numWeapon = 4;
				}else if(weapon.equals(weapons[4])){
					numWeapon = 5;
				}else if(weapon.equals(weapons[5])){
					numWeapon = 6;
				}
				
				String character = (String) JOptionPane.showInputDialog(cluedoFrame, "Select character", "charcter",
						JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
				
				if(character.equals(characters[0])){
					numCharacter = 1;
				}else if(character.equals(characters[1])){
					numCharacter = 2;
				}else if(character.equals(characters[2])){
					numCharacter = 3;
				}else if(character.equals(characters[3])){
					numCharacter = 4;
				}else if(character.equals(characters[4])){
					numCharacter = 5;
				}else if(character.equals(characters[5])){
					numCharacter = 6;
				}
				game.dir = "assumption";
				bottomPanel.requestFocus();
			} else if (e.getSource() == accusation) {
				JOptionPane.showMessageDialog(CluedoFrame.this, "Select cards for accusation", "Accusation",
						JOptionPane.PLAIN_MESSAGE);

			
				String[] rooms = {"kitchen","ballRoom","conservatory","diningRoom","billiardRoom","library","lounge","hall","study"};
				String[] weapons={"candleStick","knife", "leadPipe","rope","wrench", "revolver"};
				String[] characters={"missScarlett","professorPlum","mrsPeacock", "reverendGreen","colonelMustard","mrsWhite"};
				String room = (String) JOptionPane.showInputDialog(cluedoFrame, "Select room", "Room",
						JOptionPane.QUESTION_MESSAGE, null, rooms, rooms[0]);
					
				if(room.equals(rooms[0])){
					numRoom = 1;
				}else if(room.equals(rooms[1])){
					numRoom = 2;
				}else if(room.equals(rooms[2])){
					numRoom = 3;
				}else if(room.equals(rooms[3])){
					numRoom = 4;
				}else if(room.equals(rooms[4])){
					numRoom = 5;
				}else if(room.equals(rooms[5])){
					numRoom = 6;
				}else if(room.equals(rooms[6])){
					numRoom = 7;
				}else if(room.equals(rooms[7])){
					numRoom = 8;
				}else if(room.equals(rooms[8])){
					numRoom = 9;
				}
				
				String weapon = (String) JOptionPane.showInputDialog(cluedoFrame, "Select weapon", "Weapon",
						JOptionPane.QUESTION_MESSAGE, null, weapons, weapons[0]);
				
				if(weapon.equals(weapons[0])){
					numWeapon = 1;
				}else if(weapon.equals(weapons[1])){
					numWeapon = 2;
				}else if(weapon.equals(weapons[2])){
					numWeapon = 3;
				}else if(weapon.equals(weapons[3])){
					numWeapon = 4;
				}else if(weapon.equals(weapons[4])){
					numWeapon = 5;
				}else if(weapon.equals(weapons[5])){
					numWeapon = 6;
				}
				
				String character = (String) JOptionPane.showInputDialog(cluedoFrame, "Select character", "charcter",
						JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
				
				if(character.equals(characters[0])){
					numCharacter = 1;
				}else if(character.equals(characters[1])){
					numCharacter = 2;
				}else if(character.equals(characters[2])){
					numCharacter = 3;
				}else if(character.equals(characters[3])){
					numCharacter = 4;
				}else if(character.equals(characters[4])){
					numCharacter = 5;
				}else if(character.equals(characters[5])){
					numCharacter = 6;
				}
				game.dir = "accusation";
				
				

				bottomPanel.requestFocus();

			} else if (e.getSource() == leave) {
				if(game.currentPlayer.inRoom()==false){
					JOptionPane.showMessageDialog(CluedoFrame.this, "You're not in a room!", "Leave",
							JOptionPane.PLAIN_MESSAGE);
				}
				game.leave();
				bottomPanel.requestFocus();
			} else if (e.getSource() == cards) {
				JOptionPane.showMessageDialog(CluedoFrame.this, game.printHand(), "Card", JOptionPane.PLAIN_MESSAGE);
				bottomPanel.requestFocus();
			} else {
				JOptionPane.showMessageDialog(CluedoFrame.this, "not a button?", "hello?", JOptionPane.PLAIN_MESSAGE);
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