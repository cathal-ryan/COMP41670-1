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

				if ((userInput >= 0) && (userInput <= 5)) {
					validInput = true;
				}
			}

			switch (userInput) {
			case 0:
				turnOver = true;
				break;
			case 1:
				//drawCard();
				break;
			case 2:
				//FloodDiscardPile.getInstance().printAll();
				break;
			case 3:
				//discard();
				break;
			default:
				printout("why am i hereCASE ERROR IN PlayerTurn.doTurn()");
			}

		}
		printout("Your turn has ended.\n");
	}

	private void giveOptions() {
		printout("\nWhat do you want to do? You have " + " actions remaining");
		printout("[3]	Discard a card!");
		printout("[2]	Print hand!");
		printout("[1]    Take a Card!");
		printout("[0]    end  turn");
	}

	private void printout(String toPrint) {
		System.out.println(toPrint);
	}

	public boolean seeIfWon() {
		return false;
	}
}