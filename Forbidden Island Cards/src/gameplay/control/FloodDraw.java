package gameplay.control;

import java.util.Scanner;

import cards.Card;

import cards.FloodDeck;
import cards.FloodDiscardPile;

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
    
        public FloodDraw(Scanner inputScanner) {
            this.inputScanner = inputScanner;
            this.theDiscardPile = FloodDiscardPile.getInstance();
            this.theFloodDeck = FloodDeck.getInstance();
            this.lost = false;
            this.won = false;
            this.theTeam = Team.getInstance();
        }
    
        public void doFloodDraw() {
            System.out.println("\nBrace yourselves! It's now time to draw flood cards.");
            for(int i =0;i<WaterMeter.getWaterlevel();i++) {
                if(theTeam.enquirePlayers(inputScanner, false)){
                    won=true;
                    return;
                }                
                if(!lost){
                    System.out.println(WaterMeter.getWaterlevel()-i+" card(s) to go! Press [return] to draw!");
                    @SuppressWarnings("unused")
                    String playerStartsTurn = inputScanner.nextLine(); // Make player press return to confirm turn start            
                    Card card1 = theFloodDeck.dealCard();
                    theDiscardPile.addToPile(card1);
                    // Take a flood card and add it to discard pile
                    System.out.println("Oh no, "+ card1.getName() + " has been flooded");
                    floodTile(card1.getName()); // Flood the tile with this name.
                }
            }        
        }

		private void floodTile(Enum name) {
            // this is a placeholder method. This method will be invoked to flood a tile.
            // if tile sinks, whatever player is on it should have to swim to nearby tile. If they cant, then seeIfLost should be able to return true.
            // also if fools landing is the one which sinks, player should lose.
        }
        public boolean seeIfLost() {
			return lost;
        }
        public boolean seeIfWon() {
			return won;
		}
}    

