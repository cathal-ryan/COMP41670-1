package gameplay;

import java.util.Scanner;
import java.util.ResourceBundle.Control;

import cards.Card;
import cards.FloodDiscardPile;
import cards.Hand;
import cards.HelicopterLift;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import player.Player;
import player.Team;
import gameplay.Controller;
import gameplay.GameOutputs;

public class PlayerActions {

	private Scanner inputScanner;
	private boolean gameWon;
	private Controller	theController;
	private GameOutputs theOutputs;
	private GameInputs theInputs;

	public PlayerActions(Scanner inputScanner) {
		theController = Controller.getInstance();
		theOutputs = new GameOutputs();
		theInputs = new GameInputs();
		this.inputScanner = inputScanner;
		gameWon= false;
	}

	public void doActions() {
		int userInput;
		theOutputs.printTurnStart(theController.returnPlayerName());
		theInputs.nextLine(); // Make player press return to confirm turn start
		while (!theController.getTurnOver()){
			theOutputs.giveOptions(theController.getActionsLeft());
			userInput = theInputs.turnChoice();
			switch (userInput) {
			case 0:
				theController.setTurnOver();
				break;
			case 1:
				theController.movement();
				break;
			case 2:
				theController.shoreUp();
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
			    useSandbags();
                break;
            case 7:
				theController.lookAtHands();
                break;
            case 8:
				theController.lookDiscarded();
				break;
            case 9:
			    useTeammateCard();
				break;
			default:
				theOutputs.generalError();
			}
		}
		if(!theController.isGameOver()){
			theOutputs.printTurnOver();
		}
	}

    private void useHelicopterLift() {
		theController.useHelicopterLift(null);
	}

    private void useSandbags() {
		theController.useSandbags();
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
		theController.giveCard();
	}
}