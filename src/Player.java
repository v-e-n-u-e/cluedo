import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player implements Tile {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private List<Card> hand = new ArrayList<Card>();
	private String name;
	private boolean isOut = false;
	private Point location;
	public boolean inRoom = false;
	private Point startingPos;
	public String lastRoom;

	public Player(String name, Point startingPos) {
		this.name = name;
		this.location = startingPos;
		this.startingPos =startingPos;
		this.lastRoom="";
	}

	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point newLoc) {
		this.location = newLoc;
	}

	public boolean inRoom() {
		return this.inRoom;
	}

	public boolean setInRoom(boolean b) {
		return this.inRoom = b;
	}

	/**
	 * prints the hand to the console
	 * 
	 * @return hand to string
	 */
	public String handString() {
		String handString = "";
		for (Card c : hand) {
			handString += "|" + c.getName() + "|";
		}
		return handString;
	}

	/**
	 * checks if the player is out or not
	 * 
	 * @return
	 */
	public Boolean isOut() {
		return isOut;
	}

	/**
	 * sets isOut field to true
	 */
	public void lost() {
		isOut = true;
		this.setLocation(startingPos);
	}

	/**
	 * returns true if player has this car in their hand
	 * 
	 * @param card
	 * @return
	 */
	public Boolean holds(Card card) {
		for (Card c : hand) {
			if (c.getName().equals(card.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * adds given card to hand
	 * 
	 * @param card
	 */
	public void addCard(Card card) {
		hand.add(card);

	}
	
	/**
	 * this will return an arraylist containing positions this player goes into when entering rooms. 
	 * this goes from top to bottom, left to right. e.g. kitchen is first, study is last.
	 * @return
	 */
	public Point[] getRoomPos(){
		Point[] points = new Point[9];
		if(this.print().equals("1")){
			points[0]=new Point(2,2);//kitchen
			points[1]=new Point(3,11);//dining room
			points[2]=new Point(2,21);//lounge
			points[3]=new Point(12,3);//ballroom
			points[4]=new Point(11,20);//hall
			points[5]=new Point(21,1);//conservatory
			points[6]=new Point(21,9);//billiard room
			points[7]=new Point(20,16);//library
			points[8]=new Point(19,22);//study
		}
		else if(this.print().equals("2")){
			points[0]=new Point(3,2);
			points[1]=new Point(4,11);
			points[2]=new Point(3,21);
			points[3]=new Point(13,3);
			points[4]=new Point(12,20);
			points[5]=new Point(22,1);
			points[6]=new Point(22,9);
			points[7]=new Point(21,16);
			points[8]=new Point(20,22);
		}
		else if(this.print().equals("3")){
			points[0]=new Point(4,2);
			points[1]=new Point(5,11);
			points[2]=new Point(4,21);
			points[3]=new Point(14,3);
			points[4]=new Point(13,20);
			points[5]=new Point(23,1);
			points[6]=new Point(23,9);
			points[7]=new Point(22,16);
			points[8]=new Point(21,22);
		}
		else if(this.print().equals("4")){
			points[0]=new Point(2,4);
			points[1]=new Point(3,13);
			points[2]=new Point(2,23);
			points[3]=new Point(12,5);
			points[4]=new Point(11,22);
			points[5]=new Point(21,3);
			points[6]=new Point(21,11);
			points[7]=new Point(20,18);
			points[8]=new Point(19,24);
		}
		else if(this.print().equals("5")){
			points[0]=new Point(3,4);
			points[1]=new Point(4,13);
			points[2]=new Point(3,23);
			points[3]=new Point(13,5);
			points[4]=new Point(12,22);
			points[5]=new Point(22,3);
			points[6]=new Point(22,11);
			points[7]=new Point(21,18);
			points[8]=new Point(20,24);
		}
		else if(this.print().equals("6")){
			points[0]=new Point(4,4);
			points[1]=new Point(5,13);
			points[2]=new Point(4,23);
			points[3]=new Point(14,5);
			points[4]=new Point(13,22);
			points[5]=new Point(23,3);
			points[6]=new Point(23,11);
			points[7]=new Point(22,18);
			points[8]=new Point(21,24);
		}
		return points;
	}

	/**
	 * returns a number corrosponding to the name KEY: 1 = miss Scarlett 2 =
	 * Professor Plum 3 = Mrs Peacock 4 = Reverend Green 5 = Colonel Mustard 6 =
	 * Mrs White
	 */
	@Override
	public String print() {

		if (this.getName().equals("missScarlett")) {
			return "1";
		} else if (this.getName().equals("professorPlum")) {
			return "2";
		} else if (this.getName().equals("mrsPeacock")) {
			return "3";
		} else if (this.getName().equals("reverendGreen")) {
			return "4";
		} else if (this.getName().equals("colonelMustard")) {
			return "5";
		} else if (this.getName().equals("mrsWhite")) {
			return "6";
		}
		return null;
	}

}
