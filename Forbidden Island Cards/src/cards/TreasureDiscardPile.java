package cards;

import java.util.Collections;
import java.util.Stack;

public class TreasureDiscardPile extends DiscardPile{
	protected static TreasureDiscardPile theTreasureDiscardPile;

	
	public static TreasureDiscardPile getInstance(){
        if(theTreasureDiscardPile == null){
        	theTreasureDiscardPile = new TreasureDiscardPile();
        }
        return theTreasureDiscardPile;
    }
	
	public void putbackall() {
		Collections.shuffle(Discarded);
		while(Discarded.size()>0) {
			TreasureDeck.getInstance().addCard(Discarded.pop());
		}
	}
	
	public TreasureDiscardPile() {
		this.Discarded = new Stack<Card>();
	}
}
