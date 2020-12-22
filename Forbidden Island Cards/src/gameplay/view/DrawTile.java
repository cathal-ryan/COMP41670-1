package gameplay.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import enums.TypeEnums;
import player.*;
import board.Tile;

// To be removed
import enums.TilesEnums;

public class DrawTile {

	// number of rows in Tile Drawing
	final int row = 6;
	// number of columns of tiles in Board
	final int col = 6;

	List<List<String>> lst2d;

	public DrawTile() {
		this.lst2d = new ArrayList<List<String>>();
	}

	public void createTile(Tile t) {
		createPerim(t);
		writeName(t);
		writeType(t);
		writeFlooded(t);
		placePawn(t);
	}

	private void createPerim(Tile t) {
		for(int i = 0; i < row; i++){
			List<String> lst = new ArrayList<String>();
			if(t.getType() == TypeEnums.SEA) {
				if(i == 0 || i==row-1)
					lst.add("                     ");
				else {
					lst.add(" ");
					if(i == row-2) {
						Point p = t.getPos();
						lst.add("("+(int) p.getX()+","+(int) p.getY()+")");
						lst.add("             ");
					}
					else
						lst.add("                  ");
					lst.add("  ");
				}
			}
			else {
				if(i == 0 || i==row-1)
					lst.add("xxxxxxxxxxxxxxxxxxxx ");
				else {
					lst.add("x");
					if(i == row-2) {
						Point p = t.getPos();
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

	private void writeName(Tile t) {
		lst2d.get(1).set(1,t.getName().toString());
	}

	private void writeType(Tile t) {
		if(t.getType() != TypeEnums.NORMAL)
			lst2d.get(2).set(1,t.getType().toString());
	}

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

	private void placePawn(Tile t) {
		Team theTeam = Team.getInstance();
		String charSymb = "";
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

	public List<List<String>> get2dList() {
		return lst2d;
	}

	private void dispRow(int tileNum, int line) {
		List<String> list = lst2d.get(tileNum*row+line);
		for(String s : list)
			System.out.print(s);
	}

	public void dispAllRows() {
		for(int line=0; line<row; line++) {
			for(int tileNum=0; tileNum<col; tileNum++)
				dispRow(tileNum, line);
			System.out.println();
		}
	}
}