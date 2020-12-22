package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Hand;
import cards.TreasureCard;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import gameplay.control.Controller;
import gameplay.control.TreasureDraw;
import player.Player;

public class HandTest {

	@Test // Can the hand check if player has a tradeable hand
	public void testIfPlayerAllowedTrade() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		assertFalse("Can player trade with no tradable cards in hand", hand.canTrade());
	}

	@Test // Can hand get number of instances of a card in hand
	public void checkNumInstances() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		assertEquals("Number of cards in hand after adding 3", 3, hand.numofInstances(TypeEnums.EARTH));
	}

	@Test // Check can the game discard a card from hand
	public void checkDiscarding() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		TreasureDiscardPile theTreasureDiscardPile = TreasureDiscardPile.getInstance();
		hand.removeCard(0);

		assertTrue("Is Hand now empty after discard", hand.getCards().isEmpty());
		assertFalse("Does the Treasure Discard pile now have cards", theTreasureDiscardPile.Discarded.isEmpty());
	}
	@Test // Test if player can make hand choice correctly
	public void checkPlayerHandChoice(){
		Player play1 = new Player(0,"test",0);
		play1.addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		play1.addCardtoHand(new TreasureCard(TreasureCardEnums.CRYSTAL_OF_FIRE));
		play1.addCardtoHand(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		Controller theController = Controller.getInstance();
		System.out.println("Expected Output:\n [0] Crystal of Fire \n"
										  + "[1] Crystal of Fire  ");
		System.out.println("Expected Input: 0");
		int k = theController.chooseFromHand(play1, true);
		assertEquals("Is Hand now empty after discard", 0, k);
	}
}
