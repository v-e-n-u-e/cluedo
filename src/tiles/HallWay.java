package tiles;
import java.awt.Point;
//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HallWay implements Tile {
	Point pos;
	
	public HallWay(Point pos){
		this.pos=pos;
	}

	@Override
	public String print() {
		return "+";
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return new ImageIcon("hallway.png");
	}

}
