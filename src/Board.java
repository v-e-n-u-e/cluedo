import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Board {
	private Tile[][] tiles;

	/**
	 * upon Construction, loads the board. KEY:
	 * # = wall
	 * + = hallway
	 * 0 = spawn
	 * D = door
	 * K = kitchen
	 * B = ballRoom
	 * C = conservatory
	 * b = billiardRoom
	 * d = DiningRoom
	 * L = Library
	 * l = lounge
	 * H = hall
	 * S = study
	 * 
	 */
	public Board() {
		tiles = new Tile[25][25];
	}
	
	/**
	 * THIS CREATES A NEW BOARD AS DEFAULT VALUES
	 * THIS IS USED ON STARTING THE GAME AND ALSO EVERY TIME A PLAYER MOVES
	 * 
	 */
	public void createBoard(){
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
						
					case "S":
						p = new Point(row,col);
						tiles[row][col] = new Room("spawn",p);
					}
				}
				row++;

			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	/**
	 * prints the board to the console
	 */
	public void printBoard() {
		String boardMap = "";
		for (int row = 0; row <= 24; row++) {
			for (int col = 0; col <= 24; col++) {
				
				if(tiles[row][col] == null){
					boardMap += "#" + " ";
				}
				else{
					boardMap += tiles[row][col].print() + " ";
				}
			}
			boardMap += "\n";
		}
		System.out.println(boardMap);
	}
	
	public void printKey(){
		
		
	}
	
	
	/**
	 * places characters onto the spawn squares
	 * 
	 */
	public void setCharacters(List<Player> players){
		for(Player p:players){
			tiles[p.getLocation().y][p.getLocation().x] = p;
		}
	}

	/**
	 * Moves the player to the given point allows it
	 * 
	 * @param player
	 * @param point
	 * @return The amount of moves left
	 */
	public void move(Player player, Point destination) {
		//if(canMove(player,destination)==true){
			
			if(this.tiles[destination.y][destination.x].print()=="D"){//THIS IS IF THEY MOVE INDOORS
				Door door = (Door) this.tiles[destination.y][destination.x];//get the door they're entering
				String room = door.connectedRoom;//get name of room they're entering
				if(room=="kitchen"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="dining room"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="lounge"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="ballroom"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="hall"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="conservatory"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="billiard room"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="library"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
				if(room=="study"){
					Point roomPos=new Point(3,23);
					player.setLocation(roomPos);
				}
			}
			else{//not going in a room
			player.setLocation(destination);
			}
		//}
		//else{
			//System.out.println("Invalid move!");
		//}
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
	public Boolean canMove(Player player, Point destination) {
		Point playLoc=player.getLocation();
		if((playLoc!=destination)){//still has some room to move, not moving on same space somehow
			if(this.tiles[destination.y][destination.x].print()=="+" || //OK if it's a hallway
					this.tiles[destination.y][destination.x].print()=="D"){ //OK if it's a door
				return true;
			}
		}
		
		return false;//no movement left
	}
	

}
