import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
	
	private static int numPlayers;
	private static Scanner input;
	static Game game;

	public static void main(String args[]) {
		System.out.println("WELCOME TO CLUEDO!!!");
		makePlayers();
		game = new Game(numPlayers);
		game.loadAllCards();
		game.createPlayers();
		game.loadBoard();
		game.running();// rest of operations will take place inside the game class
		
	}
	/**
	 * asks the user for number of players and stores in a local field
	 * number must be between 3 and 6.
	 */
	private static void makePlayers() {
		numPlayers = 0;
		input = new Scanner(System.in);

		System.out.println("How many players(Please enter a number between 3 and 6):");
		try {
			numPlayers = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Value entered was not a number");
		}

	}

}
