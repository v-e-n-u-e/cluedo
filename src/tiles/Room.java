package tiles;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Room implements Tile {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY
	ArrayList<Point> doors;
	Point pos;
	String name;
	public Room(String name,Point pos){//sets up an arraylist of doors that the room contains. used to let players leave onto them
		this.name=name;
		doors = new ArrayList<Point>();
		this.pos=pos;
	/*	if(this.print()=="K"){
			doors.add(new Point(4,6));
		}
		else if(this.print()=="d"){
			doors.add(new Point(7,12));
			doors.add(new Point(6,15));
		}
		else if(this.print()=="l"){
			doors.add(new Point(6,19));
		}
		else if(this.print()=="B"){
			doors.add(new Point(8,5));
			doors.add(new Point(9,7));
			doors.add(new Point(15,7));
			doors.add(new Point(16,5));
		}
		else if(this.print()=="H"){
			doors.add(new Point(11,18));
			doors.add(new Point(12,18));
			doors.add(new Point(13,18));
			doors.add(new Point(15,20));
		}
		else if(this.print()=="C"){
			doors.add(new Point(19,4));
		}
		else if(this.print()=="b"){
			doors.add(new Point(19,9));
			doors.add(new Point(23,12));
		}
		else if(this.print()=="L"){
			doors.add(new Point(21,14));
			doors.add(new Point(18,16));
		}
		else if(this.print()=="S"){
			doors.add(new Point(18,21));
		}
	}*/
	}
	
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public String print() {
		if(this.getName().equals("kitchen")){
			return "K";
		}else if(this.getName().equals("library")){
			return "L";
		}else if(this.getName().equals("ballRoom")){
			return "B";
		}else if(this.getName().equals("conservatory")){
			return "C";
		}else if(this.getName().equals("diningRoom")){
			return "d";
		}else if(this.getName().equals("billiardRoom")){
			return "b";
		}else if(this.getName().equals("lounge")){
			return "l";
		}else if(this.getName().equals("hall")){
			return "H";
		}else if(this.getName().equals("study")){
			return "S";
		}else if(this.getName().equals("spawn")){
			return "s";
		}
		return" ";
	}


	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return new ImageIcon("room.png");
	}
	
}
