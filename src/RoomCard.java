
public class RoomCard implements Card {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

	String name;
	public RoomCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}