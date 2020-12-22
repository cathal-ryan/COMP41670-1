package testing;

import org.junit.Test;

public class ObserversTest {
   
    
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

}
