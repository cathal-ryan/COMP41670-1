package board;

import java.util.ArrayList;
import java.util.List;
import enums.TypeEnums;
import player.*;

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
					lst.add("                  ");
					lst.add("  ");
				}
			}
			else {
				if(i == 0 || i==row-1)
					lst.add("xxxxxxxxxxxxxxxxxxxx ");
				else {
					lst.add("x");
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
			charSymb = "                  ";
		else if(size == 1)
			charSymb = "        "+charSymb+"         ";
		else if(size == 2)
			charSymb = "        "+charSymb+"        ";
		else if(size == 3)
			charSymb = "        "+charSymb+"       ";
		else if(size == 4)
			charSymb = "       "+charSymb+"       ";
		lst2d.get(4).set(1,charSymb);
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

	public static void main(String[] args) {
		Tile newTile = new Tile(0,0);
		newTile.setName(TilesEnums.SEA);
		newTile.setType(TilesEnums.SEA);
		DrawTile dTile = new DrawTile();
		dTile.createTile(newTile);
		List<List<String>> testList = dTile.get2dList();
		for(List<String> list : testList) {
			for(String s : list) {
				System.out.print(s);
			}
			System.out.println();
		}

		Tile tile2 = new Tile(1,0);
		tile2.setName(TilesEnums.CAVE_OF_SHADOWS);
		tile2.setType(TilesEnums.CAVE_OF_SHADOWS);
		dTile.createTile(tile2);
		List<List<String>> testList2 = dTile.get2dList();
		System.out.println("\nPrinting tile2");
		for(List<String> list : testList2) {
			for(String s : list) {
				System.out.print(s);
			}
			System.out.println();
		}

		System.out.println("New disp test");
		dTile.dispAllRows();
		System.out.println(testList2.get(1).get(1));
	}
}