
public class Guess {

	int room;
	int weapon;
	int suspect;
	SuspectCard murderer;
	WeaponCard murderWeapon;
	RoomCard murderRoom;
	
	public Guess (int room, int weapon, int suspect){
		this.room = room;
		this.weapon = weapon;
		this.suspect = suspect;
		
		/* mapping numbers to cards*/
		switch(this.room) {
		
		case 1: 
			murderRoom = new RoomCard("kitchen");
		
		case 2: 
			murderRoom = new RoomCard("ballRoom");
		
		case 3: 
			murderRoom = new RoomCard("conservatory");
		
		case 4: 
			murderRoom = new RoomCard("billiardRoom");
		
		case 5: 
			murderRoom = new RoomCard("library");
		
		case 6: 
			murderRoom = new RoomCard("study");
		
		case 7: 
			murderRoom = new RoomCard("hall");
		
		case 8: 
			murderRoom = new RoomCard("lounge");
		
		case 9: 
			murderRoom = new RoomCard("diningRoom");
		}
		
		
		switch(this.weapon) {
		
		case 1: 
			murderWeapon = new WeaponCard("candleStick");
		case 2: 
			murderWeapon = new WeaponCard("knife");
		case 3: 
			murderWeapon = new WeaponCard("leadPipe");
		case 4: 
			murderWeapon = new WeaponCard("rope");
		case 5: 
			murderWeapon = new WeaponCard("wrench");
		case 6: 
			murderWeapon = new WeaponCard("revolver");
		}
		
		
		switch(this.suspect) {
		
		case 1: 
			murderer = new SuspectCard("missScarlett");
		case 2: 
			murderer = new SuspectCard("professorPlum");
		case 3: 
			murderer = new SuspectCard("mrsPeacock");
		case 4: 
			murderer = new SuspectCard("reverendGreen");
		case 5: 
			murderer = new SuspectCard("colonelMustard");
		case 6: 
			murderer = new SuspectCard("mrsWhite");
		
		}
		/*--------------------------*/
	}
	public SuspectCard getMurderer(){
		return this.murderer;
	}
	public RoomCard getRoom(){
		return this.murderRoom;
	}
	public WeaponCard getWeapon(){
		return this.murderWeapon;
	}
	
}
