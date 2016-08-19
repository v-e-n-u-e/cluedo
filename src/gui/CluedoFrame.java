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

	public JButton button1;
	public JTextField textField1;
	public JTextArea textArea1;
	public JPanel selectionPanel;
	public JRadioButton missScarlett, colonelMustard, professorPlum, reverendGreen, mrsWhite, mrsPeacock;

	public static void main(String[] args) {
		new CluedoFrame();

	}

	public CluedoFrame() {
		// Creates Frame.
		super("CLUEDO");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		// tell frame to fire a WindowsListener event
		// but not to close when "x" button clicked.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// Panel stuff.
		JPanel panelOne = new JPanel(); // is a "Section" on the frame
		JLabel labelOne = new JLabel("Label");
		labelOne.setText("Label");
		labelOne.setToolTipText("This shows when hovering");

		// Buttons.
		button1 = new JButton("button");
		button1.setContentAreaFilled(false);
		button1.setText("new button");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);

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

		// Connecting Components.panelOne
		panelOne.add(button1);
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

	public void selectCharacters(List<Player> players) {
		for (Player p : players) {
			//JOptionPane.showOptionDialog(null, selectionPanel);
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
			if (e.getSource() == button1) {
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
