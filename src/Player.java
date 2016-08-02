import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	private String name;
	private Boolean isOut;
	private Point location;
	
	public Player(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public Point getLocation(){
		return location;
	}
	
	/**
	 * prints the hand to the console
	 * @return hand to string
	 */
	public String handString(){
		String handString ="";
		for(Card c: hand){
			handString += "|"+c.getName()+"|";
		}
		return handString;
	}
	
	/**
	 * checks if the player is out or not
	 * @return
	 */
	public Boolean isOut(){
		return isOut;
	}
	
	
	/**
	 * sets isOut field to true
	 */
	public void lost(){
		isOut = true;
	}
	
	/**
	 * returns true if player has this car in their hand
	 * @param card
	 * @return
	 */
	public Boolean holds(Card card){
		if(hand.contains(card)){
			return true;
		}
		return false;
	}
	
	/**
	 * adds given card to hand
	 * @param card
	 */
	public void addCard(Card card){
		hand.add(card);
		
	}
	
}
