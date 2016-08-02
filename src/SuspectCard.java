import java.awt.Point;

public class SuspectCard implements Card {

	String name;
	Point p;
	public SuspectCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}