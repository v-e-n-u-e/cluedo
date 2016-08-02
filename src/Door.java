import java.awt.Point;

public class Door implements Tile {
	Point pos;
	String name;
	
	public Door(String name ,Point pos){
		this.pos=pos;
		this.name=name;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public String print() {
		
		return "D";
	}

}
