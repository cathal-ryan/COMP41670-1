	package cards;
	import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
	import java.util.Random;
import java.util.Stack;

import enums.TilesEnums;
import enums.TreasureCardEnums;

public class TreasureDeck extends Deck{
		protected static TreasureDeck theTreasureDeck;

		public static TreasureDeck getInstance(){
	        if(theTreasureDeck == null){
	            theTreasureDeck = new TreasureDeck();
	        }
	        return theTreasureDeck;
	    }
		
		public TreasureDeck() {
		    // Prepare empty array of Cards
		    this.cardsInDeck = new Stack<Card>();
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
		    
		    cardsInDeck.push(new WaterRiseCard(TreasureCardEnums.WATERS_RISE));
		    cardsInDeck.push(new WaterRiseCard(TreasureCardEnums.WATERS_RISE));
		    cardsInDeck.push(new WaterRiseCard(TreasureCardEnums.WATERS_RISE));

		    cardsInDeck.push(new SandbagsCard(TreasureCardEnums.SANDBAGS));
		    cardsInDeck.push(new SandbagsCard(TreasureCardEnums.SANDBAGS));

		    cardsInDeck.push(new HelicopterLift(TreasureCardEnums.HELICOPTER_LIFT));
		    cardsInDeck.push(new HelicopterLift(TreasureCardEnums.HELICOPTER_LIFT));
		    cardsInDeck.push(new HelicopterLift(TreasureCardEnums.HELICOPTER_LIFT));
		    
		    shuffle();
		}
		public Card dealCard() {
			if(cardsInDeck.size()<1) {
				TreasureDiscardPile.getInstance().putbackall();
				return cardsInDeck.pop();
			}
			else {
				return cardsInDeck.pop();
			}
		}
}
