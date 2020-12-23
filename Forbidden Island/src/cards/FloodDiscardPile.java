package cards;

import java.util.Collections;
import java.util.Stack;

/**
 * Class extending discardPile to create Flood Discard Pile
 * Implemented as Singleton, should only be 1 flood discard pile
 * @author Cathal Ryan and Conor Kneafsey
 */
public class FloodDiscardPile extends DiscardPile{

	private static FloodDiscardPile theFloodDiscardPile; // Singleton object

	/**
     * getInstance singleton method gets single instance
     * of FloodDiscardPile.
     * @return Singleton FloodDiscardPile object
     */
	public static FloodDiscardPile getInstance(){
        if(theFloodDiscardPile == null){
        	theFloodDiscardPile = new FloodDiscardPile();
        }
        return theFloodDiscardPile;
	}

	/**
     * Private constructor
     */
	private FloodDiscardPile() {
		this.Discarded = new Stack<Card>();
	}
	
	/**
	 * Puts flood cards from flood discard back to flood deck
     */
	public void putbackall() {
		if(Discarded.size()==0) { // Nothing to add back to deck
			return;
		}
		Collections.shuffle(Discarded);
		while(Discarded.size()>0) { // keep adding while cards remain
			FloodDeck.getInstance().addCard(Discarded.pop());
		}
	}

	/** Singleton destroyer for unit testing
     */
	public void destroyMe() {
		theFloodDiscardPile=null;
	}
}
