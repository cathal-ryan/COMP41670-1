package setup;
import cards.Deck;
import cards.DiscardPile;
import cards.FloodDeck;
import player.PlayerList;

public class CardSetup {

	//===========================================================
	// Variable Setup
	//===========================================================
	private FloodDeck setupDeck;
	private DiscardPile setupDiscard;
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the CardSetup class
	 */
	public CardSetup() {
		this.setupDeck = (FloodDeck) FloodDeck.getInstance();
		this.setupDiscard = DiscardPile.getInstance();
	}

	public void dealCards() {
		PlayerList setupPlayers = PlayerList.getInstance();
		
		// Give everyone 2 cards to start with
		int i=1;    // Iteration counter for cycling through Players        
        int numPlayers = setupPlayers.getNumPlayers();
        while (i<=numPlayers) {   
        	
            i++;
        }
  	}
	
}
