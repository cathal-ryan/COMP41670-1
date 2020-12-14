package cards;

import java.util.Collections;
import java.util.Stack;

public class FloodDiscardPile extends DiscardPile{
	protected static FloodDiscardPile theFloodDiscardPile;

	public static FloodDiscardPile getInstance(){
        if(theFloodDiscardPile == null){
        	theFloodDiscardPile = new FloodDiscardPile();
        }
        return theFloodDiscardPile;
	}

	private FloodDiscardPile() {
		this.Discarded = new Stack<Card>();
	}
	
	public void putbackall() {
		if(Discarded.size()==0) {
			return;
		}
		Collections.shuffle(Discarded);
		while(Discarded.size()>0) {
			FloodDeck.getInstance().addCard(Discarded.pop());
		}
	}
	
	public void printAll() {
		for(int i=0;i<Discarded.size();i++) {
			System.out.println(Discarded.toString());
		}
	}
	


}
