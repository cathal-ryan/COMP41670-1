package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Hand;

public class HandTest {

	@Test
	public void testConstruct() {
		Hand hand = new Hand();
		assertEquals("Hand Does Not Construct", 0, hand.getCards().size());
	}

}
