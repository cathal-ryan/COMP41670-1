package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import cards.Card;
import cards.FloodDeck;
import cards.FloodDiscardPile;
import cards.TreasureDeck;
import cards.TreasureDiscardPile;

public class DeckTest {
    
    @Test
    public void checkDeckResets() {

        FloodDeck theFloodDeck = FloodDeck.getInstance();
        FloodDiscardPile theFloodDiscard = FloodDiscardPile.getInstance();
        
        for(int i=0;i<24;i++){
            Card c1 = theFloodDeck.dealCard();
            theFloodDiscard.addToPile(c1);
        }
        assertTrue("Empty status after 24 draws", theFloodDeck.isEmpty());
        Card c1 = theFloodDeck.dealCard();
        theFloodDiscard.addToPile(c1);

        assertFalse("Empty status after 1 more draw when deck empty", theFloodDeck.isEmpty());

    }

    @Test
    public void checkDiscardResets() {

        TreasureDeck theTreasureDeck = TreasureDeck.getInstance();
        TreasureDiscardPile theTreasureDiscard = TreasureDiscardPile.getInstance();
        
        for(int i=0;i<10;i++){
            Card c1 = theTreasureDeck.dealCard();
            theTreasureDiscard.addToPile(c1);
        }

        theTreasureDiscard.putbackall();

        assertTrue("Empty after putting all cards back", theTreasureDiscard.Discarded.isEmpty());

	}

}
