package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;
import gameplay.model.TreasureHandler;

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
        System.out.println("\nSome day I'll get around to shoring up");
        System.out.println(getPos());
    }

    public void move() {
        Point p = Board.getInstance().getTilePos(TilesEnums.FOOLS_LANDING);
        TreasureHandler.getInstance().setTreasureCapture(TypeEnums.FIRE);
        TreasureHandler.getInstance().setTreasureCapture(TypeEnums.WATER);
        TreasureHandler.getInstance().setTreasureCapture(TypeEnums.WIND);
        TreasureHandler.getInstance().setTreasureCapture(TypeEnums.EARTH);

        setPos(p);

        System.out.println("\nSome day I'll get around to moving..");
        System.out.println(getPos());
        System.out.println(Board.getInstance().getTileType(getPos()));
    }

    public void helicopterMove(int k) {
        System.out.println("\nSome day I'll get around to flying.."+k);
        System.out.println(getPos());

    }

}
