import java.awt.Point;

public abstract class Tile { 
	Point pos; //the position of this tile
	
	public Tile(){
		
	}
	
	public Point getLoc(){
		return this.pos;
	}
}
