package gameplay.view;
import cards.DiscardPile;
import board.Board;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class GameOutputs {

    public GameOutputs(){}
    
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
            System.out.println("The Treasure Discard Pile: "+ pile);
        }
        else{
            System.out.println("The Flood Discard Pile: "+ pile);
        }
    }

    public void printHand(String playerName, String hand){
        System.out.println(playerName+"'s hand: \n" + hand );
    }

	public void generalError() {
        System.out.println("Invalid input.");
	}

	public void noSandbags() {
        System.out.println("You don't have a Sandbags Card..");
	}

	public void noHeli() {
        System.out.println("You don't have a Helicopter Lift card..");
	}

	public void heliPlaceSelect() {
        System.out.println("Where do you want to move to?");
    }
    
    public void whoWillFly(){
        System.out.println("Who is gonna fly there?");
    }

    public void heliAnyoneElse(){
        System.out.println("Is anyone else going to fly here?");
    }

	public void showOption(int i, String string) {
        System.out.println("["+i+"] "+ string);
	}

	public void choosePl() {
        System.out.println("\nSelect a Player:");
	}

	public void cantTrade() {
        System.out.println("There's no one to trade with!");
	}

	public void whoToTrade() {
        System.out.println("Who do you want to trade with?");
	}

	public void cardChoice(boolean trading) {
        System.out.print("\nWhich of the cards would you like to");
        if(trading){
            System.out.print(" give? You can't give Helicopter Lift or Sandbags\n");
        }
        else{
            System.out.print(" discard");
        }
	}

	public void handTooBig(String name) {
        System.out.println("Hey, "+name+", your hand is too big..");
	}

	public void useIt() {
        System.out.println("It's use it or lose it! Do you want to use this card now?");
	}

	public void noSpecials() {
        System.out.println("No one has any special cards...");
	}

	public void playSpecials() {
        System.out.println("Is there anyone who wants to play their special card?");
    }

    public void whoForSpecial() {
        System.out.println("Who will play their special card?");
    }
    
    public void heliOrSand(){
        System.out.println("Do you want to play the Helicopter Lift or Sandbags card?");
    }

	public void treasureTime() {
        System.out.println("Now it's time to draw your treasure cards!");
	}

	public void cardsLeft(int i) {
        System.out.println(i+" cards to go! Press [return] to draw!");
    }

	public void drawnCard(String name) {
        System.out.println("Great! You've drawn "+ name);
	}

	public void waterRise(int waterLevel) {
        System.out.println("Oh no! The water has risen! Now at level "+waterLevel);
	}

	public void floodDrawTime() {
        System.out.println("Brace Yourselves! It's time to draw flood cards.");
	}

    public void sunkTile(String string) {
        System.out.println("Oh no! "+ string+ " has been flooded!");
    }

    public void floodedTile(String string) {
            System.out.println("Oh no! "+ string+ " has been flooded!");
    }
    
    public void turnEndo(){
        System.out.println("-------------------------------------------");
    }

    public void printBoard() {
        Board theBoard = Board.getInstance();
        theBoard.drawBoard();
    }

	public void gameOver(String gameLoss) {
        System.out.println("  ▄▀  ██   █▀▄▀█ ▄███▄       ████▄     ▄   ▄███▄   █▄▄▄▄ ");
        System.out.println("▄▀    █ █  █ █ █ █▀   ▀      █   █      █  █▀   ▀  █  ▄▀"); 
        System.out.println("█ ▀▄  █▄▄█ █ ▄ █ ██▄▄        █   █ █     █ ██▄▄    █▀▀▌");
        System.out.println("█   █ █  █ █   █ █▄   ▄▀     ▀████  █    █ █▄   ▄▀ █  █ ");
        System.out.println(" ███     █    █  ▀███▀               █  █  ▀███▀     █   ");
        System.out.println("        █    ▀                        █▐            ▀    ");
        System.out.println("        ▀                              ▐                  ");
        System.out.println(gameLoss);
        System.out.println("\nPlease play again some time!");
	}

        public void aWinnerIsYou(String gameWon) {
                System.out.println("____    __    ____  __  .__   __. .__   __.  _______ .______          _______. __   __   __                  ");
                System.out.println("\\   \\  /  \\  /   / |  | |  \\ |  | |  \\ |  | |   ____||   _  \\        /       ||  | |  | |  |                 ");
                System.out.println(" \\   \\/    \\/   /  |  | |   \\|  | |   \\|  | |  |__   |  |_)  |      |   (----`|  | |  | |  | ");
                System.out.println("  \\            /   |  | |  . `  | |  . `  | |   __|  |      /        \\   \\    |  | |  | |  | ");
                System.out.println("   \\    /\\    /    |  | |  |\\   | |  |\\   | |  |____ |  |\\  \\----.----)   |   |__| |__| |__| ");
                System.out.println("    \\__/  \\__/     |__| |__| \\__| |__| \\__| |_______|| _| `._____|_______/    (__) (__) (__) ");
                System.out.println("                                                                                             ");
                System.out.println(gameWon);
                System.out.println("\nPlease play again some time!");
        }
}
