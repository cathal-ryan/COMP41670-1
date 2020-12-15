package cards;

abstract public class Card {
	@SuppressWarnings("rawtypes")
    private Enum name;   // Name of card

	Card(@SuppressWarnings("rawtypes") Enum name){
		this.name   = name;
	}
	
	@SuppressWarnings("rawtypes")
    public Enum getName() {
		return this.name;
	}
}
