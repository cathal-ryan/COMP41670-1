package cards;
import java.util.Collections;
import java.util.Stack;


/**
 * Abstract deck class for decks of cards
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
abstract public class Deck {

	protected Stack<Card>  cardsInDeck; // Stack for all cards in the deck

	/** Checks if the deck has any cards
     * @return Boolean if deck empty
     */
	public boolean isEmpty() {
		return cardsInDeck.isEmpty();
	}

	/** Dealing card function to be implemented
     * @return Card depending on type of deck
     */
	abstract public Card dealCard(); //must implement a dealing cards function
	
	/** Adding card into deck
     */
	public void addCard(Card card) { 
		this.cardsInDeck.push(card);
	}
	
	/** Shuffle the deck
     */
	public void shuffle() {
		Collections.shuffle(cardsInDeck);
	}
}