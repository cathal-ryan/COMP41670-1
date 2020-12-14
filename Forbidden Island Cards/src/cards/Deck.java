package cards;
import java.util.Collections;
import java.util.Stack;

abstract public class Deck {

	protected Stack<Card>  cardsInDeck;

	public boolean isEmpty() {
		return cardsInDeck.isEmpty();
	}
	
	abstract public Card dealCard();
	
	public void addCard(Card card) {
		this.cardsInDeck.push(card);
	}
	
	public void shuffle() {
		Collections.shuffle(cardsInDeck);
	}
}