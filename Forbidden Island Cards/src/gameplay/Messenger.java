package gameplay;
import cards.DiscardPile;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class Messenger {

    public Messenger(){}
    
    public void printTurnStart(String name) {
        System.out.println("It is " + name + "'s turn! Press [return] to begin.");
    }

    public void giveOptions(int actions) {
		System.out.println("\nWhat do you want to do? You have " +actions +" actions remaining.");
		System.out.println("[9]\tAsk the team if anyone wants to use a special card.");
		System.out.println("[8]\tLook at the discard piles.");
		System.out.println("[7]\tHave a look at everyone's hands.");
		System.out.println("[6]\tUse Sandbags.");
		System.out.println("[5]\tUse Helicopter Lift.");
		System.out.println("[4]\tGive a card to a teammate. (1 action)");
		System.out.println("[3]\tCapture a Treasure. (1 action)");
		System.out.println("[2]\tShore Up. (1 action)");
		System.out.println("[1]\tMove. (1 action)");
		System.out.println("[0]\tEnd your turn.");
    }
    
    public void printTurnOver(){
        System.out.println("Your turn is over.");
    }

	public void noActionsLeft() {
        System.out.println("You have no actions left. :(");
	}

	public void printFloodorTreasure() {
        System.out.println("Do you want to look at the Treasure or Flood Discard Pile?");
    }
    
    public void printPile(boolean Treasure, String pile){
        if(Treasure){
            System.out.println("The Treasure Discard Pile:"+ pile);
        }
        else{
            System.out.println("The Flood Discard Pile:"+ pile);
        }
    }

    public void printHand(String playerName, String hand){
        System.out.println(playerName+"'s hand: \n" + hand );
    }

	public void generalError() {
        System.out.println("Invalid input.");
	}
}
