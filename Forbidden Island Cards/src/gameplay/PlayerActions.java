package gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cards.Card;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDiscardPile;
import cards.WaterRiseCard;
import player.Player;
import player.PlayerList;

public class PlayerActions {

	private Player player;
	private Scanner inputScanner;

	public PlayerActions(Player thisPlayer, Scanner inputScanner) {
		this.player = thisPlayer;
		this.inputScanner = inputScanner;
	}

	public void doActions() {
		int actionsLeft = 3;
		int userInput;
		boolean validInput;
		boolean turnOver = false;

		System.out.println("It is " + player.getName() + "'s turn! Press [return] to begin.");
		@SuppressWarnings("unused")
		String playerStartsTurn = inputScanner.nextLine(); // Make player press return to confirm turn start

		while (!turnOver) {
			if (actionsLeft <= 0) {
				turnOver = true;
			}
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
				player.getPawn().move();
				break;
			case 2:
				player.getPawn().shoreUp();
				break;
			case 3:
				captureATreasure();
                break;
            case 4:
			    giveCard();
                break;
            case 5:
			    useSpecialCards();
                break;
            case 6:
			    displayHands();
                break;
            case 7:
			    lookDiscarded();
				break;
			default:
				printout("why am i hereCASE ERROR IN PlayerTurn.doTurn()");
			}

		}
		printout("Your turn has ended.\n");
	}

	private void lookDiscarded() {
		System.out.println("The Treasure Discard Pile: ");
        TreasureDiscardPile.getInstance().printPile();
    }

    private void displayHands() {
        PlayerList.getInstance().showAllHands();
    }

    private void useSpecialCards() {
        System.out.println("ff");
    }

	private void captureATreasure(){
		TreasureHandler.getInstance().captureTreasure(player);
	}

    private void giveCard() {
		System.out.println("Who do you want to give a card to?");
		displayHands();
		Player playernum = PlayerList.getInstance().choosePlayer(inputScanner);
		int cardnum = player.chooseFromHand(inputScanner, "give");
		player.giveTreasureCard(playernum, cardnum);
	}
    
    private void giveOptions() {
		printout("\nWhat do you want to do? You have " + " actions remaining");
		printout("[8]	NOTHING!");
		printout("[7]	LOOK AT DISCARDED!");
		printout("[6]	Look at Everyone's Hands!");
		printout("[5]	N/A!");
		printout("[4]	Give a card to a buddy!");
		printout("[3]	Capture a Treasure!!");
		printout("[2]	N/A!!");
		printout("[1]   N/A!!");
		printout("[0]   END YOUR TURN");
    }
    
	private void printout(String toPrint) {
		System.out.println(toPrint);
	}

	public boolean seeIfWon() {
		return false;
	}
}