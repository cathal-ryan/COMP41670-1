package pawns;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

/**
 * Abstract Class for the Pawn which is the basic playing piece
 * Set the pawn position on the board, check whether a pawn can
 * shore up a tile at a given point, move the pawn around the board,
 * use helicopter lift to move the player to a given tile, check if
 * a pawn can swim to another tile if their tile sinks and add the
 * swimmable tiles to a list
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
abstract public class Pawn {

    protected Point position;
    protected Board theBoard;
    protected List<Point> viableSwim = new ArrayList<Point>();
    protected int movePosX;
    protected int movePosY;

    protected Pawn() {
    }

    /**
      * abstract method to return a pawns subtype eg: diver, pilot etc
      */
    abstract public String getPlayerType();

    /**
      * abstract method to return the tile name of their starting location
      */
    abstract public TilesEnums startLoc();

    /**
      * set the position of a pawn
      */
    public void setPos(Point p) {
        this.position = new Point(p);
    }

    /**
      * get the position of a pawn
      */
    public Point getPos() {
        return position;
    }

    /**
      * check if a pawn can shore up a tile at a given position
      * @return true if the tile is adjacent but not diagonal to
                a pawn's current position
      */
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

    /**
      * move a pawn in a given direction
      * Check to see if the tile it is moving to is viable ie: it is within the 
      * boundaries of the board and it is not a sea/sunken tile
      * @return true if the pawn can move in the given direction
      */
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

    /**
      * implement helicopter lift which moves a pawn to 
      * a given location
      */
    public void helicopterMove(Point p) {
        setPos(p);
    }

    /**
      * Check what the nearest tiles a pawn can swim to are if the 
      * tile they are currently on is sunk. Pawns can only swim to 
      * adjacent tiles that are not diagonal. 
      * Add any viable tiles a pawn can swim to to a List
      * @return isSwimmable - true if it can swim to a tile
      */
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

    /**
      * return all the available tiles a pawn can swim to
      * @return viableSwim - List with coordinates of tiles
      */
    public List<Point> getViableSwims(){
        return viableSwim;
    }

}