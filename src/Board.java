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
	public void move(Player player, Point destination, Point lookahead) {
		//if(canMove(player,destination)==true){
			
			if(this.tiles[destination.y][destination.x].print()=="D"){//THIS IS IF THEY MOVE INDOORS
				if(this.tiles[lookahead.y][lookahead.x].print()=="K"){ //if they enter the kitchen
					Point roomPos=new Point(3,3);
					
						if(player.print()=="2"){
							 roomPos=new Point(4,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="3"){
							 roomPos=new Point(5,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="4"){
							 roomPos=new Point(3,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="5"){
							 roomPos=new Point(4,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="6"){
							 roomPos=new Point(5,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="d"){//enter dining room
					Point roomPos=new Point(3,11);
					if(player.print()=="2"){
						 roomPos=new Point(4,11);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(5,11);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(3,12);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(4,12);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(5,12);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="l"){//if they enter the lounge
					Point roomPos=new Point(2,21);
					if(player.print()=="2"){
						 roomPos=new Point(3,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(4,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(2,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(3,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(4,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="B"){//enter ballroom
					Point roomPos=new Point(12,4);
					if(player.print()=="2"){
						 roomPos=new Point(13,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(14,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(12,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(13,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(14,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="H"){//enter hall
					Point roomPos=new Point(12,20);
					if(player.print()=="2"){
						 roomPos=new Point(13,20);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(14,20);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(12,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(13,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(14,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="C"){//enter conservatory
					Point roomPos=new Point(23,3);
					if(player.print()=="2"){
						 roomPos=new Point(24,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(25,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(23,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(24,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(25,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="b"){//billiard room
					Point roomPos=new Point(23,9);
					if(player.print()=="2"){
						 roomPos=new Point(24,9);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(25,9);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(23,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(24,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(25,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="L"){//library
					Point roomPos=new Point(23,15);
					if(player.print()=="2"){
						 roomPos=new Point(24,15);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(25,15);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(23,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(24,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(25,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos); 
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="S"){//study
					Point roomPos=new Point(3,22);
					if(player.print()=="2"){
						 roomPos=new Point(4,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(5,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(3,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(4,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(5,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.setLocation(roomPos);
				}
				else{
					System.out.println("not a room? hello?");//hello?
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
