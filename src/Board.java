import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
	private Tile[][] tiles;
	private Scanner input;
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
	
	public void leaveRoom(Player player){
		Point pLoc = player.getLocation();
		ArrayList<Point> doors = new ArrayList<Point>();
		int px = pLoc.x;
		int py = pLoc.y;
		String command;
		input = new Scanner(System.in);
		player.inRoom=false;
		if(this.tiles[py-1][px].print().equals("K")){//Leaving Kitchen
			doors.add(new Point(4,6));
			player.setLocation(doors.get(0));
		}
		else if(this.tiles[py-1][px].print().equals("d")){//Leaving dining room
			doors.add(new Point(7,12));//right
			doors.add(new Point(6,15));//bottom
			System.out.println("Which door would you like to exit from?\n right|bottom");			
			command=input.next();
			if(command.equals("right")){
				player.setLocation(doors.get(0));
			}
			else if(command.equals("bottom")){
				player.setLocation(doors.get(1));
			}
		}
		else if(this.tiles[py-1][px].print().equals("l")){//Leaving lounge
			//System.out.println("we know it's lounge");
			//doors.add(new Point(7,19));
			player.setLocation(new Point(6,19));
		}
		else if(this.tiles[py-1][px].print().equals("B")){//Leaving ballroom
			doors.add(new Point(8,5));//eft
			doors.add(new Point(9,7));//bottom left
			doors.add(new Point(15,7));//bottom right
			doors.add(new Point(16,5));//right
			System.out.println("Which door would you like to exit from?\n left|bottom left|bottom right|right");			
			command=input.next();
			if(command.equals("left")){
				player.setLocation(doors.get(0));
			}
			else if(command.equals("bottom left")){
				player.setLocation(doors.get(1));
			}
			else if(command.equals("bottom right")){
				player.setLocation(doors.get(2));
			}
			else if(command.equals("right")){
				player.setLocation(doors.get(3));
			}
		}
		else if(this.tiles[py-1][px].print().equals("H")){//Leaving hall
			doors.add(new Point(11,18));//left
			doors.add(new Point(12,18));//middle
			doors.add(new Point(13,18));//right
			doors.add(new Point(15,20));//bottom right
			System.out.println("Which door would you like to exit from?\n left|middle|right|bottom right");			
			command=input.next();
			if(command.equals("left")){
				player.setLocation(doors.get(0));
			}
			else if(command.equals("middle")){
				player.setLocation(doors.get(1));
			}
			else if(command.equals("right")){
				player.setLocation(doors.get(2));
			}
			else if(command.equals("bottom right")){
				player.setLocation(doors.get(3));
			}
		}
		else if(this.tiles[py-1][px].print().equals("C")){//Leaving conservatory
			doors.add(new Point(19,4));
			player.setLocation(doors.get(0));
		}
		else if(this.tiles[py-1][px].print()=="b"){//Leaving billiard room
			doors.add(new Point(19,9));//left
			doors.add(new Point(23,12));//bottom
			System.out.println("Which door would you like to exit from?\n left|bottom");			
			command=input.next();
			if(command.equals("left")){
				player.setLocation(doors.get(0));
			}
			else if(command.equals("bottom")){
				player.setLocation(doors.get(1));
			}
		}
		else if(this.tiles[py-1][px].print().equals("L")){//Leaving library
			doors.add(new Point(21,14));//top
			doors.add(new Point(18,16));//left
			System.out.println("Which door would you like to exit from?\n top|left");			
			command=input.next();
			if(command.equals("top")){
				player.setLocation(doors.get(0));
			}
			else if(command.equals("left")){
				player.setLocation(doors.get(1));
			}
		}
		else if(this.tiles[py-1][px].print().equals("S")){//Leaving study
			doors.add(new Point(18,21));
			player.setLocation(doors.get(0));
		}
		else{
			System.out.println("Not in a room? hello?");
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
					Game.roll=1;
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
						player.inRoom=true;
						player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="d"){//enter dining room
					Point roomPos=new Point(3,11);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="l"){//if they enter the lounge
					Point roomPos=new Point(2,21);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="B"){//enter ballroom
					Point roomPos=new Point(12,4);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="H"){//enter hall
					Point roomPos=new Point(12,20);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="C"){//enter conservatory
					Point roomPos=new Point(21,3);
					Game.roll=1;
					if(player.print()=="2"){
						 roomPos=new Point(22,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(23,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(21,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(22,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(23,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="b"){//billiard room
					Point roomPos=new Point(23,9);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="L"){//library
					Point roomPos=new Point(23,15);
					Game.roll=1;
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
					player.inRoom=true;
					player.setLocation(roomPos);  
				}
				else if(this.tiles[lookahead.y][lookahead.x].print()=="S"){//study
					Point roomPos=new Point(3,22);
					Game.roll=1;
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
					player.inRoom=true;
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
		if(destination.y>24 || destination.y<0){
			return false;//don't go there
		}
		if(destination.x>24 || destination.x<0){
			return false;//don't go there
		}
		if((playLoc!=destination)){//still has some room to move, not moving on same space somehow
			if((this.tiles[destination.y][destination.x].print()=="+" || //OK if it's a hallway
					this.tiles[destination.y][destination.x].print()=="D")){ //OK if it's a door
				return true;
			}
		}
		
		return false;//no movement left
	}
	

}
