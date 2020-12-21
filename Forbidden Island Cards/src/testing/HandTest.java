package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Hand;
import cards.TreasureCard;
import enums.TreasureCardEnums;
import enums.TypeEnums;

public class HandTest {

	@Test
	public void testIfPlayerAllowedTrade() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.HELICOPTER_LIFT));
		assertFalse("Hand is not checked properly for lack of trading cards", hand.canTrade());
	}

	@Test
	public void checkNumInstances() {
		Hand hand = new Hand();
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		hand.addCard(new TreasureCard(TreasureCardEnums.EARTH_STONE));
		assertEquals("Hand is not counting cards correctly", 3, hand.numofInstances(TypeEnums.EARTH));
	}

}
