package cards;
public class SuspectCard implements Card {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

	String name;
	public SuspectCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}