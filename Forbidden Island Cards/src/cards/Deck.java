package cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import enums.TilesEnums;

abstract public class Deck {

	protected Stack<Card>  cardsInDeck;

	public boolean isEmpty() {
		return cardsInDeck.isEmpty();
	}
	
	public Card dealCard() {
		if(cardsInDeck.size()<1) {
			DiscardPile.getInstance().putbackall();
			return cardsInDeck.pop();
		}
		else {
			return cardsInDeck.pop();
		}
	}
	
	public void addCard(Card card) {
		this.cardsInDeck.push(card);
	}
	
	public void shuffle() {
		Collections.shuffle(cardsInDeck);
	}
}