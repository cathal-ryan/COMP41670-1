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
        private Scanner inputScanner;
        private FloodDiscardPile theDiscardPile;
        private FloodDeck theFloodDeck;
        private boolean lost;
        private boolean won;
        private Team theTeam;
        private GameOutputs theOutputs;
        private GameInputs theInputs;   
        private Controller theController; 
    
        public FloodDraw() {
            theOutputs = new GameOutputs();
            theInputs = new GameInputs();    
            this.theController = Controller.getInstance();
            this.theDiscardPile = FloodDiscardPile.getInstance();
            this.theFloodDeck = FloodDeck.getInstance();
            this.lost = false;
            this.won = false;
            this.theTeam = Team.getInstance();
        }
    
        public void doFloodDraw() {
            theOutputs.floodDrawTime();
            int waterLevel = theController.getWaterLevel();
            for(int i =0;i<waterLevel;i++) {
                theController.enquirePlayers(false);       
                if(!lost){
                    theOutputs.cardsLeft(waterLevel-i);
                    theInputs.confirm(); //             
                    theController.dealFloodCard();
                }
            }    
            theOutputs.turnEndo();    
        }
}    

