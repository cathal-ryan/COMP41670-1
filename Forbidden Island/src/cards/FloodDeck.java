package cards;
import java.util.Stack;

import board.TileStack;

/**
 * Flood Deck class for decks of flood cards, inherits from Deck
 * Implemented as singleton as there should be only a single deck
 * @author Cathal Ryan and Conor Kneafsey
 */
public class FloodDeck extends Deck{
	protected static FloodDeck theFloodDeck;

    /**
     * getInstance singleton method gets single instance
     * of the Flood Deck.
     * @return Singleton FloodDeck object
     */
	public static FloodDeck getInstance(){
        if(theFloodDeck == null){
            theFloodDeck = new FloodDeck();
        }
        return theFloodDeck;
	}
	
	/** Constructor private for singleton
     */
	private FloodDeck() {
		TileStack names = new TileStack(); // Tile stack will handle getting names of all cards, same as tiles 

		// Prepare empty array of Cards
	    this.cardsInDeck = new Stack<Card>();
		while(names.size() >= 1) { // add all tiles to the stack of cards
			cardsInDeck.push(new FloodCard(names.pop()));
		}
	}

	/** Returns a floodCard from the stack
	 * @return FloodCard object from the floodDeck
     */
	public Card dealCard() {
		if(cardsInDeck.size()<1) {
			FloodDiscardPile.getInstance().putbackall();
			return cardsInDeck.pop();
		}
		else {
			return cardsInDeck.pop();
		}
	}
}
