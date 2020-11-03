	package cards;
	import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
	import java.util.Random;
import java.util.Stack;

import enums.TilesEnums;

public class FloodDeck extends Deck{
		protected static FloodDeck theFloodDeck;

		public static FloodDeck getInstance(){
	        if(theFloodDeck == null){
	            theFloodDeck = new FloodDeck();
	        }
	        return theFloodDeck;
	    }
		
		public FloodDeck() {
		    // Prepare empty array of Cards
		    this.cardsInDeck = new Stack<Card>();
		    cardsInDeck.push(new FloodCard(TilesEnums.FOOLS_LANDING));
		    cardsInDeck.push(new FloodCard(TilesEnums.TEMPLE_OF_THE_SUN));
		    cardsInDeck.push(new FloodCard(TilesEnums.CORAL_PALACE));
		    cardsInDeck.push(new FloodCard(TilesEnums.TEMPLE_OF_THE_MOON));
		    cardsInDeck.push(new FloodCard(TilesEnums.OBSERVATORY));
		    cardsInDeck.push(new FloodCard(TilesEnums.BREAKERS_BRIDGE));
		    cardsInDeck.push(new FloodCard(TilesEnums.TIDAL_PALACE));
		    cardsInDeck.push(new FloodCard(TilesEnums.CAVE_OF_SHADOWS));
		    cardsInDeck.push(new FloodCard(TilesEnums.WHISPERING_GARDEN));
		    cardsInDeck.push(new FloodCard(TilesEnums.CAVE_OF_EMBERS));
		    cardsInDeck.push(new FloodCard(TilesEnums.PHANTOM_ROCK));
		    cardsInDeck.push(new FloodCard(TilesEnums.HOWLING_GARDEN));
		    cardsInDeck.push(new FloodCard(TilesEnums.SILVER_GATE));
		    cardsInDeck.push(new FloodCard(TilesEnums.IRON_GATE));
		    cardsInDeck.push(new FloodCard(TilesEnums.BRONZE_GATE));
		    cardsInDeck.push(new FloodCard(TilesEnums.MISTY_MARSH));
		    cardsInDeck.push(new FloodCard(TilesEnums.COPPER_GATE));
		    cardsInDeck.push(new FloodCard(TilesEnums.LOST_LAGOON));
		    cardsInDeck.push(new FloodCard(TilesEnums.CLIFFS_OF_ABANDON));
		    cardsInDeck.push(new FloodCard(TilesEnums.CRIMSON_FOREST));
		    cardsInDeck.push(new FloodCard(TilesEnums.DUNES_OF_DECEPTION));
		    cardsInDeck.push(new FloodCard(TilesEnums.GOLD_GATE));
		    cardsInDeck.push(new FloodCard(TilesEnums.TWILIGHT_HOLLOW));
		    cardsInDeck.push(new FloodCard(TilesEnums.WATCHTOWER));
		    shuffle();
		}
		public Card dealCard() {
			if(cardsInDeck.size()<1) {
				FloodDiscardPile.getInstance().putbackall();
				return cardsInDeck.pop();
			}
			else {
				return cardsInDeck.pop();
			}
		}
}
