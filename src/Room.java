import java.awt.Point;

public class Room implements Tile {

	Point pos;
	String name;
	String weapon;
	public Room(String name,Point pos){
		this.name=name;
		this.pos=pos;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public String getWep(){
		return this.weapon;
	}


	@Override
	public String print() {
		if(this.getName().equals("RK")){
			return "K";
		}else if(this.getName().equals("RL")){
			return "L";
		}else if(this.getName().equals("RB")){
			return "B";
		}else if(this.getName().equals("RC")){
			return "C";
		}else if(this.getName().equals("RD")){
			return "D";
		}else if(this.getName().equals("Rb")){
			return "b";
		}else if(this.getName().equals("Rl")){
			return "l";
		}else if(this.getName().equals("RH")){
			return "H";
		}else if(this.getName().equals("RS")){
			return "S";
		}
		return" ";
	}
	
}
