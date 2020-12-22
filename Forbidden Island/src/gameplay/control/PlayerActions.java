package gameplay.control;

import gameplay.view.*;
/**
 * PlayerActions class manages all options player can make when 
 * they have a turn, and have not started draw phase.
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class PlayerActions {

	private Controller		theController; 	// Access to main game logic
	private GameOutputs 	theOutputs;		// I/O to player
	private GameInputs 		theInputs;

    /**
     * Constructor for PlayerActions.
     */
	public PlayerActions() {
		theController = Controller.getInstance();
		theOutputs = new GameOutputs();
		theInputs = new GameInputs();
	}

	/**
	 * Manages player's turn and their actions, giving all possible options
	 * whether they use an explicit action or just want to view state of game
	 */
	protected void doActions() {
		int userInput;
		String player = theController.returnPlayerName(-1);
		String pawn = theController.returnPawnChar(-1);
		theOutputs.printTurnStart(player,pawn); // Announce it is player's turn
		
		theInputs.confirm(); // Make player press return to confirm turn start
		
		// Main loop enacting each action based on user input.
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
				theController.captureATreasure();
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