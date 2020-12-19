package gameplay.control;

import cards.TreasureDeckCard;
import cards.WaterRiseCard;
import gameplay.view.*;
    
    /**
     * Treasure Draw class manages drawing treasure cards
     * 
     * @author Cathal Ryan and Conor Kneafsey
     *
     */
    public class TreasureDraw {
    
        // ===========================================================
        // Setup Variables
        // ===========================================================
        private boolean lost;
        private boolean won;
        private Controller theController;
        private GameOutputs theOutputs;
        private GameInputs theInputs;    
    
        public TreasureDraw() {
            this.theController = Controller.getInstance();
            theOutputs = new GameOutputs();
            theInputs = new GameInputs();    
            this.lost = false;
            this.won = false;
        }
    
        public void doTreasureDraw() {
            int i =0;
            theOutputs.treasureTime();
            while(!lost && (i<2)){
                theOutputs.cardsLeft(2-i);
                boolean isSpecialPlayed = theController.enquirePlayers(false);
                if(theController.isGameOver()){
                    return;
                }
                if(isSpecialPlayed){
                    theOutputs.pressReturn();
                    theInputs.confirm();
                }
                TreasureDeckCard c1 = theController.getTreasureCard();
                if (c1 instanceof WaterRiseCard){
                    theController.addToPile(c1);
                    theOutputs.waterRise(theController.getWaterLevel());
                    if(theController.isGameOver()){
                        return; // game over
                    }
                }
                else {
                    theController.addCardtoHand(c1);
                    theOutputs.drawnCard(c1.getName().toString());
                }
                i++;
            }
            while (theController.getHandSize(null) > 5) {
                theController.discardTreasure(null);
            }
            String name = theController.returnPlayerName(-1);
            String pawn = theController.returnPawnChar(-1);
            theOutputs.printHand(name,pawn,theController.showAHand());
        }
}    

