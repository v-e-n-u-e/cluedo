import java.awt.Point;

public class Room implements Tile {

	Point pos;
	String name;
	String weapon;
	public Room(String name,Point pos){
		this.name=name;
		this.pos=pos;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public String getWep(){
		return this.weapon;
	}


	@Override
	public String print() {
		if(this.getName().equals("kitchen")){
			return "K";
		}else if(this.getName().equals("library")){
			return "L";
		}else if(this.getName().equals("ballRoom")){
			return "B";
		}else if(this.getName().equals("conservatory")){
			return "C";
		}else if(this.getName().equals("diningRoom")){
			return "d";
		}else if(this.getName().equals("billiardRoom")){
			return "b";
		}else if(this.getName().equals("lounge")){
			return "l";
		}else if(this.getName().equals("hall")){
			return "H";
		}else if(this.getName().equals("study")){
			return "S";
		}
		return" ";
	}
	
}
