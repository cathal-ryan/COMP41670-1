package gameplay.control;

import java.util.Scanner;

import cards.Card;

import cards.FloodDeck;
import cards.FloodDiscardPile;
import gameplay.view.GameInputs;
import gameplay.view.GameOutputs;
import player.Player;
import player.Team;
    
    /**
     * PlayerTurn class manages all of the options a player can make whilst they
     * have a turn.
     * 
     * @author Cathal Ryan and Conor Kneafsey
     *
     */
    public class FloodDraw {
    
        // ===========================================================
        // Setup Variables
        // ===========================================================
        private GameOutputs theOutputs;
        private GameInputs theInputs;   
        private Controller theController; 
    
        public FloodDraw() {
            theOutputs = new GameOutputs();
            theInputs = new GameInputs();    
            this.theController = Controller.getInstance();
        }
    
        public void doFloodDraw() {
            theOutputs.floodDrawTime();
            int waterLevel = theController.getWaterLevel();
            for(int i =0;i<waterLevel;i++) {
                if(theController.enquirePlayers(false)){
                    return;       //game is won
                }
                theOutputs.cardsLeft(waterLevel-i);
                theInputs.confirm(); //             
                theController.dealFloodCard();
                if(theController.isGameOver()){
                    return; //Game is lost
                }
            }    
            theOutputs.turnEndo();    
        }
}    

