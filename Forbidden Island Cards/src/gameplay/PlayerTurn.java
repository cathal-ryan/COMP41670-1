package gameplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cards.Card;
import cards.Deck;
import cards.FloodDeck;
import cards.TreasureDeck;
import enums.TilesEnums;
import player.Player;

/**
 * PlayerTurn class manages all of the options a player can make whilst 
 * they have a turn.
 * @author Conor Kennedy and Fergal Lonergan
 *
 */
public class PlayerTurn {

	// ===========================================================
	// Setup Variables
	// ===========================================================
	private Player          player;
	private Scanner         inputScanner;

    public PlayerTurn(Player thisPlayer, Scanner inputScanner) {
		this.player = thisPlayer;
		this.inputScanner = inputScanner;
	}

    public void doTurn() {
		int 	actionsLeft = 3;
	    int     userInput;
	    boolean validInput;
		boolean turnOver = false;
		
        System.out.println("It is "+player.getName()+"'s turn! Press [return] to begin.");
        @SuppressWarnings("unused")
        String playerStartsTurn = inputScanner.nextLine(); // Make player press return to confirm turn start
        
		while (!turnOver) {
			if(actionsLeft<=0) {
				turnOver=true;
			}
			giveOptions();
			
			userInput  = 0;
			validInput = false;
			
			while (!validInput) {
				String userString = inputScanner.nextLine();
				
				try {userInput = Integer.parseInt(userString);} 
	            catch (NumberFormatException e) {continue;}
				
				if ((userInput >= 0) && (userInput <= 5)) {
					validInput = true;
				}
			}
			
			switch (userInput) {
			    case 0:  turnOver = true;                  break;
			    case 1:  drawCard();              		break;
			    case 2: printHand();					break;
			    case 3: discard();						break;
			    default: printout("why am i hereCASE ERROR IN PlayerTurn.doTurn()");
			}
			
		}
		drawCard();
		printout("Your turn has ended.\n");
	}


	private void giveOptions() {
		printout("\nWhat do you want to do? You have "+ " actions remaining");
		printout("[3]	Discard a card!");
		printout("[2]	Print hand!");
		printout("[1]    Take a Card!");
		printout("[0]    end  turn");
	}
	
	public void printHand() {
		List<Card> handOfCards = new ArrayList<Card>();
		handOfCards = player.showHand();
		for(int i=0;i<handOfCards.size();i++) {
			Card ceh = handOfCards.get(i);
			System.out.println(ceh.getName());
		}
	}
	
	public void drawCard() {
				player.drawTreasureCard(TreasureDeck.getInstance().dealCard());
				player.drawTreasureCard(TreasureDeck.getInstance().dealCard());
				while(player.handSize()>5) {
					discard();
				}
	}
	
	public void discard() {
		int userIn  = 0;
		boolean validIn = false;
		System.out.println("Which of the following cards would you like to discard?");
		for(int i=0;i<player.showHand().size();i++) {
			System.out.println("["+i+"]: "+player.showHand().get(i).getName());
		}
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {userIn = Integer.parseInt(userString);} 
	        catch (NumberFormatException e) {continue;}
			if ((userIn >= 0) && (userIn <= player.showHand().size())) {
				validIn = true;
			}
		}
		player.getHand().removeCard(userIn);
	}
	
	
	private void printout(String toPrint) {
		System.out.println(toPrint);
	}
}