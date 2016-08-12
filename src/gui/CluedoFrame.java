package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.*;

public class CluedoFrame extends JFrame{
	
	public JButton button1;
	public JTextField textField1;
	public JTextArea textArea1;
	public JRadioButton missScarlett,colonelMustard,professorPlum;
	
	
	
	public static void main(String[] args){
		new CluedoFrame();
		
	}
	
	public CluedoFrame(){
		//Creates Frame.
		super("CLUEDO");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panel stuff.
		JPanel panelOne = new JPanel(); // is a "Section" on the frame
		JLabel labelOne = new JLabel("Label");
		labelOne.setText("Label");
		labelOne.setToolTipText("This shows when hovering");
		
		//Buttons.
		button1 = new JButton("button");
		button1.setContentAreaFilled(false);
		button1.setText("new button");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);

		
		//Text Field.
		textField1 = new JTextField("TextField",15);
		textField1.setColumns(10);
		textField1.setText("Type again");
		
		//Text Area.
		textArea1 = new JTextArea(15,20);
		textArea1.setText("TextArea");
		textArea1.setLineWrap(true);
		JScrollPane scrollBar1 = new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea1.setWrapStyleWord(true);// Words dont span on other lines
		textArea1.requestFocus();//Highlights on open
		
		//RadioButtons.
		missScarlett = new JRadioButton("missScarlett");
		professorPlum = new JRadioButton("professorPlum");
		colonelMustard = new JRadioButton("colonelMustard");
		ButtonGroup selection = new ButtonGroup();
		selection.add(missScarlett);
		selection.add(professorPlum);
		selection.add(colonelMustard);
		JPanel selectionPanel = new JPanel();
		Border selectionBorder = BorderFactory.createTitledBorder("Characters");
		selectionPanel.setBorder(selectionBorder);
		selectionPanel.add(missScarlett);
		selectionPanel.add(professorPlum);
		selectionPanel.add(colonelMustard);
		
		
		//Connecting Components.panelOne
		panelOne.add(button1);
		panelOne.add(textField1);
		panelOne.add(labelOne); // adds label to "Section" one
		panelOne.add(scrollBar1);
		panelOne.add(selectionPanel);//adds radio buttons
		this.add(panelOne);
		ListenForWindow lforWIndow = new ListenForWindow();
		this.setVisible(true); // makes current window visable
		ListenForMouse lForMouse = new ListenForMouse();
		panelOne.addMouseListener(lForMouse);
		
	}
	


//*------------------LISTENERS----------------------*//

//MOUSE
class ListenForMouse implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		textArea1.append("Xpos: "+x+" Ypos:"+y+" ");
		
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



//BUTTON
class ListenForButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1){
				textArea1.append("button1 pressed");
				JOptionPane.showMessageDialog(CluedoFrame.this, "you have pressed the button","title of message", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}


//WINDOW
class ListenForWindow implements WindowListener{

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
}


