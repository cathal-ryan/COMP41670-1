	package cards;
	import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
	import java.util.Random;
import java.util.Stack;

import enums.TilesEnums;
import enums.tileStack;

public class FloodDeck extends Deck{
	protected static FloodDeck theFloodDeck;

	public static FloodDeck getInstance(){
        if(theFloodDeck == null){
            theFloodDeck = new FloodDeck();
        }
        return theFloodDeck;
    }
	
	public FloodDeck() {
		tileStack names = new tileStack();

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
