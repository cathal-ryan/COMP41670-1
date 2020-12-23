package setup;

import board.Board;
import cards.Card;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureCard;
import cards.TreasureDiscardPile;
import enums.TilesEnums;
import enums.TreasureCardEnums;
import player.Team;

/**
 * Class to handle all setting up of cards/hands for a game of Forbidden Island
 * Deals 2 treasure cards to each player
 * Deals 6 flood cards to be flooded.
 * Methods are protected, they shouldn't be used outside of setup.
 * 
 * @author  Cathal Ryan and Conor Kneafsey
 */
public class CardSetup {

	// Variable Setup
	private TreasureDeck setupTreasureDeck;
	private FloodDeck setupFloodDeck;
	private FloodDiscardPile setupFloodDiscard;
    @SuppressWarnings("unused")
	private TreasureDiscardPile setupTreasureDiscard;
	private Team setupTeam;
	private SetupOutputs setupOuts;
	private Board theBoard;

	/**
	 * Constructor for the CardSetup class
	 */
	protected CardSetup() {
		this.setupTreasureDeck = TreasureDeck.getInstance();
		this.setupFloodDeck = FloodDeck.getInstance();
		this.setupFloodDiscard = FloodDiscardPile.getInstance();
		this.setupTreasureDiscard = TreasureDiscardPile.getInstance();
		this.setupTeam = Team.getInstance();
		setupOuts = new SetupOutputs();
		this.theBoard       = Board.getInstance();
	}

	/**
	 * Deal all the necessary cards to players to begin the game
	 */
	protected void dealCards() {
		int i=0;    // Iteration counter for cycling through Players        
		int numPlayers = setupTeam.getNumPlayers();
		//For each player in the team ,give them 2 cards
        while (i<numPlayers) {  
				boolean cardsDealt=false;
        		for(int j=0;j<2;j++) {
					cardsDealt=false;
					while(!cardsDealt){
						TreasureCard c1 = (TreasureCard) setupTreasureDeck.dealCard();
						// If its waters rise, return and shuffle
						if((TreasureCardEnums) c1.getName() == TreasureCardEnums.WATERS_RISE) {
							setupTreasureDeck.addCard(c1);
							setupTreasureDeck.shuffle();
						}
						else {
							setupTeam.getPlayer(i).addCardtoHand(c1);
							cardsDealt=true;
						}
					}
				}
            i++;
		}
		// Flood 6 tiles
        for(int k=0;k<6;k++) {
			try{Thread.sleep(100);} //Do a little sleep here just so the floodings come in one by one
			catch(InterruptedException ex)
			{Thread.currentThread().interrupt();}

			// Deal a card and flood each corresponding tile
			Card c1 = setupFloodDeck.dealCard();
			String floodName = c1.getName().toString();
			setupOuts.flooded(floodName);
			setupFloodDiscard.addToPile(c1);
			theBoard.floodTile((TilesEnums)c1.getName());
		}
    }
}
	

