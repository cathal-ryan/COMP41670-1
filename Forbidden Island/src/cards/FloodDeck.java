package cards;
import java.util.Stack;

import enums.TileStack;

/**
 * Flood Deck class for decks of flood cards, inherits from Deck
 * Implemented as singleton as there should be only a single deck
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class FloodDeck extends Deck{
	protected static FloodDeck theFloodDeck;

	
	public static FloodDeck getInstance(){
        if(theFloodDeck == null){
            theFloodDeck = new FloodDeck();
        }
        return theFloodDeck;
    }
	
	private FloodDeck() {
		TileStack names = new TileStack();

		// Prepare empty array of Cards
	    this.cardsInDeck = new Stack<Card>();
		while(names.size() >= 1) {
			cardsInDeck.push(new FloodCard(names.pop()));
		}
	}

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
