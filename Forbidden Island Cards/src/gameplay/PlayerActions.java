package gameplay;

import java.util.Scanner;

import cards.Card;
import cards.FloodDiscardPile;
import cards.Hand;
import cards.HelicopterLift;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import player.Player;
import player.Team;

public class PlayerActions {

	private Player player;
	private Scanner inputScanner;
	private int		actionsLeft;
	private Team theTeam;
	private TreasureDiscardPile treasurePile;
	private TreasureHandler theTreasureHandler;
	private FloodDiscardPile floodPile;
	private boolean gameWon;

	public PlayerActions(Player thisPlayer, Scanner inputScanner) {
		this.player = thisPlayer;
		this.inputScanner = inputScanner;
		this.actionsLeft = 3;
		this.theTeam = Team.getInstance();
		this.treasurePile = TreasureDiscardPile.getInstance();
		this.floodPile = FloodDiscardPile.getInstance();
		gameWon= false;
	}

	public void doActions() {
		int userInput;
		boolean validInput;
		boolean turnOver = false;

		System.out.println("It is " + player.getName() + "'s turn! Press [return] to begin.");
		@SuppressWarnings("unused")
		String playerStartsTurn = inputScanner.nextLine(); // Make player press return to confirm turn start

		while (!turnOver) {
			giveOptions();
			userInput = 0;
			validInput = false;

			while (!validInput) {
				String userString = inputScanner.nextLine();
				try {
					userInput = Integer.parseInt(userString);
				} catch (NumberFormatException e) {
					continue;
				}
				if ((userInput >= 0) && (userInput <= 10)) {
					validInput = true;
				}
			}
			switch (userInput) {
			case 0:
				turnOver = true;
				break;
			case 1:
				move();
				break;
			case 2:
				shoreUp();
				break;
			case 3:
				captureATreasure();
                break;
            case 4:
			    giveCard();
                break;
            case 5:
			    useHelicopterLift();
				break;
			case 6:
			    useHelicopterLift();
                break;
            case 7:
			    displayHands();
                break;
            case 8:
			    lookDiscarded(inputScanner);
				break;
            case 9:
			    useTeammateCard();
				break;
			default:
				printout("Invalid Input :(");
			}
		}
		if(seeIfWon()){
			turnOver=true;
		}
		else{
			printout("Your turn has ended.\n");
		}
	}
    
    private void giveOptions() {
		printout("\nWhat do you want to do? You have " +actionsLeft +" actions remaining");
		printout("[9]	See if any teammates wish to use their special cards");
		printout("[8]	Look at the discard pile.");
		printout("[7]	Have a look at everyone's hands");
		printout("[6]	Use Sandbags");
		printout("[5]	Use Helicopter Lift!");
		printout("[4]	Give a card to a teammate. (1 action)");
		printout("[3]	Capture a Treasure. (1 action)");
		printout("[2]	Shore Up. (1 action)");
		printout("[1]   Move. (1 action)");
		printout("[0]   END YOUR TURN");
	}
	
	private void move() {
		if(!getActions()){
			return;
		}
		player.getPawn().move();	
		actionsLeft--;
	}

	private boolean getActions(){
		if(actionsLeft>0){
			return true;
		}
		else{
			System.out.println("\nYou can't do that, you have no actions left :(");
			return false;
		}
	}

	private void shoreUp(){
		if(!getActions()){
			return;
		}
		player.getPawn().shoreUp();
		actionsLeft--;
	}
	
	private void lookDiscarded(Scanner inputScanner) {
		if(Choices.getYesOrNo(inputScanner,"Do you want to look at the Treasure or Flood Discard Pile?", "Flood", "Treasure")){
			System.out.println("The Treasure Discard Pile: ");
			treasurePile.printPile();
		}
		else{
			System.out.println("The Flood Discard Pile: ");
			floodPile.printPile();		
		}
    }

    private void displayHands() {
        theTeam.showAllHands();
    }

    private void useHelicopterLift() {
		theTeam.useHelicopterLift(inputScanner, player);
	}

    private void useSandbags() {
		theTeam.useSandbags(player);
	}
	
	private void useTeammateCard(){
		gameWon = theTeam.enquirePlayers(inputScanner, true);
	}

	private void captureATreasure(){
		if(!getActions()){
			return;
		}
		if(theTreasureHandler.captureTreasure(player)){
			actionsLeft--;
		}
	}

    private void giveCard() {
		if(!getActions()){
			return;
		}
		if(!player.getHand().canTrade()){
			System.out.println("You can't trade right now :(");
			return;
		}
		System.out.println("Who do you want to give a card to?");
		displayHands();
		Player playernum = theTeam.choosePlayer(inputScanner, player);
		boolean validSelection = false;
		while(!validSelection){
			int cardnum = player.chooseFromHand(inputScanner, "give? You can't give Sandbags or Helicopter Lift", true);
			validSelection = player.giveTreasureCard(playernum, cardnum, inputScanner);
		}
		actionsLeft--;
	}
    
	private void printout(String toPrint) {
		System.out.println(toPrint);
	}

	public boolean seeIfWon() {
		return false;
	}

	public boolean canWin(){
		// FOR EACH PAWN IF THEIR TILE IS FOOLS LANDING, AND ALL 4 TREASURES ARE CAPTURED == RETURN TRUE!!
		return false;
	}
}