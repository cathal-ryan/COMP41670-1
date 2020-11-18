package gameplay;

import java.util.Scanner;

import cards.Card;
import cards.Hand;
import cards.HelicopterLift;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import player.Player;
import player.PlayerList;

public class PlayerActions {

	private Player player;
	private Scanner inputScanner;
	private int		actionsLeft;
	private PlayerList playerList;
	private TreasureDiscardPile treasurePile;
	private TreasureHandler theTreasureHandler;

	public PlayerActions(Player thisPlayer, Scanner inputScanner) {
		this.player = thisPlayer;
		this.inputScanner = inputScanner;
		this.actionsLeft = 3;
		this.playerList = PlayerList.getInstance();
		this.treasurePile = TreasureDiscardPile.getInstance();
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
			    displayHands();
                break;
            case 7:
			    lookDiscarded();
				break;
            case 8:
			    useTeammateCard();
				break;
			default:
				printout("An error has occured. I should not be here.");
			}
			if (actionsLeft < 1) {
				turnOver = true;
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
		printout("[8]	See if any teammates wish to use their special cards");
		printout("[7]	Look at the discarded treasure pile.");
		printout("[6]	Have a look at everyone's hands");
		printout("[5]	Use Helicopter Lift!");
		printout("[4]	Give a card to a teammate.");
		printout("[3]	Capture a Treasure.");
		printout("[2]	Shore Up. ");
		printout("[1]   Move. (1 action)");
		printout("[0]   END YOUR TURN");
	}
	
	private void move() {
		player.getPawn().move();	
		actionsLeft--;
	}

	private void shoreUp(){
		player.getPawn().shoreUp();
		actionsLeft--;
	}
	
	private void lookDiscarded() {
		System.out.println("The Treasure Discard Pile: ");
        treasurePile.printPile();
    }

    private void displayHands() {
        playerList.showAllHands();
    }

    private void useHelicopterLift() {
		if(!player.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT)){
			System.out.println("You don't have a helicopter lift card :(");
			return;
		}
		int pos = player.getHand().getIndexOfCard(TreasureCardEnums.HELICOPTER_LIFT);
		player.getHand().removeCard(pos);
		if (canWin()){
			System.out.println("You've played the Helicopter Lift card with all 4 treasures captured, with all players on Fools Landing!");
		}
		System.out.println("Who do you want to move?");
		Player playerHelicopter = playerList.choosePlayer(inputScanner,null);
		playerHelicopter.getPawn().helicopterMove();
	}
	
	private void useTeammateCard(){
		System.out.println("Gonna do some");
	}

	private void captureATreasure(){
		if(theTreasureHandler.captureTreasure(player)){
			actionsLeft--;
		}
	}

    private void giveCard() {
		if(!player.getHand().canTrade()){
			System.out.println("You can't trade right now :(");
		}
		System.out.println("Who do you want to give a card to?");
		displayHands();
		Player playernum = playerList.choosePlayer(inputScanner, player);
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