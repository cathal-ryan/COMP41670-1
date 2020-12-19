package gameplay.view;

import enums.TypeEnums;
import player.Player;

import java.util.List;

import board.Board;

/**Provides major communications with the player, with system.out
 * as a way of communicating
 * This can be changed if you wish by creating a different version of messenger e.g with a GUI
 */
public class GameOutputs {

    public GameOutputs(){}
    
    public void printTurnStart(String name, String pawn) {
        System.out.println("It is " + name + "'s turn! ( "+pawn+" ) Press [return] to begin.");
    }

    public void giveOptions(String player,String pawn, int actions) {
        System.out.println("\n"+player +"'s turn. ( "+pawn+" ) What do you want to do? You have " +actions +" actions remaining.");
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

    public void whereMove() {
        System.out.println("Which direction would you like to move?");
        System.out.println("[w]     Move Up");
        System.out.println("[a]     Move Left");
        System.out.println("[s]     Move Down");
        System.out.println("[d]     Move Right");
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
    
    public void printPile(boolean Treasure, String pile){
        if(Treasure){
            System.out.println("The Treasure Discard Pile: "+ pile);
        }
        else{
            System.out.println("The Flood Discard Pile: "+ pile);
        }
    }

    public void printHand(String playerName, String pawn, String hand){
        System.out.println(pawn + " "+ playerName+"'s hand: " + hand );
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

    public void heliWhere(){
        System.out.println("Where do you want to move to?");
    }

	public void showOption(int i, String name, String pawn) {
        System.out.println("["+i+"] "+ name+ " "+ pawn);
	}

	public void choosePl() {
        System.out.println("\nSelect a Player:");
	}

	public void cantTrade() {
        System.out.println("You can't give any cards right now!");
	}

	public void whoToTrade() {
        System.out.println("Who do you want to give a card to?");
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
        System.out.println("Hey, "+name+", your hand is too big.. Before discarding a card, would you like to see the current game state?");
	}

	public void useIt() {
        System.out.println("It's use it or lose it! Do you want to use this card now?");
	}

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

	public void cardsLeft(int i) {
        System.out.println("\n"+i+" cards to go!");
    }

    public void pressReturn(){
        System.out.println("Press [return] to draw!");
    }

	public void drawnCard(String name) {
        System.out.println("Great! You've drawn "+ name);
	}

	public void waterRise(int waterLevel) {
        System.out.println("Oh no! The water has risen! ");
        displayWater(waterLevel);
    }
    
    public void displayWater(int waterLevel){
        System.out.println("Water level at: "+ waterLevel);
    }

	public void floodDrawTime() {
        System.out.println("Brace Yourselves! It's time to draw flood cards.");
	}

    public void sunkTile(String string) {
        System.out.println("Oh no! "+ string+ " has sunk! The "+string+" card has been removed from play!");
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

		public void option(int i, String opt) {
                        System.out.println("["+i+"] "+opt);
		}

		public void cantCapture(String reason, TypeEnums tile) {
                        System.out.print("You can't capture a treasure right now. "+reason);
                        if(tile!=null){
                            System.out.print(tile);
                        }
                        System.out.print("\n");
		}

		public void treasureCaptured(TypeEnums tile) {
                        System.out.println("\nYou have succesfully captured the "+tile+"!");
		}

		public void showCaptured(List<TypeEnums> treasures) {
            System.out.print("Captured Treasures: ");
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

		public void needToSwim(String name, String type) {
            System.out.println("\n"+name + " must swim to safety. Where will you swim to?");
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
}
