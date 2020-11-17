package gameplay;

import java.util.Collections;

import enums.TilesEnums;
import enums.TreasureCardEnums;
import player.Player;

public class TreasureHandler {

    private  static boolean wind;
    private  static boolean fire;
    private  static boolean earth;
    private  static boolean water;
    private  static TreasureHandler theTH;

    public static TreasureHandler getInstance(){
        if(theTH == null){
            theTH = new TreasureHandler();
        }
        return theTH;
    }

    private TreasureHandler() {
        wind     = false;
        fire     = false;
        earth    = false;
        water    = false;
    }

    public static void captureTreasure(Player player){
		// Check if the pawn's location corresponds to any of the treasure tiles.
		// it should return the name of the treasure tile. Enum tile = tile.getTileName()
		TreasureCardEnums tile = TreasureCardEnums.CRYSTAL_OF_FIRE;
		int occurences = Collections.frequency(player.getHand().getCardNamesAsStrings(), tile.toString());
		if(occurences>=1){
            player.getHand().discardforTreasure(tile);
            setTreasureCapture(tile);
		}
		else{
			System.out.println("You need at least 4 of the same card to claim a treasure!");
		}
    }
    
    public static void setTreasureCapture(TreasureCardEnums tile){
		switch (tile) {
			case CRYSTAL_OF_FIRE:
				fire=true;
				break;
			case OCEANS_CHALICE:
				water=true;
				break;
			case STATUE_OF_THE_WIND:
				wind=true;
				break;
			case EARTH_STONE:
				earth=true;
                break;
            default:
				System.out.println("I shouldn't be here!");
        }
    }

    public boolean allTreasuresCaptured(){
        if(wind && fire && water && earth){
            return true;
        }
        else{
            return false;
        }
    }

}
