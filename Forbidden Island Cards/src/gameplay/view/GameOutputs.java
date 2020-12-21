package gameplay.view;

import enums.TypeEnums;
import java.util.List;
import board.*;

import java.awt.Point;

/**
 * Provides all communications out to the player, with system.out as the means of communication
 * Allows for easy refactoring to a GUI.
 * @author Cathal Ryan and Conor Kneafsey
 */
public class GameOutputs {

    public GameOutputs(){}
    
    /**
     * Tells player their input is wrong.
     */
	public void generalError() {
        System.out.println("Invalid input.");
	}
	
    /**
     * Tells player their turn has begun.
     * @param name The player's name
	 * @param pawn The player's pawn
     */
    public void printTurnStart(String name, String pawn) {
        System.out.println("It is " + name + "'s turn! ( "+pawn+" ) Press [return] to begin.");
    }
    
    /**
     * Gives the major options of a turn to the player.
     * @param name The player's name
	 * @param pawn The player's pawn
	 * @param actions Integer number of actions the player has left
     */
    public void giveOptions(String name,String pawn, int actions) {
        System.out.println("\n"+name +"'s turn. ( "+pawn+" ) What do you want to do? You have " +actions +" actions remaining.");
        System.out.println("[10]\tLook at the game board.");
		System.out.println("[9]\tLook at everyone's hands.");
		System.out.println("[8]\tLook at the discard piles.");
		System.out.println("[7]\tUse Helicopter Lift");
		System.out.println("[6]\tUse Sandbags.");
		System.out.println("[5]\tAsk the team if anyone wants to use a special card.");
		System.out.println("[4]\tGive a card to a teammate. (1 action)");
		System.out.println("[3]\tCapture a Treasure. (1 action)");
		System.out.println("[2]\tShore Up. (1 action)");
		System.out.println("[1]\tMove. (1 action)");
		System.out.println("[0]\tEnd your turn.");
    }

    /**
     * Gives the movement directions.
     */
    public void whereMove() {
        System.out.println("Which direction would you like to move?");
        System.out.println("[w]     Move Up");
        System.out.println("[a]     Move Left");
        System.out.println("[s]     Move Down");
        System.out.println("[d]     Move Right");
    }

    public void whereShoreUp() {
        System.out.println("What tile would you like to shore up?");
    }
    
    public void cantShoreUp() {
        System.out.println("You have chosen a tile that cannot be shored up from your location");
    }

    public void shoreAgain() {
        System.out.println("Would you like to choose another tile to shore up?");
    }

    public void printTurnOver(){
        System.out.println("Your turn is over.\n");
    }

	public void noActionsLeft() {
        System.out.println("You have no actions left. :(");
	}

	public void printFloodorTreasure() {
        System.out.println("Do you want to look at the Treasure or Flood Discard Pile?");
    }
	
    /**
     * Prints a discard pile.
     * @param Treasure if its treasure deck, give true, if flood give false
     * @param pile The discard pile to be shown
     */
    public void printPile(boolean Treasure, String pile){
        if(Treasure){
            System.out.println("The Treasure Discard Pile: "+ pile);
        }
        else{
            System.out.println("The Flood Discard Pile: "+ pile);
        }
    }

    /**
     * Shows a player hand.
     * @param playerName Player's name
     * @param pawn Their pawn to be printed
     * @param hand The hand to be shown
     */
    public void printHand(String playerName, String pawn, String hand){
        System.out.println(pawn + " "+ playerName+"'s hand: " + hand );
    }

	public void noSandbags() {
        System.out.println("You don't have a Sandbags Card..");
	}

	public void noHeli() {
        System.out.println("You don't have a Helicopter Lift card..");
	}

    public void whoWillFly(){
        System.out.println("Who is gonna fly there?");
    }

    public void heliAnyoneElse(){
        System.out.println("Is anyone else going to fly here?");
    }

    public void heliWhere(){
        System.out.println("Where do you want to move to?");
    }

    /**
     * Gives a list of options
     * @param i The option number
     * @param name The name of first option
     * @param pawn Optional extra information on option (usually a pawn for a player)
     */
	public void showOption(int i, String name, String pawn) {
        System.out.println("["+i+"] "+ name+ " "+ pawn);
    }

	public void choosePl() {
        System.out.println("\nSelect a Player:");
	}


	public void cantTrade() {
        System.out.println("You can't give any cards right now!");
	}

    /**
     * Prompts selection of player for trading
     */
	public void whoToTrade() {
        System.out.println("Who do you want to give a card to?");
	}

    /**
     * Prompts selection of cards, in two modes
     * @param trading If true, the player is trading, if false the player is discarding
     */
	public void cardChoice(boolean trading) {
        System.out.print("\nWhich of the cards would you like to");
        if(trading){
            System.out.print(" give? You can't give Helicopter Lift or Sandbags\n");
        }
        else{
            System.out.print(" discard");
        }
	}

    /**
     * Informs player their hand is too big and asks if they wish to see game state before discard
     * @param name Player's name
     */
	public void handTooBig(String name) {
        System.out.println("Hey, "+name+", your hand is too big.. Before discarding a card, would you like to see the current game state?");
	}

    /**
     * Player must use or lose the special card
     */
	public void useIt() {
        System.out.println("It's use it or lose it! Do you want to use this card now?");
	}

    /**
     * No special cards in the game
     */
	public void noSpecials() {
        System.out.println("No one has any special cards...");
    }
    
