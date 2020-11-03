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
    
        public FloodDraw(Player thisPlayer, Scanner inputScanner) {
            this.player = thisPlayer;
            this.inputScanner = inputScanner;
        }
    
        public void doFloodDraw() {
            for(int i =0;i<=WaterMeter.getWaterlevel();i++) {
                FloodDiscardPile.getInstance().discardCard((FloodDeck.getInstance().dealCard()));
            }        
        }
}    

