package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

public class Explorer extends Pawn{

	public Explorer() {
		super();

	}
	@Override
	public String getPlayerType() {
		return "Explorer";
	}

	public TilesEnums startLoc() {
		return TilesEnums.COPPER_GATE;
	}

	// checks grid around the explorer to see if theyre viable swim options.
	@Override
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
