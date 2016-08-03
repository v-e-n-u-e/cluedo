

public class SuspectCard implements Card {

	String name;
	public SuspectCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}