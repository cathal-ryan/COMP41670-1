package cards;
/**
 * Abstract class for cards in game
 * @author Cathal Ryan and Conor Kneafsey
 */
abstract public class Card {
	

	// Card has name which can be of type enum
	@SuppressWarnings("rawtypes")
    private Enum name;   // Name of card

	/** Constructor requires a name
     */
	Card(@SuppressWarnings("rawtypes") Enum name){
		this.name   = name;
	}
	
	/** Gets card name
     * @return Enum name of Card
     */
	@SuppressWarnings("rawtypes")
    public Enum getName() {
		return this.name;
	}
}
