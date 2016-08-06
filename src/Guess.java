
public class Guess {
	//THIS PROGRAM WAS MADE BY CONNOR MOOT AND CALLUM CROSBY

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
		
		case 0: 
			murderRoom = new RoomCard("kitchen");
			break;
		case 1: 
			murderRoom = new RoomCard("diningRoom");
			break;
		case 2: 
			murderRoom = new RoomCard("lounge");
			break;
		case 3: 
			murderRoom = new RoomCard("ballRoom");
			break;
		case 4: 
			murderRoom = new RoomCard("hall");
			break;
		case 5: 
			murderRoom = new RoomCard("conservatory");
			break;
		case 6: 
			murderRoom = new RoomCard("billiardRoom");
			break;
		case 7: 
			murderRoom = new RoomCard("library");
			break;
		case 8: 
			murderRoom = new RoomCard("study");
			break;
		}
		
		
		switch(this.weapon) {
		
		case 1: 
			murderWeapon = new WeaponCard("candleStick");
			break;
		case 2: 
			murderWeapon = new WeaponCard("knife");
			break;
		case 3: 
			murderWeapon = new WeaponCard("leadPipe");
			break;
		case 4: 
			murderWeapon = new WeaponCard("rope");
			break;
		case 5: 
			murderWeapon = new WeaponCard("wrench");
			break;
		case 6: 
			murderWeapon = new WeaponCard("revolver");
			break;
		}
		
		
		switch(this.suspect) {
		
		case 1: 
			murderer = new SuspectCard("missScarlett");
			break;
		case 2: 
			murderer = new SuspectCard("professorPlum");
			break;
		case 3: 
			murderer = new SuspectCard("mrsPeacock");
			break;
		case 4: 
			murderer = new SuspectCard("reverendGreen");
			break;
		case 5: 
			murderer = new SuspectCard("colonelMustard");
			break;
		case 6: 
			murderer = new SuspectCard("mrsWhite");
			break;
		
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
