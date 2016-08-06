import java.awt.Point;
//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

public class HallWay implements Tile {
	Point pos;
	
	public HallWay(Point pos){
		this.pos=pos;
	}

	@Override
	public String print() {
		return "+";
	}

}
