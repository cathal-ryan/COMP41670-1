package board;

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

	public Tile(int x, int y) {
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

	public void clean() {
		flooded = false;
		sunk = false;
	}
}