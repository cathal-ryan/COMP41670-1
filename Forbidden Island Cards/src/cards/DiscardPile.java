package cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import enums.TilesEnums;


abstract public class DiscardPile {
	protected  Stack<Card>  Discarded;
	
	abstract public void putbackall();

	public void discardCard(Card card) {
		Discarded.push(card);
	}
	public void shuffle() {
		Collections.shuffle(Discarded);
	}
}
