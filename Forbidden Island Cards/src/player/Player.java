package player;
import java.util.List;
import gameplay.WaterMeter;

import cards.Card;
import cards.DiscardPile;
import cards.Hand;
import cards.TreasureDeck;
import cards.WaterRiseCard;


abstract public class Player {

	//===========================================================
	// Variable Setup
	//===========================================================
	private String        playerName;
	private Hand          playerHand;
	private boolean       canDoTurn;

	public Player(int playerNum, String playerName) {
		this.playerName     = playerName;
		this.playerHand     = new Hand();
		this.canDoTurn		=true;
	}
	
	public String getName() {
		return playerName;
	}

	public List<Card> showHand() {
		return playerHand.getCards();
	}
	
	public Hand getHand() {
		return this.playerHand;
	}
	
	public int handSize() {
		return this.playerHand.getCards().size();
	}

	public boolean canHaveTurn(){
		return canDoTurn;
	}

	public void drawTreasureCard(Card card1) {
		if(card1 instanceof WaterRiseCard) {
			WaterMeter.setWatermeter();
			DiscardPile.getInstance().discardCard(card1);
		}
		this.playerHand.addCard(card1);
	}
	
	public void drawStartingHand() {
		for(int j=0;j<2;j++) {
			Card c1 = TreasureDeck.getInstance().dealCard();    	
			if(c1 instanceof WaterRiseCard) {
				TreasureDeck.getInstance().addCard(c1);
				TreasureDeck.getInstance().shuffle();
			}
			else {
				drawTreasureCard(c1);
			}
		}	
	}
	
	abstract public String getPlayerType();
	
}
