package gameplay.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import enums.TypeEnums;
import player.*;
import board.Tile;


/**
  * DrawTile class to draw the board
  * Creates a list which contains a list of strings
  * The inner list contains a string with everything 
  * required for one tile's full line
  * The 2d list is used to hold each line that together build up
  * every tile line on the board which can then be printed to the
  * console
  * The lines consist of outside perimeters for the tile, displaying
  * the tile name, displaying the tile type, displaying if it is 
  * flooded, displaying the coordinates of the tile, and finally,
  * displaying if there are any players on the tile.
  * 
  * @author Cathal Ryan and Conor Kneafsey
  */

public class DrawTile {

	final int row = 6;			// number of rows we want a tile to have
	final int col = 6;			// number of columns of tiles in Board

	List<List<String>> lst2d;	// 2d list with the inner list holding strings

	/**
	  * Constructor for DrawTile. Initialised to 2d list
	  */
	public DrawTile() {
		this.lst2d = new ArrayList<List<String>>();
	}

	/**
	  * method to create a tile by building the perimeter
	  */
	public void createTile(Tile t) {
		createPerim(t);
		writeName(t);
		writeType(t);
		writeFlooded(t);
		placePawn(t);
	}

	/**
	  * create the perimeter around the tile consisting of xs
	  * for a normal tile but leave it black if its a seat tile.
	  * Spaces are left in the middle that can be overwritten 
	  *	later with tile information
	  */
	private void createPerim(Tile t) {
		// loop through the amount of rows we want to draw
		for(int i = 0; i < row; i++){
			List<String> lst = new ArrayList<String>();

			// if its a sea tile, leave the perimeter blank
			if(t.getType() == TypeEnums.SEA) {
				if(i == 0 || i==row-1)
					lst.add("                     ");
				else {
					lst.add(" ");
					if(i == row-2) {
						Point p = t.getPos();

						// Add the tile coordinates
						lst.add("("+(int) p.getX()+","+(int) p.getY()+")");
						lst.add("             ");
					}
					else
						lst.add("                  ");
					lst.add("  ");
				}
			}

			// if it is a normal tile, create a perimeter using xs
			else {
				if(i == 0 || i==row-1)
					lst.add("xxxxxxxxxxxxxxxxxxxx ");
				else {
					lst.add("x");
					if(i == row-2) {
						Point p = t.getPos();

						// Add the tile coordinates
						lst.add("("+(int) p.getX()+","+(int) p.getY()+")");
						lst.add("             ");
					}
					else
						lst.add("                  ");
					lst.add("x ");
				}
			}
			lst2d.add(i,lst);
		}
	}

	/**
	  *	replaces the empty space on line 2 with the tile name
	  */
	private void writeName(Tile t) {
		lst2d.get(1).set(1,t.getName().toString());
	}

	/**
	  * replaces the empty space on line 3 with the tile type
	  */
	private void writeType(Tile t) {
		if(t.getType() != TypeEnums.NORMAL)
			lst2d.get(2).set(1,t.getType().toString());
	}

	/**
	  * check if a tile has flooded or sank and display 
	  * this information on line 4
	  */
	private void writeFlooded(Tile t) {
		if(t.isSunk()) {
			String status = "     Has Sunk     ";
			lst2d.get(3).set(1,status);
		}
		else if(t.isFlooded()) {
			String status = "     Flooded      ";
			lst2d.get(3).set(1,status);
		}
	}

	/**
	  * display all players who are on this tile on line 5
	  */
	private void placePawn(Tile t) {
		Team theTeam = Team.getInstance();
		String charSymb = "";

		// get all the players on the tile
		for(Player p : theTeam.getAllPlayers()) {
			if(p.getPawnPos().equals(t.getPos())) {
				charSymb += p.getChar();
			}
		}
		int size = charSymb.length();
		if(size == 0)
			charSymb = "             ";
		else if(size == 1)
			charSymb = "   "+charSymb+"         ";
		else if(size == 2)
			charSymb = "   "+charSymb+"        ";
		else if(size == 3)
			charSymb = "   "+charSymb+"       ";
		else if(size == 4)
			charSymb = "  "+charSymb+"       ";
		lst2d.get(4).set(2,charSymb);
	}

	/**
	  * Print a line from a single tile using the given board column
	  *	@param boardCol represents the column on the board that we wish to print the tile
	  * @param line represents which line of the Tile we want to print
	  */
	private void dispTileLine(int boardCol, int line) {
		List<String> list = lst2d.get(boardCol*row+line);
		for(String s : list)
			System.out.print(s);
	}

	/**
	  * Print all lines that create the tile in a full row across the board
	  */
	public void dispFullRow() {
		for(int line=0; line<row; line++) {
			for(int boardCol=0; boardCol<col; boardCol++)
				dispTileLine(boardCol, line);
			System.out.println();
		}
	}
}