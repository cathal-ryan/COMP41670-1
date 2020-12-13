package gameplay.control;

import java.util.ResourceBundle.Control;

import cards.Card;
import cards.FloodDiscardPile;
import cards.Hand;
import cards.HelicopterLift;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import player.Player;
import player.Team;
import gameplay.control.Controller;
import gameplay.view.*;

public class PlayerActions {

	private boolean gameWon;
	private Controller	theController;
	private GameOutputs theOutputs;
	private GameInputs theInputs;

	public PlayerActions() {
		theController = Controller.getInstance();
		theOutputs = new GameOutputs();
		theInputs = new GameInputs();
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
				theController.giveCard();
				break;
            case 5:
				theController.useHelicopterLift(null);
				break;
			case 6:
				theController.useSandbags(null);
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
	
	private void useTeammateCard(){
		theController.enquirePlayers(true);
	}

	private void captureATreasure(){
		if(!getActions()){
			return;
		}
		if(theTreasureHandler.captureTreasure(player)){
			actionsLeft--;
		}
	}
}