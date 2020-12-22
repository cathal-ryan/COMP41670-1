package cards;

import java.util.Collections;
import java.util.Stack;

/**
 * Class extending discardPile to create the Treasure Discard Pile
 * Implemented as Singleton, should only be 1 Treasure discard pile in game
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class TreasureDiscardPile extends DiscardPile{
	private static TreasureDiscardPile theTreasureDiscardPile; //Singleton

	/**
     * getInstance singleton method gets single instance
     * of TreasureDiscardPile.
     * @return Singleton FloodDiscardPile object
     */
	public static TreasureDiscardPile getInstance(){
        if(theTreasureDiscardPile == null){
        	theTreasureDiscardPile = new TreasureDiscardPile();
        }
        return theTreasureDiscardPile;
	}
	
	/**
     * Private constructor, gets new stack
     */
	private TreasureDiscardPile() {
		this.Discarded = new Stack<Card>();
	}
	
	/**
	 * Puts treasure cards from flood discard back to treasure deck
     */	
	public void putbackall() {
		Collections.shuffle(Discarded);
		while(Discarded.size()>0) {
			TreasureDeck.getInstance().addCard(Discarded.pop());
		}
	}

}
