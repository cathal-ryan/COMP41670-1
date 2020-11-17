package player;

import java.util.List;
import gameplay.WaterMeter;
import pawns.*;
import cards.Card;
import cards.DiscardPile;
import cards.Hand;
import cards.TreasureDeck;
import cards.WaterRiseCard;

public class Player {
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private String playerName;
	private Hand playerHand;
	private Pawn playerPawn;

	public Player(int playerNum, String playerName, int adventurerNum) {
		this.playerName = playerName;
		this.playerHand = new Hand();
		switch (adventurerNum) {
			case 0:
				playerPawn = new Diver();
				break;
			case 1:
				playerPawn = new Engineer();
				break;
			case 2:
				playerPawn = new Explorer();
				break;
			case 3:
				playerPawn = new Navigator();
				break;
			case 4:
				playerPawn = new Pilot();
				break;
			case 5:
				playerPawn = new Messenger();
				break;
			default:
				System.out.println("I shouldn't be here!");
		}
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

	public void drawTreasureCard(Card card1) {
		this.playerHand.addCard(card1);
	}
	
	public String getPlayerType(){
		return playerPawn.getPlayerType();
	};
	
}
