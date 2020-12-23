package pawns;

import java.awt.Point;

import board.Board;
import enums.TilesEnums;
import enums.TypeEnums;

public class Diver extends Pawn{

	public Diver() {
		super();

	}
	
	@Override
	public String getPlayerType() {
		return "Diver";
	}

	public TilesEnums startLoc() {
		return TilesEnums.IRON_GATE;
	}

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
