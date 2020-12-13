package gameplay.control;

import java.util.Scanner;

import cards.Card;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
import cards.WaterRiseCard;
import gameplay.view.*;
import player.Player;
import player.Team;
    
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
        private Player player;
        private Scanner inputScanner;
        private boolean lost;
        private boolean won;
        private TreasureDiscardPile thePile;
        private TreasureDeck theTreasureDeck;
        private Team theTeam;
        private Controller theController;
        private GameOutputs theOutputs;
        private GameInputs theInputs;    
    
        public TreasureDraw() {
            this.theController = Controller.getInstance();
            theOutputs = new GameOutputs();
            theInputs = new GameInputs();    
            this.lost = false;
            this.won = false;
            this.thePile = TreasureDiscardPile.getInstance();
            this.theTreasureDeck= TreasureDeck.getInstance();
            this.theTeam = Team.getInstance();
        }
    
        public void doTreasureDraw() {
            int i =0;
            theOutputs.treasureTime();
            while(!lost && (i<2)){
                theController.enquirePlayers(false);
                theOutputs.cardsLeft(2-i);
                theInputs.confirm();
                TreasureDeckCard c1 = theController.getTreasureCard();
                if (c1 instanceof WaterRiseCard){
                    theController.addToPile(c1);
                    theOutputs.waterRise(theController.getWaterLevel());
                }
                else {
                    theController.addCardtoHand(c1);
                    theOutputs.drawnCard(c1.getName().toString());
                }
                i++;
            }
            if(!lost){
                while (theController.getHandSize(null) > 5) {
                    theController.discardTreasure(null);
                }
                theOutputs.printHand(theController.returnPlayerName(),theController.showAHand());
            }
        }
}    

