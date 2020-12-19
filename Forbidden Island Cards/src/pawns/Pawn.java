package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

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

    public boolean move(char dir) {
        Board theBoard = Board.getInstance();
        Point p;
        int movePosX = (int) position.getX();
        int movePosY = (int) position.getY();
        switch(dir) {
            case 'w':
                movePosY++;
                if(movePosY > 5)
                    return false;
                p = new Point(movePosX,movePosY);
                if(theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 'a':
                movePosX--;
                if(movePosX < 0)
                    return false;
                p = new Point(movePosX,movePosX);
                if(theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 's':
                movePosY--;
                if(movePosY < 0)
                    return false;
                p = new Point(movePosX,movePosY);
                if(theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 'd':
                movePosX++;
                if(movePosX > 5)
                    return false;
                p = new Point(movePosX,movePosY);
                if(theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            default:
                return false;
        }
    }

    public void helicopterMove(int k) {
        System.out.println("\nSome day I'll get around to flying.."+k);
        System.out.println(getPos());

    }

}