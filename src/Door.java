import java.awt.Point;

public class Door implements Tile {
	Point pos;
	String name;
	String connectedRoom;
	
	public Door(String name ,Point pos){
		this.pos=pos;
		this.name=name;
		this.connectedRoom=findConnected();
	}
	
	public String getName(){
		return name;
	}

	@Override
	public String print() {
		return "D";
	}
	
	public String getRoom(){
		return this.connectedRoom;
	}
	
	//will figure out what room it connects to based on its position
	public String findConnected(){
	if(pos.x>=0 && pos.x<=7){//This means it's either kitchen, dining room, or lounge (LEFT SIDE)
		if(pos.y==6){//this means it's the kitchen
			return "kitchen";
		}
		if(pos.y>=12 && pos.y<=15){//this means it's the dining room
			return "dining room";
		}
		if(pos.y==19){//this means it's the lounge
			return "kitchen";
		}
	}
	if(pos.x>=8 && pos.x<=16){//this means it's either ballroom or hall (MIDDLE)
		if(pos.y>=5 && pos.y<=7){//this means it's the ballroom
			return "ballroom";
		}
		if(pos.y>=18){//this means it's the hall
			return "hall";
		}
	}
	if(pos.x>=17){//this means it's either conservatory, billiard room, library, or study
		if(pos.y==4){//this means it's the conservatory
			return "conservatory";
		}
		if(pos.y>=8 && pos.y<=12){//this means it's the b-ball room
			return "billiard room";
		}
		if(pos.y>=14 && pos.y<=18){//this means it's the library
			return "library";
		}
		if(pos.y==22){//this means it's the study
			return "study";
		}
	}
	return "Not a door? hello?";
	}
}
