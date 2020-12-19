package setup;

import board.Board;
import cards.Card;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
import cards.WaterRiseCard;
import enums.TilesEnums;
import player.Player;
import player.Team;

public class CardSetup {

	//===========================================================
	// Variable Setup
	//===========================================================
	private TreasureDeck setupTreasureDeck;
	private FloodDeck setupFloodDeck;
	private FloodDiscardPile setupFloodDiscard;
	private TreasureDiscardPile setupTreasureDiscard;
	private Team setupTeam;
	private SetupOutputs setupOuts;
	private Board theBoard;
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the CardSetup class
	 */
	public CardSetup() {
		this.setupTreasureDeck = TreasureDeck.getInstance();
		this.setupFloodDeck = FloodDeck.getInstance();
		this.setupFloodDiscard = FloodDiscardPile.getInstance();
		this.setupTreasureDiscard = TreasureDiscardPile.getInstance();
		this.setupTeam = Team.getInstance();
		setupOuts = new SetupOutputs();
		this.theBoard       = Board.getInstance();
	}

	public void dealCards() {
		int i=0;    // Iteration counter for cycling through Players        
        int numPlayers = setupTeam.getNumPlayers();
        while (i<numPlayers) {  
				Player playa1 =  setupTeam.getPlayer(i);
				boolean cardsDealt=false;
        		for(int j=0;j<2;j++) {
					cardsDealt=false;
					while(!cardsDealt){
						TreasureDeckCard c1 = (TreasureDeckCard) setupTreasureDeck.dealCard();
						if(c1 instanceof WaterRiseCard) {
							setupTreasureDeck.addCard(c1);
							setupTreasureDeck.shuffle();
						}
						else {
							setupTeam.getPlayer(i).drawTreasureCard(c1);
							cardsDealt=true;
						}
					}
				}
            i++;
		}
        for(int k=0;k<6;k++) {
			try{Thread.sleep(100);}
			catch(InterruptedException ex)
			{Thread.currentThread().interrupt();}
			Card c1 = setupFloodDeck.dealCard();
			String floodName = c1.getName().toString();
			setupOuts.flooded(floodName);
			setupFloodDiscard.addToPile(c1);
			theBoard.floodTile((TilesEnums)c1.getName());
		}
    }
}
	

