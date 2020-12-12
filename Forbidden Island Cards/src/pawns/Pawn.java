package pawns;

import java.awt.Point;

import enums.TilesEnums;

abstract public class Pawn {

    protected Point position;

    protected Pawn() {
	}
    abstract public String getPlayerType();

    abstract public TilesEnums startLoc();

    public void setPos(Point p) {
        this.position = new Point(p);
    }

    public Point getPos() {
        return position;
    }
    
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
