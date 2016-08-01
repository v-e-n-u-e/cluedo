import java.awt.Point;
import java.util.List;

public class Player {
	private List<Card> hand;
	private String name;
	private Boolean isOut;
	private Point location;
	
	public Player(){
		
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
		
		return "";
	}
	
	/**
	 * checks if the player is out or not
	 * @return
	 */
	public Boolean isOut(){
		return isOut;
	}
	
	public void lost(){
		
	}
	
}
