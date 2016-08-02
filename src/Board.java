import java.awt.Point;
import java.util.List;

public class Board {
	private Tile[][] tiles;
	private List<Player> players;

	/**
	 * upon Construction, loads the board with the players 
	 * on it.
	 */
	public Board(List<Player> players) {
		this.players = players;
		
	}
	
	/**
	 * prints the board to the console
	 */
	public void printBoard(){
		
	}
	
	/**
	 * Moves the player to the given point
	 * allows it
	 * @param player
	 * @param point
	 * @return The amount of moves left
	 */
	public int move(Player player,Point point){
		
		return 0;
	}
	
	/**
	 * Checks to see if the player can actually move to the given square
	 * eg(In bounds of map or appropriate dice roll
	 * @param player
	 * @param point
	 * @param roll
	 * @return
	 */
	public Boolean canMove(Player player,Point point, int roll){
		
		return false;
	}
	
	
	
	
}
