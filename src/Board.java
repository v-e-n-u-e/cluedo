import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Board {
	private Tile[][] tiles;
	private List<Player> players;

	/**
	 * upon Construction, loads the board with the players on it.
	 */
	public Board(List<Player> players) {
		tiles = new Tile[25][25];
		this.players = players;
		int row = 0;
		try {
			Scanner scan = new Scanner(new File("CluedoBoard"));
			while (scan.hasNext()) {
				String characters[] = scan.nextLine().split(" ");
				for (int col = 0; col <= 24; col++) {
					Point p;
					switch (characters[col].trim()) {

					case "W":
						p = new Point(row, col);
						tiles[row][col] = null;
						break;

					case "H":
						p = new Point(row, col);
						tiles[row][col] = new HallWay(p);
						break;

					case "E":
						p = new Point(row, col);
						tiles[row][col] = null;
						break;

					case "RK":
						p = new Point(row, col);
						tiles[row][col] = new Room("kitchen", p);
						break;

					case "DK":
						p = new Point(row, col);
						tiles[row][col] = new Door("kitchenDoor", p);
						break;

					case "RB":
						p = new Point(row, col);
						tiles[row][col] = new Room("ballRoom", p);
						break;

					case "DB":
						p = new Point(row, col);
						tiles[row][col] = new Door("ballRoomDoor", p);
						break;

					case "RC":
						p = new Point(row, col);
						tiles[row][col] = new Room("conservatory", p);
						break;

					case "DC":
						p = new Point(row, col);
						tiles[row][col] = new Door("conservatoryDoor", p);
						break;

					case "RD":
						p = new Point(row, col);
						tiles[row][col] = new Room("diningRoom", p);
						break;

					case "DD":
						p = new Point(row, col);
						tiles[row][col] = new Door("diningRoomDoor", p);
						break;

					case "Rb":
						p = new Point(row, col);
						tiles[row][col] = new Room("billiardRoom", p);
						break;

					case "Db":
						p = new Point(row, col);
						tiles[row][col] = new Door("billiardRoomDoor", p);
						break;

					case "RL":
						p = new Point(row, col);
						tiles[row][col] = new Room("library", p);
						break;

					case "DL":
						p = new Point(row, col);
						tiles[row][col] = new Door("libraryDoor", p);
						break;

					case "Rl":
						p = new Point(row, col);
						tiles[row][col] = new Room("lounge", p);
						break;

					case "Dl":
						p = new Point(row, col);
						tiles[row][col] = new Door("loungeDoor", p);
						break;

					case "RH":
						p = new Point(row, col);
						tiles[row][col] = new Room("hall", p);
						break;

					case "DH":
						p = new Point(row, col);
						tiles[row][col] = new Door("hallDoor", p);
						break;

					case "RS":
						p = new Point(row, col);
						tiles[row][col] = new Room("study", p);
						break;

					case "DS":
						p = new Point(row, col);
						tiles[row][col] = new Door("studyDoor", p);
						break;
					}

				}
				row++;

			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		printBoard();

	}

	/**
	 * prints the board to the console
	 */
	public void printBoard() {
		String boardMap = "";
		for (int row = 0; row <= 24; row++) {
			for (int col = 0; col <= 24; col++) {
				
				if(tiles[row][col] == null){
					boardMap += "+" + " ";
				}
				else{
					boardMap += tiles[row][col].print() + " ";
				}
			}
			boardMap += "\n";
		}
		System.out.println(boardMap);
	}

	/**
	 * Moves the player to the given point allows it
	 * 
	 * @param player
	 * @param point
	 * @return The amount of moves left
	 */
	public int move(Player player, Point point) {

		return 0;
	}

	/**
	 * Checks to see if the player can actually move to the given square eg(In
	 * bounds of map or appropriate dice roll
	 * 
	 * @param player
	 * @param point
	 * @param roll
	 * @return
	 */
	public Boolean canMove(Player player, Point point, int roll) {

		return false;
	}

}
