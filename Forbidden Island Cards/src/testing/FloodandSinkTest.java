package testing;

import static org.junit.Assert.*;
import java.awt.Point;

import org.junit.Test;

import board.Board;
import cards.FloodCard;
import cards.FloodDiscardPile;
import enums.TypeEnums;
import gameplay.control.Controller;
import gameplay.model.GameModel;
import setup.BoardSetup;

public class FloodandSinkTest {

	@Test
	public void floodingTilesTest() { // Testing all tiles get flooded after drawing deck
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
        Controller theC = Controller.getInstance();
        for(int i=0;i<24;i++){
            theC.dealFloodCard();
        }
        boolean TilesFlooded=true;
        for(int x=0;x<6;x++){ // get all points
            for(int y=0;y<6;y++){
                Point p =new Point(x,y); 
                TypeEnums type = theBoard.getTileType(p);
                if(type!=TypeEnums.SEA){ // if not sea and its not flooded, then not correct
                    if(!theBoard.isTileFlooded(p)){
                        TilesFlooded=false;
                    }
                }
            }
        }
		assertTrue("All tiles flooded after drawing entire deck",TilesFlooded);
    }
    
	@Test
	public void sinkingTilesTest() { // Testing all tiles get sunk after drawing deck twice
	    BoardSetup bset= new BoardSetup(); bset.setTiles();
		Board theBoard=Board.getInstance();
        GameModel theM = GameModel.getInstance();
        FloodDiscardPile theTDP = FloodDiscardPile.getInstance();
        for(int i=0;i< 48;i++){
            FloodCard card1 = (FloodCard) theM.dealFlood();
        }
        boolean TilesSunk=true;
        for(int x=0;x<6;x++){ // get all points
            for(int y=0;y<6;y++){
                Point p =new Point(x,y); 
                TypeEnums type = theBoard.getTileType(p);
                if(type!=TypeEnums.SEA){ // if not sea then not correct
                	System.out.println(type);
                    TilesSunk=false;
                }
            }
        }
		assertTrue("All tiles sunk after drawing entire deck twice",TilesSunk);
	}
}
