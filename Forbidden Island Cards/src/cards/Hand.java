package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.TreasureCardEnums;
import enums.TypeEnums;

public class Hand {

	List<TreasureCard> handOfCards;
	
	public Hand() {
	    this.handOfCards = new ArrayList<TreasureCard>();
	}

	public void addCard(TreasureCard card){
		handOfCards.add(card);
	}

	public List<TreasureCard> getCards(){
		return handOfCards;
	}
	
	public List<TreasureCardEnums> getNamesList(){
		List<TreasureCardEnums> s1 =new ArrayList<>();
		for(TreasureCard c1:handOfCards){
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
	
	public void discardforTreasure(TypeEnums typename){
		TreasureCardEnums name = convertTypetoTreasure(typename);
		int k=0;
		List<Integer> forRemoval = new ArrayList();
		for (int j=0;j<handOfCards.size();j++){
			Card c1 = handOfCards.get(j);
				if (c1.getName()==name){
					forRemoval.add(j);
					k++;
					if(k>=4){
						break;
					}
				}
		}
		for(k=0;k<forRemoval.size();k++){
			removeCard(forRemoval.get(k));
		}
	}

	public boolean canTrade(){
		if(handOfCards.isEmpty()){
			return false;
		}
		for (TreasureCard c1:handOfCards){
			if (!(c1.getName() == TreasureCardEnums.SANDBAGS || c1.getName() == TreasureCardEnums.HELICOPTER_LIFT)){
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

	public int numofInstances(TypeEnums typename){
		TreasureCardEnums name = convertTypetoTreasure(typename);
		return Collections.frequency(getNamesList(), name);
	}
	
	public boolean checkContains(TreasureCardEnums name){
		return (getNamesList().contains(name));
	}	

	private TreasureCardEnums convertTypetoTreasure(TypeEnums typename){
		TreasureCardEnums name;
		switch (typename) {
			case FIRE:
				name=TreasureCardEnums.CRYSTAL_OF_FIRE;
				break;
			case WATER:
				name=TreasureCardEnums.OCEANS_CHALICE;
				break;
			case WIND:
				name=TreasureCardEnums.STATUE_OF_THE_WIND;
				break;
			case EARTH:
				name=TreasureCardEnums.EARTH_STONE;
                break;
            default:
				name = TreasureCardEnums.CRYSTAL_OF_FIRE;
		}
		return name;
	}
}



