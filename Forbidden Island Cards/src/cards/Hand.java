package cards;

import java.util.ArrayList;
import java.util.List;

import enums.TreasureCardEnums;

public class Hand {

	List<TreasureDeckCard> handOfCards;
	
	public Hand() {
	    this.handOfCards = new ArrayList<TreasureDeckCard>();
	}

	public void addCard(TreasureDeckCard card){
		handOfCards.add(card);
	}

	public List<TreasureDeckCard> getCards(){
		return handOfCards;
	}
	
	public List<TreasureCardEnums> getNamesList(){
		List<TreasureCardEnums> s1 =new ArrayList<>();
		for(TreasureDeckCard c1:handOfCards){
			s1.add( (TreasureCardEnums) c1.getName());
		}
		return s1;
	}

	public String getHandasString(){
		String hand = "";
		if(handOfCards.size()==0){
			hand = ("Nada..."+" ¯\\_(ツ)_/¯");
		}
		for (int k = 0; k < handOfCards.size(); k++) {
			hand = hand + (handOfCards.get(k).getName());
			if(k==handOfCards.size()-1){
				hand = hand + (".\n");
			}
			else{
				hand = hand + (", ");
			}
		}
		return hand;
	}
	
	public void removeCard(int i){
		TreasureDiscardPile.getInstance().addToPile(handOfCards.get(i));
		handOfCards.remove(i);
	}
	
	public void discardforTreasure(TreasureCardEnums name){
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

	public boolean canTrade(){
		if(handOfCards.isEmpty()){
			return false;
		}
		for (TreasureDeckCard c1:handOfCards){
			if (!(c1 instanceof SandbagsCard || c1 instanceof HelicopterLift)){
				return true;
			}
		}
		return false;
	}

	public int getIndexOfCard(TreasureCardEnums name){
		List<TreasureCardEnums> l1 = getNamesList();
		for(int j=0;j<l1.size();j++){
			if(l1.get(j) == name){
				return j;
			}
		}
		return -1;
	}
	
	public boolean checkContains(TreasureCardEnums name){
		return (getNamesList().contains(name));
	}	
}



