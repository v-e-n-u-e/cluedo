
public class RoomCard implements Card {

	String name;
	public RoomCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}