	public void playSpecials() {
        System.out.println("\nIs there anyone who wants to play their special card?");
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
    
    /**
     * Show cards left to draw
     * @param i The number of cards left for draw
     */
	public void cardsLeft(int i) {
        System.out.println("\n"+i+" cards to go!");
    }

    /**
     * Tell player hit return to draw
     */
    public void pressReturn(){
        System.out.println("Press [return] to draw!");
    }

    /**
     * Card drawn
     * @param name Name of card drawn
     */
	public void drawnCard(String name) {
        System.out.println("Great! You've drawn "+ name);
	}

    /**
     * Water has risen
     * @param waterLevel integer current water level
     */
	public void waterRise(int waterLevel) {
        System.out.println("Oh no! The water has risen! ");
        displayWater(waterLevel);
    }

    /**
     * Give current water level
     * @param waterLevel integer current water level
     */
    public void displayWater(int waterLevel){
        System.out.println("Water level at: "+ waterLevel);
    }

	public void floodDrawTime() {
        System.out.println("Brace Yourselves! It's time to draw flood cards.");
	}

    /**
     * Tell them a tile is sunk
     * @param tile Tile thats sunk
     */
    public void sunkTile(String tile) {
        System.out.println("Oh no! "+ tile+ " has sunk! The "+tile+" card has been removed from play!");
    }

    /**
     * Tell them a tile is flooded
     * @param tile Tile thats flodded
     */
    public void floodedTile(String string) {
        System.out.println("Oh no! "+ string+ " has been flooded!");
    }
    
    public void turnEndo(){
        System.out.println("-------------------------------------------");
    }

    /**
     * Game is Lost
     * @param gameLoss How the game was lost
     */
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
    
    /**
     * Game is Won
     * @param gameWon How the game was won
     */
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
    
	/**
     * Tells player they can't capture treasure
     * @param captureMode mode for why cannot capture treasure
     * @param tile If that reason is a treasure, then tile will display the kind of treasure already captured
     */
	public void cantCapture(TypeEnums tile, int captureMode) {
        String reason="";
        if (captureMode == 1){
            reason = ("You've already captured the "+tile);
        }
        else if (captureMode == 2){
            reason = ("You're not on the right tile to capture a treasure..");
        }
        else if (captureMode == 3){
            reason = ("You don't have enough cards..");
        }
        System.out.print("You can't capture a treasure right now. "+reason);
        System.out.print("\n");
    }
    
	/**
     * Tells player they've captured treasure
     * @param tile Treasure type captured
     */
	public void treasureCaptured(TypeEnums tile) {
	    System.out.println("\nYou have succesfully captured the "+tile+"!");
	}
    
    /**
     * Prints out all the treasures which have been captured
     * @param treasures List of captured treasures
     */
	public void showCaptured(List<TypeEnums> treasures) {
        System.out.print("Captured Treasures: ");
        // If any of the treasures match the types here, they're printed to stirng with a symbol.
	    for( TypeEnums i:treasures){
	        if(i==TypeEnums.EARTH){
	            System.out.print(i.toString() + "🌎");
	        }
	        if(i==TypeEnums.WATER){
	            System.out.print(i.toString() + "🍷");
	        }
	        if(i==TypeEnums.FIRE){
	            System.out.print(i.toString() + "🔥");
	        }
	        if(i==TypeEnums.WIND){
	            System.out.print(i.toString() + "💨");
	        }
	    }
	    System.out.println();
	}
    
    /**
     * Tells player they need to swim
     * @param name Player's name
     * @param character Player's character
     * @param type String player type eg Diver, the swimming conditions will be specified based on this and given to player
     */
	public void needToSwim(String name, String character, String type) {
	    System.out.println("\n"+name+" (" +character +") "+" must swim to safety. Where will you swim to?");
	    switch (name) {
	        case "Diver":
	            System.out.println("You are a Diver, and so can swim to the nearest tile.");
	            break;
	        case "Explorer":
	        System.out.println("You are an Explorer, and so can swim diagonally");
	            break;
	        case "Pilot":
	        System.out.println("You are a pilot, and so can fly anywhere on the board.");
	            break;
	        default:
	            System.out.println("You can swim up, down, left or right");
	    }
	}
	
	public void noMove() {
	    System.out.println("You can't move here.");
	}
	
	public void enterCoords(char x){
	    System.out.println("Please enter in "+ x + " coordinate.");
	}
	
	public void sandbagsWhere() {
	    System.out.println("Where do you want to shore up?");
	}
	
	public void cantSandbags() {
	    System.out.println("You can't use Sandbags here.");
	}
	
	public void nowhereToShore() {
	    System.out.println("There's nowhere to shore up!");
	}
	
	public void nowSelectCard() {
	    System.out.println("Now, please select a card to discard.");
    }
    
    /**
     * Prints out the board
     */
    public void printBoard() {
        Board theBoard = Board.getInstance();
        theBoard.drawBoard();
    }

	public void whereSwim(List<Point> swimmables) {
        System.out.println("You can swim to the following tiles: ");
        int i=0;
        for (Point tile:swimmables){
            i++;
            Tile tileForPrint = Board.getInstance().getTile(tile);
            System.out.print(tileForPrint.getName()+"("+(int)tile.getX()+", "+(int)tile.getY()+"), ");
            if(i%4==0)
                System.out.print("\n");
        }
        System.out.println();
	}
}
