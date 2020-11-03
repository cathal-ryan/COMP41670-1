package cards;

abstract public class Card {
	@SuppressWarnings("rawtypes")
    private Enum name;   // Name of object referred to by card,  e.g. "Mrs. Peacock"

	Card(@SuppressWarnings("rawtypes") Enum name){
		this.name   = name;
	}
	
	@SuppressWarnings("rawtypes")
    public Enum getName() {
		return this.name;
	}
}
