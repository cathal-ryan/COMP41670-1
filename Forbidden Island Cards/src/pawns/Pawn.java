package pawns;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

abstract public class Pawn {

    protected Point position;
    protected Board theBoard;
    protected List<Point> viableSwim = new ArrayList<Point>();
    protected int movePosX;
    protected int movePosY;

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

    public boolean canShoreUp(Point p) {
        theBoard = Board.getInstance();
        movePosX = (int) position.getX();
        movePosY = (int) position.getY();
        for(int y=movePosY-1;y<movePosY+2;y++) {
            Point check = new Point(movePosX,y);
            if(check.equals(p) && theBoard.isTileFlooded(p) && !theBoard.isTileSunk(p))
                return true;
        }
        for(int x=movePosX-1;x<movePosX+2;x++) {
            Point check = new Point(x,movePosY);
            if(check.equals(p) && theBoard.isTileFlooded(p) && !theBoard.isTileSunk(p))
                return true;
        }
        return false;
    }

    public boolean move(char dir) {
        theBoard = Board.getInstance();
        Point p;
        movePosX = (int) position.getX();
        movePosY = (int) position.getY();
        switch(dir) {
            case 'w':
                movePosY++;
                p = new Point(movePosX,movePosY);
                if(movePosY < 6 && theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 'a':
                movePosX--;
                p = new Point(movePosX,movePosY);
                if(movePosX >= 0 && theBoard.getTileType(p) != TypeEnums.SEA ) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 's':
                movePosY--;
                p = new Point(movePosX,movePosY);
                if(movePosY >= 0 && theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            case 'd':
                movePosX++;
                p = new Point(movePosX,movePosY);
                if(movePosX < 6 && theBoard.getTileType(p) != TypeEnums.SEA) {
                    position.move(movePosX,movePosY);
                    return true;
                }
                else
                    return false;
            default:
                return false;
        }
    }

    public void helicopterMove(Point p) {
        setPos(p);
    }

    // could also use this to check if you can move.
    public boolean canSwim() {
        boolean isSwimmable = false;
        viableSwim.clear();
        theBoard = Board.getInstance();
        movePosX = (int) position.getX();
        movePosY = (int) position.getY();
        int x=0;int y=0;
        for(y=movePosY-1;y<movePosY+2;y=y+2){
			x = movePosX;
            Point p = new Point(x,y);
			boolean can2 = (x >= 0) && (x < 6) && (y < 6) && (y >= 0);
			if(can2){
                if((theBoard.getTileType(p) != TypeEnums.SEA)){
                    isSwimmable =true;
                    viableSwim.add(p);
                }
			}
        }
		for(x=movePosX-1;x<movePosX+2;x=x+2){
			y = movePosY;
			Point p = new Point(x,y);
			boolean can2 = (x >= 0) && (x < 6) && (y < 6) && (y >= 0);
			if(can2){
                if (theBoard.getTileType(p) != TypeEnums.SEA){
                    isSwimmable=true;
                    viableSwim.add(p);
                }
			}
        }
        return isSwimmable;
    }

    public List<Point> getViableSwims(){
        return viableSwim;
    }

}