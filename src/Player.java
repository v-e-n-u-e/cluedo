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

	public Player(String name, Point startingPos) {
		this.name = name;
		this.location = startingPos;
		this.startingPos =startingPos;
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
