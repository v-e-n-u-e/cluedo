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
	
	public void leaveRoom(Player player){
		Point pLoc = player.getLocation();
		ArrayList<Point> doors = new ArrayList<Point>();
		int px = pLoc.x;
		int py = pLoc.y;
		String command;
		input = new Scanner(System.in);
		player.inRoom=false;
		if(this.getTiles()[py-1][px-1].print().equals("K")){//Leaving Kitchen
			doors.add(new Point(4,6));
			if((this.getTiles()[doors.get(0).y+1][doors.get(0).x] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{//good to go
			player.setLocation(doors.get(0));
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
				player.setLocation(doors.get(0));															//in front of the door, and wont let you out
			} 
			else if(command.equals("bottom")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
			}
			else{
				System.out.println("Someone's blocking that door!");
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
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("B")){//Leaving ballroom
			doors.add(new Point(8,5));//eft
			doors.add(new Point(9,7));//bottom left
			doors.add(new Point(15,7));//bottom right
			doors.add(new Point(16,5));//right
			System.out.println("Which door would you like to exit from?\n left|bottom left|bottom right|right");			
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
			}
			else if(command.equals("bottom left")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
			}
			else if(command.equals("bottom right")  && !(this.getTiles()[doors.get(2).y+1][doors.get(2).x] instanceof Player)){
				player.setLocation(doors.get(2));
			}
			else if(command.equals("right")  && !(this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){
				player.setLocation(doors.get(3));
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("H")){//Leaving hall
			doors.add(new Point(11,18));//left
			doors.add(new Point(12,18));//middle
			doors.add(new Point(13,18));//right
			doors.add(new Point(15,20));//bottom right
			System.out.println("Which door would you like to exit from?\n left|middle|right|bottom right");			
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
			}
			else if(command.equals("middle")  && !(this.getTiles()[doors.get(1).y-1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
			}
			else if(command.equals("right")  && !(this.getTiles()[doors.get(2).y-1][doors.get(2).x] instanceof Player)){
				player.setLocation(doors.get(2));
			}
			else if(command.equals("bottom right")  && !(this.getTiles()[doors.get(3).y][doors.get(3).x+1] instanceof Player)){
				player.setLocation(doors.get(3));
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
			}
			else if(command.equals("bottom")  && !(this.getTiles()[doors.get(1).y+1][doors.get(1).x] instanceof Player)){
				player.setLocation(doors.get(1));
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
			}
			else if(command.equals("left")  && !(this.getTiles()[doors.get(1).y][doors.get(1).x-1] instanceof Player)){
				player.setLocation(doors.get(1));
			}
		}
		else if(this.getTiles()[py-1][px-1].print().equals("S")){//Leaving study
			doors.add(new Point(18,21));
			if((this.getTiles()[doors.get(0).y-1][doors.get(0).x] instanceof Player)
					&& (this.getTiles()[doors.get(0).y][doors.get(0).x-1] instanceof Player)){//This checks if all doors are blocked by players
				System.out.println("All your exits are blocked! Your turn is over!");//Turn ends if you have no way out. unlucky
				Game.roll=0;
			}else{
			player.setLocation(doors.get(0));}
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
					Point roomPos=new Point(2,2);
					Game.roll=1;
						/*if(player.print()=="2"){
							 roomPos=new Point(3,2);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="3"){
							 roomPos=new Point(4,2);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="4"){
							 roomPos=new Point(2,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="5"){
							 roomPos=new Point(3,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}
						if(player.print()=="6"){
							 roomPos=new Point(4,4);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
						}*/
						player.inRoom=true;
						player.setLocation(player.getRoomPos()[0]);
						//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="d"){//enter dining room
					Point roomPos=new Point(3,11);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(4,11);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(5,11);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(3,13);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(4,13);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(5,13);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[1]);
					//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="l"){//if they enter the lounge
					Point roomPos=new Point(2,21);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(3,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(4,21);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(2,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(3,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(4,23);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[2]);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="B"){//enter ballroom
					Point roomPos=new Point(12,3);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(13,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(14,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(12,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(13,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(14,5);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[3]);
					//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="H"){//enter hall
					Point roomPos=new Point(12,18);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(13,18);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(14,18);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(12,20);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(13,20);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(14,20);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[4]);
					//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="C"){//enter conservatory
					Point roomPos=new Point(21,1);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(22,1);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(23,1);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(21,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(22,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(23,3);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[5]);
					//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="b"){//billiard room
					Point roomPos=new Point(21,8);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(22,8);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(23,8);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(21,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(22,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(23,10);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[6]);
					//player.setLocation(roomPos);
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="L"){//library
					Point roomPos=new Point(20,14);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(21,14);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(22,14);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(20,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(21,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(22,16);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[7]);
					//player.setLocation(roomPos);  
				}
				else if(this.getTiles()[lookahead.y][lookahead.x].print()=="S"){//study
					Point roomPos=new Point(19,22);
					Game.roll=1;
					/*if(player.print()=="2"){
						 roomPos=new Point(20,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="3"){
						 roomPos=new Point(21,22);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="4"){
						 roomPos=new Point(19,24);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="5"){
						 roomPos=new Point(20,24);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}
					if(player.print()=="6"){
						 roomPos=new Point(21,24);//WE NEED TO FIX THIS SO IT"S NOT ONE LOCATION	
					}*/
					player.inRoom=true;
					player.setLocation(player.getRoomPos()[8]);
					//player.setLocation(roomPos);
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
	/*
	//checks if a player has nowhere to move
	private Boolean boxedIn(Player player){
		if(!this.tiles[player.getLocation().y+1][player.getLocation().x].print().equals("+") &&
			!this.tiles[player.getLocation().y-1][player.getLocation().x].print().equals("+") &&
			!this.tiles[player.getLocation().y][player.getLocation().x-1].print().equals("+") &&
			!this.tiles[player.getLocation().y][player.getLocation().x+1].print().equals("+")){
			return true;
		}
		
		return false;
	}*/
	

}
