package gameplay;

import java.util.Scanner;

import cards.Card;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
    import cards.WaterRiseCard;
    import player.Player;
import player.Team;
    
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
        private boolean won;
        private TreasureDiscardPile thePile;
        private TreasureDeck theTreasureDeck;
        private Team theTeam;

    
        public TreasureDraw(Player thisPlayer, Scanner inputScanner) {
            this.player = thisPlayer;
            this.inputScanner = inputScanner;
            this.lost = false;
            this.won = false;
            this.thePile = TreasureDiscardPile.getInstance();
            this.theTreasureDeck= TreasureDeck.getInstance();
            this.theTeam = Team.getInstance();
        }
    
        public void doTreasureDraw() {
            int i =0;
            System.out.println("Now it's time to draw your treasure cards!");
            while(!lost && (i<2)){
                if(theTeam.enquirePlayers(inputScanner, false)){
                    won=true;
                    return;
                }
                System.out.println(2-i+" cards to go! Press [return] to draw!");
                @SuppressWarnings("unused")
                String playerStartsTurn = inputScanner.nextLine(); // Make player press return to confirm turn start        
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
                System.out.println("\nCool, so "+ player.getName()+ " now has the following hand: ");
                player.getHand().printHand();
            }
        }
		public boolean seeIfLost() {
			return lost;
        }
        public boolean seeIfWon() {
			return won;
		}
}    

