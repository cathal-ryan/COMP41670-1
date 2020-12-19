package gameplay.control;

import gameplay.view.*;

public class PlayerActions {

	private Controller	theController;
	private GameOutputs theOutputs;
	private GameInputs theInputs;

	public PlayerActions() {
		theController = Controller.getInstance();
		theOutputs = new GameOutputs();
		theInputs = new GameInputs();
	}

	public void doActions() {
		int userInput;
		String player = theController.returnPlayerName(-1);
		String pawn = theController.returnPawnChar(-1);
		theOutputs.printTurnStart(player,pawn);
		theInputs.confirm(); // Make player press return to confirm turn start
		while (!theController.getTurnOver() && !theController.isGameOver()){
			theOutputs.giveOptions(player, pawn, theController.getActionsLeft());
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
				theController.captureATreasure(); // should be captrure treasure
				break;
            case 4:
				theController.giveCard();
				break;
			case 5:
				theController.enquirePlayers(true);
				break;
			case 6:
				theController.useSandbags(null,false);
                break;
            case 7:
				theController.useHelicopterLift(null);
                break;
            case 8:
				theController.lookDiscarded();
				break;
            case 9:
				theController.lookAtHands();
				break;
			case 10:
				theController.lookAtBoard();
				break;
			default:
				theOutputs.generalError();
			}
		}
		if(!theController.isGameOver()){
			theOutputs.printTurnOver();
		}
	}
}