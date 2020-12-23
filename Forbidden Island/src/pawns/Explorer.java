package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

/**
 * Class for the Explorer which is a subclass of Pawn
 * Sets the pawns starting location and can return the
 * pawn type. Also facilitates swiming diagonally
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Explorer extends Pawn{

	/**
	  * Constructor for Explorer. Implements the constructor
	  * from the parent class Pawn.
	  */
	public Explorer() {
		super();

	}


	/**
	  * Return a string with the type of pawn it is
	  * @return that it is an Explorer
	  */
	public String getPlayerType() {
		return "Explorer";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Copper Gate - where Explorer starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.COPPER_GATE;
	}

	/**
	  * Override the canSwim() method found in the parent class Pawn
	  * Implements the explorer's special swimming ability that allows it
	  * to swim to diagonal tiles
	  * Adds the nearest tiles the explorer can swim to into the arraylist
	  * viableSwim
	  * @return isSwimmable to see if it can swim to a tile
	  */
    public boolean canSwim() {
		viableSwim.clear();
		boolean isSwimmable = false;
        Board theBoard = Board.getInstance();
        int movePosX = (int) position.getX();
        int movePosY = (int) position.getY();
        int x=0;int y=0;
        for(x=movePosX-1;x<movePosX+2;x++){
            for(y=movePosY-1;y<movePosY+2;y++){
				Point p = new Point(x,y);
                boolean can2 = (x > 0) && (x < 6) && (y < 6) && (y > 0);
                if(can2){
					if (theBoard.getTileType(p) != TypeEnums.SEA){
						isSwimmable=true;
						viableSwim.add(p);
					}
                }
            }
        }
        return isSwimmable;
    }

}
