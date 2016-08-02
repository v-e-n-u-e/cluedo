import java.awt.Point;

public class HallWay implements Tile {
	Point pos;
	
	public HallWay(Point pos){
		this.pos=pos;
	}

	@Override
	public String print() {
		return " ";
	}

}
