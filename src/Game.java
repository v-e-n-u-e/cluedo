import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
	Solution solution; // each game will have one solution
	Board board; // each game will have one board
	Player currentPlayer;
	List<Card> shuffledCards = new ArrayList<Card>();
	List<Card> weaponCards = new ArrayList<Card>();
	List<Card> roomCards = new ArrayList<Card>();
	List<Card> suspectCards = new ArrayList<Card>();
	List<Player> players = new ArrayList<Player>();
	int numPlayers;
	private Scanner input;

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
		Player player1 = new Player("missScarlett", new Point(7, 24));
		Player player2 = new Player("professorPlum", new Point(24, 19));
		Player player3 = new Player("mrsPeacock", new Point(24, 6));
		Player player4 = new Player("reverendGreen", new Point(15, 0));
		Player player5 = new Player("colonelMustard", new Point(0, 17));
		Player player6 = new Player("mrsWhite", new Point(9, 0));
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
		this.board = new Board();
		this.board.createBoard();
		board.setCharacters(players);
		//board.printBoard();
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
		solution = new Solution(roomCards.get(roomIndex), weaponCards.get(weaponIndex), suspectCards.get(suspectIndex));
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
	 * Deals cards between players in the local list, but not to players who
	 * arnt playing e.g numPlayers = 3 then the cards will be delt to the first
	 * 3 players in the array.
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
	 * returns the next player to have their turn
	 * 
	 * @param current
	 * @return
	 */
	public Player nextPlayer(Player current) {

		return null;
	}

	/**
	 * make an Accusation, player can win or lose off this move The player picks
	 * a room,a weapon and a suspect from the list printed.
	 * 
	 * @param player
	 */
	public void makeAccusation(Player player) {
		input = new Scanner(System.in);
		int room;
		int suspect;
		int weapon;

		System.out.println("Please Pick a room: \n" + "1 = kitchen 2 = ballRoom 3 = conservatory \n"
				+ "4 = billiardRoom 5 = library 6= study \n" + "7= hall 8 = lounge 9 = diningRoom");
		room = input.nextInt();

		System.out.println("Please pick a weapon: \n" + "1 = 3candleStick 2 = knife 3 =leadPipe \n"
				+ "4 = rope 5 = wrench 6 =revolver");
		weapon = input.nextInt();

		System.out.println("Please pick a suspect: \n" + "1 = missScarlett 2 = professorPlum 3 = mrsPeacock \n"
				+ "4 = reverendGreen 5 = colonelMustard 6 = mrsWhite");
		suspect = input.nextInt();

		Guess guess = new Guess(room, weapon, suspect);

		// Player has won the game
		if (solution.checkSolution(guess.getRoom(), guess.getWeapon(), guess.getMurderer()) == true) {

			// stop game method here
		} else {
			System.out.println("Incorrect guess, You are out of the game");
			player.lost();
		}
	}

	/**
	 * make an Assumption with another player, player guesses a weapon, room and
	 * suspect and if any player has one of those cards they must show it. also
	 * the suspect is transported to the room.
	 * 
	 * @param player
	 */
	public void makeAssumption(Player player) {
		input = new Scanner(System.in);
		int room;
		int suspect;
		int weapon;

		System.out.println("Please Pick a room: \n" + "1 = kitchen 2 = ballRoom 3 = conservatory \n"
				+ "4 = billiardRoom 5 = library 6= study \n" + "7= hall 8 = lounge 9 = diningRoom");
		room = input.nextInt();

		System.out.println("Please pick a weapon: \n" + "1 = candleStick 2 = knife 3 =leadPipe \n"
				+ "4 = rope 5 = wrench 6 =revolver");
		weapon = input.nextInt();

		System.out.println("Please pick a suspect: \n" + "1 = missScarlett 2 = professorPlum 3 = mrsPeacock \n"
				+ "4 = reverendGreen 5 = colonelMustard 6 = mrsWhite");
		suspect = input.nextInt();

		Guess guess = new Guess(room, weapon, suspect);
		
		for(Player p: players){
	
			if(p.holds(guess.getRoom())){//If the player holds the room card
				System.out.println(p.getName() + "holds:"+guess.getRoom().getName());
			}if(p.holds(guess.getMurderer())){//If the player holds the suspect card
				System.out.println(p.getName() + "holds:"+guess.getMurderer().getName());
			}if(p.holds(guess.getWeapon())){//If the player holds the weapon card
				System.out.println(p.getName() + "holds:"+guess.getWeapon().getName());
			}
			
			if(p.getName().equals(guess.getMurderer())){
				p.setLocation(currentPlayer.getLocation());
				p.setInRoom(true);
			}
		}

	}

	/**
	 * returns the players hand For Testing only
	 * 
	 * @param player
	 */
	public void printCards(Player player) {
		System.out.println(player.handString());
	}

	/**
	 * roll two dice (numbers between 2-12)
	 * 
	 * @return
	 */
	public int rollDice() {

		int roll = (int) (Math.random() * 6) + 1;
		int roll2 = (int) (Math.random() * 6) + 1;
		return roll + roll2;
	}

	/**
	 * This runs until a player has won the game.
	 */
	public void running() {
		input = new Scanner(System.in);
		while (true) {
			for (int i = 0; i < numPlayers; i++) {
					String command;
					currentPlayer = players.get(i);
					if (currentPlayer.isOut() != true) {
					int roll = this.rollDice();
					//board.printBoard();
					System.out.println("KEY:    D=Door             +=Hallway       #=Wall      K=Kitchen \n");
					System.out.println("        C=Conservatory     S=Study         L=Library   b=Billiard Room \n");
					System.out.println("        B=Ballroom         d=Dining Room   H=Hall      l=Lounge \n");
					System.out.println(currentPlayer.getName()+"("+currentPlayer.print()+")" + ": You Rolled a " + roll + " \n");
					System.out.println("your hand is :" + currentPlayer.handString() + "\n");
					System.out.println("Commands:|up|down|left|right|");
					System.out.println("         assumption (Must be in a room)");
					System.out.println("         accusation (Must be in a room)");

					while (roll != 0 && currentPlayer.inRoom() == false) {
						board.createBoard();
						board.setCharacters(players);
						board.printBoard();
						System.out.println("You have " + roll + " squares left to move.");
						System.out.println("enter your command:");
						System.out.println(currentPlayer.getLocation());
						command = input.next();
						
						if(command.equals("assumption")){
							roll =0;
							makeAssumption(currentPlayer);
						}else if(command.equals("accusation")){
							roll =0;
							makeAccusation(currentPlayer);
						}else if(command.equals("up")){
							System.out.println();
							Point destination = new Point(currentPlayer.getLocation().x,currentPlayer.getLocation().y-1);
							if(board.canMove(currentPlayer, destination)==true){
								board.move(currentPlayer, destination);
								roll--;
							}
							else{
								System.out.println("Invalid move!");
							}
						}else if(command.equals("down")){
							Point destination = new Point(currentPlayer.getLocation().x,currentPlayer.getLocation().y+1);
							if(board.canMove(currentPlayer, destination)==true){
								board.move(currentPlayer, destination);
								roll--;
							}
							else{
								System.out.println("Invalid move!");
							}
						}else if(command.equals("left")){
							Point destination = new Point(currentPlayer.getLocation().x-1,currentPlayer.getLocation().y);
							if(board.canMove(currentPlayer, destination)==true){
								board.move(currentPlayer, destination);
								roll--;
							}
							else{
								System.out.println("Invalid move!");
							}
						}else if(command.equals("right")){
							Point destination = new Point(currentPlayer.getLocation().x+1,currentPlayer.getLocation().y);
							if(board.canMove(currentPlayer, destination)==true){
								board.move(currentPlayer, destination);
								roll--;
							}
							else{
								System.out.println("Invalid move!");
							}
						}
					}
				}
			}
		}
	}
}