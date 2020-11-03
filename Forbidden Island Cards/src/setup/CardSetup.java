package setup;
import cards.Card;
import cards.Deck;
import cards.DiscardPile;
import cards.FloodCard;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDiscardPile;
import cards.WaterRiseCard;
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
        		for(int j=0;j<2;j++) {
        			Card c1 = TreasureDeck.getInstance().dealCard();    	
        			if(c1 instanceof WaterRiseCard) {
        				setupTreasureDeck.addCard(c1);
        				setupTreasureDeck.shuffle();
        			}
        			else {
        				setupPlayers.getPlayer(i).drawTreasureCard(c1);
        			}
        		}	
                i++;
        	}
        for(int k=0;k<3;k++) {
        	Card c1 = setupFloodDeck.dealCard();
        	setupFloodDiscard.discardCard(c1);
        }
        }
}
	

