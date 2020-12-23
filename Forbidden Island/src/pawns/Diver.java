package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

/**
 * Class for the Diver which is a subclass of Pawn
 * Sets the pawns starting location and implements
 * their swimming ability when a tile sinks
 * 
 * @author Cathal Ryan and Conor Kneafsey
 */
public class Diver extends Pawn{

	/**
	  * Constructor for Diver. Implements the constructor from the
	  * parent class Pawn.
	  */
	public Diver() {
		super();

	}
	
	/**
	  * Return a string with the type of pawn it is
	  * @return that it is a Diver
	  */
	public String getPlayerType() {
		return "Diver";
	}

	/**
	  * Return the name of the pawn's starting location
	  * @return Iron Gate - where Diver starts
	  */
	public TilesEnums startLoc() {
		return TilesEnums.IRON_GATE;
	}

	/**
	  * Override the canSwim() method found in the parent class Pawn
	  * Implements the diver's special swimming ability that allows it
	  * to move through sunken tiles
	  * Adds the nearest tiles the diver can swim to into the arraylist
	  * viableSwim
	  * @return a boolean check to see if it can swim to a tile
	  */
	@Override
    public boolean canSwim() {
		Board theBoard = Board.getInstance();
        viableSwim.clear();
        movePosX = (int) position.getX();
        movePosY = (int) position.getY();
		int currentMinDist = 1000;
		int distance =1000;
		for(int x=0;x<6;x++){
			for(int y=0;y<6;y++){
				Point p = new Point(x,y);
				if (theBoard.getTileType(p) != TypeEnums.SEA){
					distance = Math.abs(x-movePosX) + Math.abs(y-movePosY);
					if (distance==currentMinDist){
						viableSwim.add(new Point(x,y));
					}
					if (distance<currentMinDist){
						viableSwim.clear();
						viableSwim.add(new Point(x,y));
						currentMinDist = distance;
					}
				}
			}
		}
        return true;
    }

}
