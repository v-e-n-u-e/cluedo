import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	Solution sol; // each game will have one solution
	Board board; // each game will have one board
	Player currentPlayer;
	List<Card> shuffledCards = new ArrayList<Card>();
	List<Card> weaponCards = new ArrayList<Card>();
	List<Card> roomCards = new ArrayList<Card>();
	List<Card> suspectCards = new ArrayList<Card>();
	List<Player> players = new ArrayList<Player>();
	int numPlayers;

	public Game(int players) {
		this.numPlayers = players;
	}

	/**
	 * creates and stores players in a list which is then going to be passed on
	 * to Board
	 * 
	 * @param players
	 */
	public void createPlayers() {

		/* Creates all characters */
		Player player1 = new Player("missScarlet");
		Player player2 = new Player("professorPlum");
		Player player3 = new Player("mrsPeacock");
		Player player4 = new Player("reverendGreen");
		Player player5 = new Player("colonelMustard");
		Player player6 = new Player("mrsWhite");
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		/*---------------------------*/

	}

	/**
	 * creates an instance of the board and populates it with the players
	 */
	public void loadBoard() {
		this.board = new Board(players);
	}

	/**
	 * loads all the cards in seperate arrays e.g(Weapons, suspects and rooms)
	 * 
	 */
	public void loadAllCards() {

		/* Load Weapon Cards into array */
		WeaponCard revolver = new WeaponCard("revolver");
		WeaponCard knife = new WeaponCard("knife");
		WeaponCard candleStick = new WeaponCard("candleStick");
		WeaponCard leadPipe = new WeaponCard("leadPipe");
		WeaponCard rope = new WeaponCard("rope");
		WeaponCard wrench = new WeaponCard("wrench");
		weaponCards.add(revolver);
		weaponCards.add(knife);
		weaponCards.add(candleStick);
		weaponCards.add(leadPipe);
		weaponCards.add(rope);
		weaponCards.add(wrench);
		/*---------------------------*/

		/* Load Room Cards into array */
		RoomCard kitchen = new RoomCard("kitchen");
		RoomCard ballRoom = new RoomCard("ballRoom");
		RoomCard conservatory = new RoomCard("conservatory");
		RoomCard diningRoom = new RoomCard("diningRoom");
		RoomCard billiardRoom = new RoomCard("billiardRoom");
		RoomCard library = new RoomCard("library");
		RoomCard lounge = new RoomCard("lounge");
		RoomCard hall = new RoomCard("hall");
		RoomCard study = new RoomCard("study");
		roomCards.add(kitchen);
		roomCards.add(ballRoom);
		roomCards.add(conservatory);
		roomCards.add(diningRoom);
		roomCards.add(billiardRoom);
		roomCards.add(library);
		roomCards.add(lounge);
		roomCards.add(hall);
		roomCards.add(study);
		/*--------------------------*/

		/* Loads Suspect Cards into array */
		SuspectCard missScarlet = new SuspectCard("missScarlet");
		SuspectCard professorPlum = new SuspectCard("professorPlum");
		SuspectCard mrsPeacock = new SuspectCard("mrsPeacock");
		SuspectCard reverendGreen = new SuspectCard("reverendGreen");
		SuspectCard colonelMustard = new SuspectCard("colonelMustard");
		SuspectCard mrsWhite = new SuspectCard("mrsWhite");
		suspectCards.add(missScarlet);
		suspectCards.add(professorPlum);
		suspectCards.add(mrsPeacock);
		suspectCards.add(reverendGreen);
		suspectCards.add(colonelMustard);
		suspectCards.add(mrsWhite);
		/*----------------------------*/

	}

	/**
	 * picks a random suspect,weapon and room from arrays above.
	 */
	public void generateSolution() {
		int suspectIndex = (int) Math.random() * 5;
		int roomIndex = (int) Math.random() * 8;
		int weaponIndex = (int) Math.random() * 5;
		sol = new Solution(roomCards.get(roomIndex), weaponCards.get(weaponIndex), suspectCards.get(suspectIndex));
		// Remove cards from deck
		roomCards.remove(roomIndex);
		weaponCards.remove(weaponIndex);
		suspectCards.remove(suspectIndex);
	}

	/**
	 * shuffles all three of the decks together into one array called cards
	 */
	public void shuffleDeck() {
		shuffledCards.addAll(weaponCards);
		shuffledCards.addAll(roomCards);
		shuffledCards.addAll(suspectCards);
		Collections.shuffle(shuffledCards);

	}

	/**
	 * Deals cards between players in the local list, but not to players who arnt playing
	 * e.g numPlayers = 3 then the cards will be delt to the first 3 players in the array.
	 */
	public void dealCards() {
		int count = 0;
		while (count != 18) {
			for (int i = 0; i < numPlayers; i++) {
				if (count != 18) {
					players.get(i).addCard(shuffledCards.get(count));
					count++;
				}

			}
		}
	}

	
	/**
	 * This runs until a player had won the game.
	 */
	public void running() {

	}

	
	/**
	 * returns the next player to have their turn
	 * @param current
	 * @return
	 */
	public Player nextPlayer(Player current) {

		return null;
	}

	/**
	 * make an Aqusation, player can win or lose off this move
	 * 
	 * @param player
	 */
	public void makeAqusation(Player player) {
		
	}

	/**
	 * make an Assumption with another player
	 * 
	 * @param player
	 */
	public void makeAssumption(Player player) {

	}
	
	
	/**
	 * reutrns the players hand
	 * @param player
	 */
	public void printCards(Player player){
		player.handString();
	}

}
