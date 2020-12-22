package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import enums.TreasureCardEnums;
import enums.TypeEnums;

/**
 * Class defining a Hand of Cards in a game of Forbidden Island
 * Can initialise a hand, add cards to hand and return
 * the cards in a hand.
 * It can return a list of the names of cards in the hand,
 * It can return a string representing the hand which can be manipulated.
 * Provides checks player has cards that can be given, and can discard for claiming treasures.
 * 
 * @author  Conor Kneafsey and Cathal Ryan
 *
 */
public class Hand {

	List<TreasureCard> handOfCards; // the hand
	
	/**
	 * Constructor creates ArrayList of the cards
	 */
	public Hand() {
	    this.handOfCards = new ArrayList<>();
	}

	/** Allows cards to be added to the hand
	 *@param card - the TreasureCard to be added
	 */
	public void addCard(TreasureCard card){
		handOfCards.add(card);
	}

	/** Gets the hand
	 *@return handofCards - the hand arraylist
	 */
	public List<TreasureCard> getCards(){
		return handOfCards;
	}
	
	/** Gets the name of all cards in hand
	 *@return names - list of names of cards in the hand
	 */
	public List<TreasureCardEnums> getNamesList(){
		List<TreasureCardEnums> names =new ArrayList<>();
		for(TreasureCard c1:handOfCards){
			names.add( (TreasureCardEnums) c1.getName());
		}
		return names;
	}

	/** Gets all the cards in the hand as one single string, to be manipulated elsewhere
	 *@return hand - string of the hand
	 */
	public String getHandasString(){
		String hand = "";
		if(handOfCards.isEmpty()){
			hand = (" ");
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
	
	/** Allows for 4 cards in the hand to be discarded for capturing a treasure
	 * @param typename - the type of treasure being captured
	 */
	public void discardforTreasure(TypeEnums typename){
		TreasureCardEnums name = convertTypetoTreasure(typename); // Convert the type name into a card name
		int k=0;
		Iterator<TreasureCard> it = handOfCards.iterator(); // Declare iterator to remove cards without breaking loop
		while(it.hasNext()){
			TreasureCard TreaCard = it.next();
			if (TreaCard.getName()==name){
				it.remove();
				k++;
				if(k>3){ // break when we've gotten rid of 4 cards
					break;
				}
			}
		}
	}

	/** Discards of a card in a hand and puts it in discard pile
	 * @param i index of card to be removed
	 */
	public void removeCard(int i){
		TreasureDiscardPile.getInstance().addToPile(handOfCards.get(i));
		handOfCards.remove(i);
	}

	/** Checks if a player is able to give cards, if their hand contains a card that isn't sandbags or helicopter lift
	 * @return boolean whether the player can trade or not.
	 */
	public boolean canTrade(){
		if(handOfCards.isEmpty()){ //check if has cards
			return false;
		}
		for (TreasureCard c1:handOfCards){
			if (!(c1.getName() == TreasureCardEnums.SANDBAGS || c1.getName() == TreasureCardEnums.HELICOPTER_LIFT)){
				return true; // if they have cards that aren't sandbags or heli lift
			}
		}
		return false;
	}

	/** Find where a card is in the hand based on its name.
	 * @param name The name of the card you want the index of
	 * @return index of the card with the name
	 */
	public int getIndexOfCard(TreasureCardEnums name){
		List<TreasureCardEnums> l1 = getNamesList();
		for(int j=0;j<l1.size();j++){
			if(l1.get(j) == name){
				return j;
			}
		}
		return -1; // if they dont have the card just return -1
	}

	/** Find how many times a type of card appears in the hand, primarily
	 * to check whether they can capture treasure, hence typename is passed in
	 * @param typename The type of card we want to count
	 * @return integer number of times this card appears in hand
	 */
	public int numofInstances(TypeEnums typename){
		TreasureCardEnums name = convertTypetoTreasure(typename); //convert from type to a name we can use
		return Collections.frequency(getNamesList(), name); //count appearances
	}
	
	/** Check if hand has a particular card in it
	 * @param name Name of card we want to count
	 */
	public boolean checkContains(TreasureCardEnums name){
		return (getNamesList().contains(name));
	}	

	/** Private function for dealing with converting a type to treasure
	 * Treasure ususally deals with a typeEnum, and so we want to convert
	 * it to a name that makes sense in the hand.
	 * @param typename Type of the card we want to convert a name to.
	 */
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



