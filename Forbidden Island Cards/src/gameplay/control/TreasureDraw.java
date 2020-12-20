package gameplay.control;

import cards.TreasureCard;
import cards.WaterRiseCard;
import enums.TreasureCardEnums;
import gameplay.view.*;
    
/**
 * Treasure Draw class manages drawing treasure cards.
 * Each player should draw 2 cards after completing actions
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class TreasureDraw {

    // ===========================================================
    // Setup Variables
    // ===========================================================
    private Controller      theController;
    private GameOutputs     theOutputs;
    private GameInputs      theInputs;    

    /**
     * Constructor for a Treasure Draw.
     */
    public TreasureDraw() {
        this.theController = Controller.getInstance();
        theOutputs = new GameOutputs();
        theInputs = new GameInputs();    
    }
    
	/**
	 * Manages treasure cards being drawn, takes 2 cards and allows controller
	 * to add to hand. If waters rise card, necessary logic is enacted to raise
	 * water meter and to check if game is lost at each point.
	 */
    protected void doTreasureDraw() {
        int i =0;
        theOutputs.treasureTime();
        while(i<2){
            theOutputs.cardsLeft(2-i);
            // Ask the players if anyone wants to play a special card before drawing.
            boolean isSpecialPlayed = theController.enquirePlayers(false);
            if(theController.isGameOver()){
                return; // Game Over
            }
            // If special card was played, make player press confirm to draw next card
            if(isSpecialPlayed){
                theOutputs.pressReturn();
                theInputs.confirm();
            }
            TreasureCard c1 = theController.getTreasureCard();
            // If the card is WatersRise, increment water meter and check if game over
            if ((TreasureCardEnums) c1.getName() == TreasureCardEnums.WATERS_RISE){
                theController.addToPile(c1);
                theOutputs.waterRise(theController.getWaterLevel());
                if(theController.isGameOver()){
                    return; // Game Over
                }
            }
            // Otherwise give the treasure card
            else{
                theController.addCardtoHand(c1);
                theOutputs.drawnCard(c1.getName().toString());
            }
            i++;
        }
        // If hand size too big, discard
        while (theController.getHandSize(null) > 5) {
            theController.discardTreasure(null);
        }
        // Finally show hand
        String name = theController.returnPlayerName(-1);
        String pawn = theController.returnPawnChar(-1);
        theOutputs.printHand(name,pawn,theController.showAHand());
    }
}    

