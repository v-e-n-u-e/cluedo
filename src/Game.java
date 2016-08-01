import java.util.List;

public class Game {
	Solution sol; //each game will have one solution
	Board board; //each game will have one board
	Player currentPlayer;
	List<Card> cards;
	List<Player> players;
	
	public Game(int players){
		
	}
	
	/**
	 * creates and stores players in a list which is then going 
	 * to be passed on to Board
	 * @param players
	 */
	public void createPlayers(int players){
		
	}
	
	/**
	 * creates an instance of the board
	 */
	public void loadBoard(){
		
	}
	
	/**
	 * runs the game
	 */
	public void running(){
		
	}
	
	public Player nextPlayer(Player current){
		
		return null;
	}
	
	/**
	 * make an Aqusation, player can win or lose off this move
	 * @param player
	 */
	public void makeAqusation(Player player){
		
	}
	/**
	 * make an Assumption with another player
	 * @param player
	 */
	public void makeAssumption(Player player){
		
	}
	
}
