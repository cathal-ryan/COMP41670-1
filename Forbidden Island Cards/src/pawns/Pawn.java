package pawns;

abstract public class Pawn {

    private int[] location;

    protected Pawn() {
	}
    abstract public String getPlayerType();
    
    public void shoreUp() {
        System.out.println("Some day I'll get around to shoring up");

    }

    public void move() {
        System.out.println("Some day I'll get around to moving..");

    }

    public void helicopterMove() {
        System.out.println("Some day I'll get around to flying..");

    }

}
