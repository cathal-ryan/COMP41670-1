package gameplay;

    import java.util.Scanner;
    
    import cards.Card;
    import cards.TreasureDeck;
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
    
        public TreasureDraw(Player thisPlayer, Scanner inputScanner) {
            this.player = thisPlayer;
            this.inputScanner = inputScanner;
            this.lost = false;
        }
    
        public void doTreasureDraw() {
            int i =0;
            while(!lost && (i<2)){
                Card c1 = TreasureDeck.getInstance().dealCard();
                if(c1 instanceof WaterRiseCard) {
                    WaterMeter.cardDrawn();
                    int waterLevel = WaterMeter.getWaterlevel();
                    if(waterLevel>5){
                        lost=true;
                    }
                    else{
                        System.out.println("Oh no! The Water Rises!! Flood Level currently at: "+ waterLevel);
                        TreasureDiscardPile.getInstance().discardCard(c1);
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
                    discard();
                }
                System.out.println("Cool, so "+ player.getName()+ " now has the following hand: ");
                player.getHand().printHand();
            }
        }

        public void discard() {
            int userIn = 0;
            boolean validIn = false;
            System.out.println("Which of the following cards would you like to discard?");
            for (int i = 0; i < player.showHand().size(); i++) {
                System.out.println("[" + i + "]: " + player.showHand().get(i).getName());
            }
            while (!validIn) {
                String userString = inputScanner.nextLine();
                try {
                    userIn = Integer.parseInt(userString);
                } catch (NumberFormatException e) {
                    continue;
                }
                if ((userIn >= 0) && (userIn <= player.showHand().size())) {
                    validIn = true;
                }
            }
            player.getHand().removeCard(userIn);
        }

		public boolean seeIfLost() {
			return lost;
		}
    

}    

