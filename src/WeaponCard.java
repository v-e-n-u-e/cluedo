
public class WeaponCard implements Card {

	String name;
	public WeaponCard(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
