package setup;

import cards.Card;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDeckCard;
import cards.TreasureDiscardPile;
import cards.WaterRiseCard;
import player.Player;
import player.PlayerList;

public class CardSetup {

	//===========================================================
	// Variable Setup
	//===========================================================
	private TreasureDeck setupTreasureDeck;
	private FloodDeck setupFloodDeck;
	private FloodDiscardPile setupFloodDiscard;
	private TreasureDiscardPile setupTreasureDiscard;
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the CardSetup class
	 */
	public CardSetup() {
		this.setupTreasureDeck = (TreasureDeck) TreasureDeck.getInstance();
		this.setupFloodDeck = (FloodDeck) FloodDeck.getInstance();
		this.setupFloodDiscard = FloodDiscardPile.getInstance();
		this.setupTreasureDiscard = TreasureDiscardPile.getInstance();
	}

	public void dealCards() {
		PlayerList setupPlayers = PlayerList.getInstance();
		int i=1;    // Iteration counter for cycling through Players        
        int numPlayers = setupPlayers.getNumPlayers();
        while (i<=numPlayers) {  
				Player playa1 =  setupPlayers.getPlayer(i);
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
							setupPlayers.getPlayer(i).drawTreasureCard(c1);
							cardsDealt=true;
						}
					}
				}
                i++;
			}
		PlayerList.getInstance().showAllHands();
		// Take out 6 flood cards
        for(int k=0;k<6;k++) {
			try{Thread.sleep(100);}
			catch(InterruptedException ex)
			{Thread.currentThread().interrupt();}

			Card c1 = setupFloodDeck.dealCard();
			System.out.println(c1.getName() +" has been flooded!");
			// could have a board.flood(c1.getName) here
        	setupFloodDiscard.addToPile(c1);
        }
        }
}
	

