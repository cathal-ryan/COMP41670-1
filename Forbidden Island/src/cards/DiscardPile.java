package cards;

import java.util.Collections;
import java.util.Stack;

/**
 * Abstract class for discard piles in game of Forbidden Island
 * Pile implemented as stack, can be shuffled and returned as a string, and can add cards to stack.
 * @author Cathal Ryan and Conor Kneafsey
 */
abstract public class DiscardPile {


	public  Stack<Card>  Discarded; // The discarded cards
	private String discardsAsString; // returns the card list as a string to be manipulated
	
	/**
     * Puts back all cards from the discard pile to the deck
     */
	abstract public void putbackall();

	/** Adds a card to the stack
	 * @param card Card to be added to the stack
	 */
	public void addToPile(Card card) {
		Discarded.push(card);
	}

	/** Shuffles the cards in the stack
	 */
	public void shuffle() {
		Collections.shuffle(Discarded);
	}

	/**
	 * Method to get pile as a string which can be manipulated, using recursion
	 */
	public void printPile() { 
		// If stack is empty then return 
		if (Discarded.isEmpty()) 
			return; 
		 
		Card card = Discarded.peek(); 
	 
		// Pop the top element of the stack 
		Discarded.pop(); 
	 
		// Recursively call the function PrintPile 
		printPile(); 
	 
		// Print the stack element starting 
		// from the bottom 
		discardsAsString = discardsAsString + (card.getName() + ", "); 
	 
		// Push the same element onto the stack 
		// to preserve the order 
		Discarded.push(card); 
	} 

	/**
	 * Returns the string pile from Print pile
	 */
	public String returnPrintedPile() {
		discardsAsString = "";
		printPile();
		return discardsAsString;
	}


}
