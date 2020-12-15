package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import board.Tile;
import enums.TypeEnums;

// To be removed
import enums.TilesEnums;

public class DrawTile {

	final int row = 6;
	final int col = 20;

	List<List<String>> lst2d;

	public DrawTile(Tile t) {
		this.lst2d = new ArrayList<List<String>>();
		createPerim(t);
		writeName(t);
		writeType(t);
	}

	private void createPerim(Tile t) {
		for(int i = 0; i < row; i++){
			List<String> lst = new ArrayList<String>();
			if(t.getType() == TypeEnums.SEA) {
				if(i == 0 || i==row-1)
					lst.add("wwwwwwwwwwwwwwwwwwww");
				else {
					lst.add("w");
					lst.add("                  ");
					lst.add("w");
				}
			}
			else {
				if(i == 0 || i==row-1)
					lst.add("xxxxxxxxxxxxxxxxxxxx");
				else {
					lst.add("x");
					lst.add("                  ");
					lst.add("x");
				}
			}
			lst2d.add(i,lst);
		}
	}

	private void writeName(Tile t) {
		lst2d.get(1).set(1,t.getName().toString());
	}

	private void writeType(Tile t) {
		lst2d.get(2).set(1,t.getType().toString());
	}

	private List<List<String>> get2dList() {
		return lst2d;
	}

	public static void main(String[] args) {
		Tile newTile = new Tile(0,0);
		newTile.setName(TilesEnums.SEA);
		newTile.setType(TilesEnums.SEA);
		DrawTile dTile = new DrawTile(newTile);
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
		DrawTile dTile2 = new DrawTile(tile2);
		List<List<String>> testList2 = dTile2.get2dList();
		System.out.println("\nPrinting tile2");
		for(List<String> list : testList2) {
			for(String s : list) {
				System.out.print(s);
			}
			System.out.println();
		}
		System.out.println(testList2.get(1).get(1));
	}
}