package gameplay;

import java.util.Scanner;

import cards.Card;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
    import cards.WaterRiseCard;
    import player.Player;
    
    /**
     * PlayerTurn class manages all of the options a player can make whilst they
     * have a turn.
     * 
     * @author Conor Kennedy and Fergal Lonergan
     *
     */
    public class TreasureDraw {
    
        // ===========================================================
        // Setup Variables
        // ===========================================================
        private Player player;
        private Scanner inputScanner;
        private boolean lost;
        private TreasureDiscardPile thePile;
        private TreasureDeck theTreasureDeck;

    
        public TreasureDraw(Player thisPlayer, Scanner inputScanner) {
            this.player = thisPlayer;
            this.inputScanner = inputScanner;
            this.lost = false;
            this.thePile = TreasureDiscardPile.getInstance();
            this.theTreasureDeck= TreasureDeck.getInstance();
        }
    
        public void doTreasureDraw() {
            int i =0;
            while(!lost && (i<2)){
                TreasureDeckCard c1 = (TreasureDeckCard) theTreasureDeck.dealCard();
                if(c1 instanceof WaterRiseCard) {
                    WaterMeter.cardDrawn();
                    int waterLevel = WaterMeter.getWaterlevel();
                    if(waterLevel>5){
                        lost=true;
                    }
                    else{
                        System.out.println("Oh no! The Water Rises!! Flood Level currently at: "+ waterLevel);
                        thePile.addToPile(c1);
                    }
                }
                else {
                    player.getHand().addCard(c1);
                    System.out.println("Great! You've drawn "+ c1.getName());
                }
                i++;
            }
            if(!lost){
                while (player.handSize() > 5) {
                    player.discardTreasureCard(inputScanner);
                }
                System.out.println("Cool, so "+ player.getName()+ " now has the following hand: ");
                player.getHand().printHand();
            }
        }
		public boolean seeIfLost() {
			return lost;
		}
}    

