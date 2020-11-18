package gameplay;


    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    import cards.Card;
    import cards.Deck;
    import cards.DiscardPile;
    import cards.FloodDeck;
    import cards.FloodDiscardPile;
    import cards.TreasureDeck;
    import cards.TreasureDiscardPile;
    import cards.WaterRiseCard;
    import enums.TilesEnums;
    import player.Player;
    
    /**
     * PlayerTurn class manages all of the options a player can make whilst they
     * have a turn.
     * 
     * @author Conor Kennedy and Fergal Lonergan
     *
     */
    public class FloodDraw {
    
        // ===========================================================
        // Setup Variables
        // ===========================================================
        private Player player;
        private Scanner inputScanner;
        private FloodDiscardPile theDiscardPile;
        private FloodDeck theFloodDeck;
        private boolean lost;
    
        public FloodDraw(Player thisPlayer, Scanner inputScanner) {
            this.player = thisPlayer;
            this.inputScanner = inputScanner;
            this.theDiscardPile = FloodDiscardPile.getInstance();
            this.theFloodDeck = FloodDeck.getInstance();
            this.lost = false;
        }
    
        public void doFloodDraw() {
            for(int i =0;i<=WaterMeter.getWaterlevel();i++) {
                if(!lost){
                    Card card1 = theFloodDeck.dealCard();
                    theDiscardPile.addToPile(card1);
                    // Take a flood card and add it to discard pile
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
			return false;
		}
}    

