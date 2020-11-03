package cards;


import java.util.ArrayList;
import java.util.List;

public class Hand {

	List<Card> handOfCards;
	
	public Hand() {
	    this.handOfCards = new ArrayList<Card>();
	}

	public void addCard(Card card){
		handOfCards.add(card);
	}
	
	public List<Card> getCards(){
		return handOfCards;
	}
	
	public void removeCard(int i){
		DiscardPile.getInstance().discardCard(handOfCards.get(i));
		handOfCards.remove(i);
	}

}
