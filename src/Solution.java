public class Solution {

	Card room; //THE ROOM THE MURDER TOOK PLACE IN
	Card weapon; //THE WEAPON THE MURDERER USED
	Card suspect; //THE KILLER
	
	//Randomly generate all of the above each game?
	public Solution(Card room,Card weapon,Card suspect){
		
	}
	
	public Boolean checkSolution(Card checkRoom, Card checkWeapon, Card checkSuspect){
		if(this.room.getName().equals(checkRoom.getName()) 
				&& this.weapon.getName().equals(checkWeapon.getName()) 
				&& this.suspect.getName().equals(checkSuspect.getName())){
		return true;//WINNER WINNER CHICKEN DINNER
		}
		return false;//NOT A WINNER
	}
	
	
}
