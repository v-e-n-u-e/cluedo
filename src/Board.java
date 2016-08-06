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

	
	public Tile[][] getTiles() {
		return tiles;
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
						getTiles()[row][col] = null;
						break;

					case "H":
						p = new Point(row, col);
						getTiles()[row][col] = new HallWay(p);
						break;

					case "E":
						p = new Point(row, col);
						getTiles()[row][col] = null;
						break;

					case "RK":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("kitchen", p);
						break;

					case "DK":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("kitchenDoor", p);
						break;

					case "RB":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("ballRoom", p);
						break;

					case "DB":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("ballRoomDoor", p);
						break;

					case "RC":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("conservatory", p);
						break;

					case "DC":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("conservatoryDoor", p);
						break;

					case "RD":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("diningRoom", p);
						break;

					case "DD":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("diningRoomDoor", p);
						break;

					case "Rb":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("billiardRoom", p);
						break;

					case "Db":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("billiardRoomDoor", p);
						break;

					case "RL":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("library", p);
						break;

					case "DL":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("libraryDoor", p);
						break;

					case "Rl":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("lounge", p);
						break;

					case "Dl":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("loungeDoor", p);
						break;

					case "RH":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("hall", p);
						break;

					case "DH":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("hallDoor", p);
						break;

					case "RS":
						p = new Point(row, col);
						getTiles()[row][col] = new Room("study", p);
						break;

					case "DS":
						p = new Point(row, col);
						getTiles()[row][col] = new Door("studyDoor", p);
						break;
						
					case "S":
						p = new Point(row,col);
						getTiles()[row][col] = new Room("spawn",p);
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
				
				if(getTiles()[row][col] == null){
					boardMap += "#" + " ";
				}
				else{
					boardMap += getTiles()[row][col].print() + " ";
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
			getTiles()[p.getLocation().y][p.getLocation().x] = p;
		}
	}
	/**
	 * once called the player can leave the room, if the room has multiple doors
	 * then the user is asked which door they would like to leave from. once left the player
	 * is placed in the door square
	 * @param player
	 */
	public void leaveRoom(Player player){
		Point pLoc = player.getLocation();
		ArrayList<Point> doors = new ArrayList<Point>();
		int px = pLoc.x;
		int py = pLoc.y;
		String command;
		input = new Scanner(System.in);
		if(this.getTiles()[py-1][px-1].print().equals("K")){//Leaving Kitchen
			doors.add(new Point(4,6));
			if((this.getTiles()[doors.get(0).y+1][doors.get(0).x] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{//good to go
			player.setLocation(doors.get(0));
			player.inRoom=false;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("d")){//Leaving dining room
			doors.add(new Point(7,12));//right
			doors.add(new Point(6,15));//bottom
			System.out.println("Which door would you like to exit from?\n right|bottom");			
			command=input.next();
			if((this.getTiles()[doors.get(0).y][doors.get(0).x+1] instanceof Player)
					&& (this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}
			else if(command.equals("right") && !(this.getTiles()[doors.get(0).y][doors.get(0).x+1] instanceof Player)){//These check whether or not someone is standing
				player.setLocation(doors.get(0));	
				player.inRoom=false;//in front of the door, and wont let you out
			} 
			else if(command.equals("bottom")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
				player.inRoom=false;
			}
			else{
				System.out.println("Invalid door to leave from!");
				Game.roll++;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("l")){//Leaving lounge
			//System.out.println("we know it's lounge");
			doors.add(new Point(6,19));
			if((this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(0).y][doors.get(0).x+1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{
			player.setLocation(new Point(6,19));
			player.inRoom=false;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("B")){//Leaving ballroom
			doors.add(new Point(8,5));//eft
			doors.add(new Point(9,7));//bottom left
			doors.add(new Point(15,7));//bottom right
			doors.add(new Point(16,5));//right
			System.out.println("Which door would you like to exit from?\n left|bottomleft|bottomright|right");			
			command=input.next();
			if((this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)
					&& (this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)
					&& (this.getTiles()[doors.get(2).y+1][doors.get(2).x] instanceof Player)
					&& (this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}
			else if(command.equals("left")  && !(this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){
				player.setLocation(doors.get(0));
				player.inRoom=false;
			}
			else if(command.equals("bottomleft")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
				player.inRoom=false;
			}
			else if(command.equals("bottomright")  && !(this.getTiles()[doors.get(2).y+1][doors.get(2).x] instanceof Player)){
				player.setLocation(doors.get(2));
				player.inRoom=false;
			}
			else if(command.equals("right")  && !(this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){
				player.setLocation(doors.get(3));
				player.inRoom=false;
			}
			else{
				System.out.println("Invalid door to leave from!");
				Game.roll++;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("H")){//Leaving hall
			doors.add(new Point(11,18));//left
			doors.add(new Point(12,18));//middle
			doors.add(new Point(13,18));//right
			doors.add(new Point(15,20));//bottom right
			System.out.println("Which door would you like to exit from?\n left|middle|right|bottomright");			
			command=input.next();
			if((this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(1).y-1][doors.get(1).x] instanceof Player)
					&& (this.getTiles()[doors.get(2).y-1][doors.get(2).x] instanceof Player)
					&& (this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}
			else if(command.equals("left")  && !(this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)){
				player.setLocation(doors.get(0) );
				player.inRoom=false;
			}
			else if(command.equals("middle")  && !(this.getTiles()[doors.get(1).y-1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
				player.inRoom=false;
			}
			else if(command.equals("right")  && !(this.getTiles()[doors.get(2).y-1][doors.get(2).x] instanceof Player)){
				player.setLocation(doors.get(2));
				player.inRoom=false;
			}
			else if(command.equals("bottomright")  && !(this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){
				player.setLocation(doors.get(3));
				player.inRoom=false;
			}
			else{
				System.out.println("Invalid door to leave from!");
				Game.roll++;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("C")){//Leaving conservatory
			doors.add(new Point(19,4));
			if((this.getTiles()[doors.get(0).y+1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{
			player.setLocation(doors.get(0));
			player.inRoom=false;
			}
		}
		else if(this.getTiles()[py-1][px-1].print()=="b"){//Leaving billiard room
			doors.add(new Point(19,9));//left
			doors.add(new Point(23,12));//bottom
			System.out.println("Which door would you like to exit from?\n left|bottom");			
			command=input.next();
			if((this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)
					&& (this.getTiles()[doors.get(0).y+1][doors.get(0).x] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}
			else if(command.equals("left")  && !(this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){
				player.setLocation(doors.get(0));
				player.inRoom=false;
			}
			else if(command.equals("bottom")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
				player.inRoom=false;
			}
			else{
				System.out.println("Invalid door to leave from!");
				Game.roll++;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("L")){//Leaving library
			doors.add(new Point(21,14));//top
			doors.add(new Point(18,16));//left
			System.out.println("Which door would you like to exit from?\n top|left");			
			command=input.next();
			if((this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}
			else if(command.equals("top")  && !(this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)){
				player.setLocation(doors.get(0));
				player.inRoom=false;
			}
			else if(command.equals("left")  && !(this.getTiles()[doors.get(1).y][doors.get(1).x-1] instanceof Player)){
				player.setLocation(doors.get(1));
				player.inRoom=false;
			}
			else{
				System.out.println("Invalid door to leave from!");
				Game.roll++;
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("S")){//Leaving study
			doors.add(new Point(18,21));
			if((this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{
			player.setLocation(doors.get(0));
			player.inRoom=false;}
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
			
			if(this.getTiles()[destination.y][destination.x].print()=="D"){//THIS IS IF THEY MOVE INDOORS
				if(this.getTiles()[lookahead.y][lookahead.x].print()=="K"){ //if they enter the kitchen
					if(player.lastRoom.equals("K")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
						player.inRoom=true;
						Game.roll=1;
						player.setLocation(player.getRoomPos()[0]);
						player.lastRoom="K";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="d"){//enter dining room
					if(player.lastRoom.equals("d")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					player.inRoom=true;
					Game.roll=1;
					player.setLocation(player.getRoomPos()[1]);
					player.lastRoom="d";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="l"){//if they enter the lounge
					if(player.lastRoom.equals("l")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[2]);
					player.lastRoom="l";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="B"){//enter ballroom
					if(player.lastRoom.equals("B")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[3]);
					player.lastRoom="B";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="H"){//enter hall
					if(player.lastRoom.equals("H")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[4]);
					player.lastRoom="H";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="C"){//enter conservatory
					if(player.lastRoom.equals("C")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[5]);
					player.lastRoom="C";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="b"){//billiard room
					if(player.lastRoom.equals("b")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[6]);
					player.lastRoom="b";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="L"){//library
					if(player.lastRoom.equals("L")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[7]);
					player.lastRoom="L";
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="S"){//study
					if(player.lastRoom.equals("S")){
						Game.roll++;
						System.out.println("You can't enter the last room you were in!");
						return;
					}
					Game.roll=1;
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[8]);
					player.lastRoom="S";
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
		else if((playLoc!=destination)){//still has some room to move, not moving on same space somehow
			if((this.getTiles()[destination.y][destination.x].print()=="+" || //OK if it's a hallway
					this.getTiles()[destination.y][destination.x].print()=="D")){ //OK if it's a door
				return true;
			}
		}
		
		return false;//no movement left
	}

}
