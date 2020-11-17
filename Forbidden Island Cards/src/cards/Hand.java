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
	
	public List<String> getCardNamesAsStrings(){
		List<String> s1 =new ArrayList<>();
		for(Card c1:handOfCards){
			s1.add(c1.getName().toString());
		}
		return s1;
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

	public void discardforTreasure(Enum name){
		int k=0;
		for (int j=0;j<handOfCards.size();j++){
			Card c1 = handOfCards.get(j);
				if (c1.getName()==name){
					removeCard(j);
					k++;
					if(k>=3){
						return;
					}
				}
		}
	}
}



