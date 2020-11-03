package cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import enums.TilesEnums;


public class DiscardPile {
	protected  Stack<Card>  Discarded;
	protected static DiscardPile theDiscardPile;
	
	public static DiscardPile getInstance(){
        if(theDiscardPile == null){
        	theDiscardPile = new DiscardPile();
        }
        return theDiscardPile;
    }
	
	protected void putbackall() {
		Collections.shuffle(Discarded);
		while(Discarded.size()>0) {
			FloodDeck.getInstance().addCard(Discarded.pop());
		}
	}
	
	public DiscardPile() {
		this.Discarded = new Stack<Card>();
	}
	
	public void discardCard(Card card) {
		Discarded.push(card);
	}
	public void shuffle() {
		Collections.shuffle(Discarded);
	}
}
