package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Hand;
import cards.TreasureCard;
import cards.TreasureDiscardPile;
import enums.TreasureCardEnums;
import enums.TypeEnums;
import gameplay.control.TreasureDraw;

public class HandTest {

	@Test
	public void testIfPlayerAllowedTrade() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		assertFalse("Can player trade with no tradable cards in hand", hand.canTrade());
	}

	@Test
	public void checkNumInstances() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		assertEquals("Number of cards in hand after adding 3", 3, hand.numofInstances(TypeEnums.EARTH));
	}

	@Test
	public void checkDiscarding() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		TreasureDiscardPile theTreasureDiscardPile = TreasureDiscardPile.getInstance();
		hand.removeCard(0);

		assertTrue("Is Hand now empty after discard", hand.getCards().isEmpty());
		assertFalse("Does the Treasure Discard pile now have cards", theTreasureDiscardPile.Discarded.isEmpty());
	}
}
