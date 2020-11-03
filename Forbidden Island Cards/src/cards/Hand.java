package cards;


import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

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

	public void printHand(){
		for (int k = 0; k < handOfCards.size(); k++) {
			System.out.print(handOfCards.get(k).getName());
			if(k==handOfCards.size()-1){
				System.out.print(".\n");
			}
			else{
				System.out.print(", ");
			}
		}
	}
	
	public void removeCard(int i){
		TreasureDiscardPile.getInstance().discardCard(handOfCards.get(i));
		handOfCards.remove(i);
	}

}
