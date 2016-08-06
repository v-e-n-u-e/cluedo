
public class WeaponCard implements Card {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

	String name;
	public WeaponCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
