package cards;
import java.util.Stack;
import enums.TreasureCardEnums;

/**
 * Treasure Deck class for decks of treasure cards, inherits from Deck
 * Implemented as singleton as there should be only a single treasure deck
 * 
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class TreasureDeck extends Deck{
	protected static TreasureDeck theTreasureDeck; // singleton object

	/**
     * getInstance singleton method gets single instance
     * of the Treasure Deck.
     * @return Singleton FloodDeck object
     */
	public static TreasureDeck getInstance(){
		if(theTreasureDeck == null){
			theTreasureDeck = new TreasureDeck();
		}
		return theTreasureDeck;
	}
	
	/**
	 * Private constructor creates deck stack
	 */
	private TreasureDeck() {
		// Prepare empty array of Cards
		this.cardsInDeck = new Stack<Card>();
		// Add in all the cards and shuffle
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.EARTH_STONE));

		cardsInDeck.push(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));

		cardsInDeck.push(new TreasureCard(TreasureCardEnums.STATUE_OF_THE_WIND));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.STATUE_OF_THE_WIND));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.STATUE_OF_THE_WIND));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.STATUE_OF_THE_WIND));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.STATUE_OF_THE_WIND));

		cardsInDeck.push(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.OCEANS_CHALICE));
		
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.WATERS_RISE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.WATERS_RISE));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.WATERS_RISE));

		cardsInDeck.push(new TreasureCard(TreasureCardEnums.SANDBAGS));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.SANDBAGS));

		cardsInDeck.push(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		cardsInDeck.push(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));		    
		shuffle();
	}

	/** Returns a floodCard from the stack
	* @return TreasureCard object from the treasure Deck
	*/
	public Card dealCard() {
		if(cardsInDeck.size()<1) { // reset the deck if none left
			TreasureDiscardPile.getInstance().putbackall();
			return cardsInDeck.pop();
		}
		else {
			return cardsInDeck.pop();
		}
	}
}
