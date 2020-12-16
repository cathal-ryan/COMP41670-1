package board;

import java.util.Arrays;
import java.awt.Point;

import enums.TilesEnums;
import enums.TypeEnums;

public class Tile {

	private TilesEnums tileName;
	private TypeEnums tileType;
	private Point pos;
	private Point up;
	private Point down;
	private Point left;
	private Point right;
	private boolean flooded;
	private boolean sunk;

	protected Tile(int x, int y) {
		this.tileName = TilesEnums.SEA;
		this.tileType = TypeEnums.SEA;

		this.pos = new Point(x,y);
		this.up = new Point(x, y+1);
		this.down = new Point(x, y-1);
		this.left = new Point(x-1, y);
		this.right = new Point(x+1, y);

		this.flooded = false;
		this.sunk = false;
	}

	public TilesEnums getName() {
		return tileName;
	}

	public TypeEnums getType() {
		return tileType;
	}
	
	public Point getPos() {
		return pos;
	}

	public Point getUp() {
		return up;
	}

	public Point getDown() {
		return down;
	}

	public Point getLeft() {
		return left;
	}

	public Point getRight() {
		return right;
	}

	public boolean isFlooded() {
		return flooded;
	}

	public boolean isSunk() {
		return sunk;
	}

	public void flood() {
		if(isFlooded())
			sink();
		else
			flooded = true;
	}

	public void shoreUp() {
		if(!isSunk())
			flooded = false;
	}

	private void sink() {
		sunk = true;
		setType(TilesEnums.SEA);
	}

	public void setName(TilesEnums name) {
		tileName = name;
	}

	public void setType(TilesEnums name) {
		switch(name) {
			case FOOLS_LANDING:
				tileType = TypeEnums.FOOLS_LANDING;
				break;
			case TEMPLE_OF_THE_MOON:
				tileType = TypeEnums.EARTH;
				break;
			case TEMPLE_OF_THE_SUN:
				tileType = TypeEnums.EARTH;
				break;
			case WHISPERING_GARDEN:
				tileType = TypeEnums.WIND;
				break;
			case HOWLING_GARDEN:
				tileType = TypeEnums.WIND;
				break;
			case CAVE_OF_EMBERS:
				tileType = TypeEnums.FIRE;
				break;
			case CAVE_OF_SHADOWS:
				tileType = TypeEnums.FIRE;
				break;
			case CORAL_PALACE:
				tileType = TypeEnums.WATER;
				break;
			case TIDAL_PALACE:
				tileType = TypeEnums.WATER;
				break;
			case SEA:
				tileType = TypeEnums.SEA;
				break;
			default:
				tileType = TypeEnums.NORMAL;
		}
	}

	public static void main(String[] args) {

		Tile newTile = new Tile(0,0);
		System.out.println("\nTile name is: " + newTile.getName());
		System.out.println("Tile type is: " + newTile.getType());

		newTile.setName(TilesEnums.HOWLING_GARDEN);
		newTile.setType(TilesEnums.HOWLING_GARDEN);
		System.out.println("\nTile name is: " + newTile.getName());
		System.out.println("Tile type is: " + newTile.getType());
		System.out.println("Tile pos is: " + newTile.getPos());
		System.out.println("Is " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		
		newTile.flood();
		System.out.println("\nIs " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		
		newTile.shoreUp();
		System.out.println("\nIs " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		
		newTile.flood();
		newTile.flood();
		System.out.println("\nIs " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		System.out.println("Is " + newTile.getName() + " sunk?: " + newTile.isSunk());
		
		newTile.shoreUp();
		System.out.println("\nIs " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		System.out.println("Is " + newTile.getName() + " sunk?: " + newTile.isSunk());
		
		newTile.shoreUp();
		newTile.shoreUp();
		System.out.println("\nIs " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		System.out.println("Is " + newTile.getName() + " sunk?: " + newTile.isSunk());
	}
}