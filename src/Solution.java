public class Solution {

	Card room; //THE ROOM THE MURDER TOOK PLACE IN
	Card weapon; //THE WEAPON THE MURDERER USED
	Card suspect; //THE KILLER
	
	//Randomly generate all of the above each game?
	
	public Boolean checkSolution(Card checkRoom, Card checkWeapon, Card checkSuspect){
		if(this.room.equals(checkRoom) && this.weapon.equals(checkWeapon) && this.suspect.equals(checkSuspect)){
		return true;//WINNER WINNER CHICKEN DINNER
		}
		return false;//NOT A WINNER
	}
	
	
}
