package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class CluedoFrame extends JFrame implements ActionListener{
	
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
		JLabel labelOne = new JLabel("Hello");
		labelOne.setText("New text");
		labelOne.setToolTipText("This shows when hovering");
		
		//Buttons.
		JButton button1 = new JButton("button");
		button1.setContentAreaFilled(false);
		button1.setText("new button");

		
		//Text Field.
		JTextField textField1 = new JTextField("Type here",15);
		textField1.setColumns(10);
		textField1.setText("Type again");
		
		//Text Area.
		JTextArea textArea1 = new JTextArea(15,20);
		textArea1.setText("hello this stuff is important");
		textArea1.setLineWrap(true);
		JScrollPane scrollBar1 = new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea1.setWrapStyleWord(true);// Words dont span on other lines
		textArea1.requestFocus();//Highlights on open
		
		//Connecting Components.panelOne
		panelOne.add(button1);
		panelOne.add(textField1);
		panelOne.add(labelOne); // adds label to "Section" one
		panelOne.add(scrollBar1);
		this.add(panelOne);
		this.setVisible(true); // makes current window visable
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
