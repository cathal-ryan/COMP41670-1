package board;

import java.util.Arrays;

import enums.TilesEnums;
import enums.TypeEnums;

public class Tile {

	private TilesEnums tileName;
	private TypeEnums tileType;
	private int[] pos;
	private int[] up;
	private int[] down;
	private int[] left;
	private int[] right;
	private boolean flooded;
	private boolean sunk;

	protected Tile(int x, int y, TilesEnums name) {
		this.tileName = name;

		this.pos = new int[] {x, y};
		this.up = new int[] {x, y+1};
		this.down = new int[] {x, y-1};
		this.left = new int[] {x-1, y};
		this.right = new int[] {x+1, y};

		switch(tileName) {
			case FOOLS_LANDING:
				this.tileType = TypeEnums.FOOLS_LANDING;
				break;
			case TEMPLE_OF_THE_MOON:
				this.tileType = TypeEnums.EARTH;
				break;
			case TEMPLE_OF_THE_SUN:
				this.tileType = TypeEnums.EARTH;
				break;
			case WHISPERING_GARDEN:
				this.tileType = TypeEnums.WIND;
				break;
			case HOWLING_GARDEN:
				this.tileType = TypeEnums.WIND;
				break;
			case CAVE_OF_EMBERS:
				this.tileType = TypeEnums.FIRE;
				break;
			case CAVE_OF_SHADOWS:
				this.tileType = TypeEnums.FIRE;
				break;
			case CORAL_PALACE:
				this.tileType = TypeEnums.WATER;
				break;
			case TIDAL_PALACE:
				this.tileType = TypeEnums.WATER;
				break;
			default:
				this.tileType = TypeEnums.NORMAL;
		}

		this.flooded = false;
		this.sunk = false;
	}

	public void flood() {
		flooded = true;
	}

	public void shoreUp() {
		flooded = false;
	}

	public void sink() {
		sunk = true;
	}

	public TilesEnums getName() {
		return tileName;
	}

	public TypeEnums getType() {
		return tileType;
	}
	
	public int[] getPos() {
		return pos;
	}

	public int[] getUp() {
		return up;
	}

	public int[] getDown() {
		return down;
	}

	public int[] getLeft() {
		return left;
	}

	public int[] getRight() {
		return right;
	}

	public boolean isFlooded() {
		return flooded;
	}

	public boolean isSunk() {
		return sunk;
	}

	public static void main(String[] args) {

		Tile newTile = new Tile(0,0, TilesEnums.IRON_GATE);
		System.out.println("Tile name is: " + newTile.getName());
		System.out.println("Tile type is: " + newTile.getType());
		System.out.println("Tile pos is: " + Arrays.toString(newTile.getPos()));
		System.out.println("Is " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		newTile.flood();
		System.out.println("Is " + newTile.getName() + " flooded?: " + newTile.isFlooded());
		newTile.shoreUp();
		System.out.println("Is " + newTile.getName() + " flooded?: " + newTile.isFlooded());

	}
}