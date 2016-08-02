import java.util.List;

public class Game {
	Solution sol; //each game will have one solution
	Board board; //each game will have one board
	Player currentPlayer;
	List<Card> cards;
	List<Player> players;
	int numPlayers;
	
	public Game(int players){
		this.numPlayers = players;
	}
	
	/**
	 * creates and stores players in a list which is then going 
	 * to be passed on to Board
	 * @param players
	 */
	public void createPlayers(){
		
	}
	
	/**
	 * creates an instance of the board and populates it with the players
	 */
	public void loadBoard(){
		
	}
	
	/**
	 * loads all the cards in seperate arrays e.g(Weapons, suspects and rooms)
	 */
	public void loadAllCards(){
		
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
	
	/**
	 * Deals cards between players in the local list
	 */
	public void dealCards(){
		
	}
	
}
