package gameplay.model;

import java.util.ArrayList;
import java.util.List;

import enums.TypeEnums;
/**
 * Singleton Treasure Handling class, allows for keeping track of treasures captured
 * and allows for new treasures to be captured
 * @author Cathal Ryan and Conor Kneafsey
 *
 */
public class TreasureHandler {
	
    private  static TreasureHandler theTH; // Singleton self
    private  static boolean wind; // Each of the treasures captured status represented by boolean
    private  static boolean fire;
    private  static boolean earth;
    private  static boolean water;

    /**
     * getInstance singleton method returns the single instance
     * of the treasureHandler object.
     * @return The single TreasureHandler object
     */
    public static TreasureHandler getInstance(){
        if(theTH == null){
            theTH = new TreasureHandler();
        }
        return theTH;
    }

    /**
     * The private TreasureHandler constructor
     */
    private TreasureHandler() {
        wind     = false; // Statue of the Wind
        fire     = false; // Crystal of Fire
        earth    = false; // Earth Stone
        water    = false; // Ocean's Chalice
    }
    
	/**
	 * setTreasureCapture allows for declaring a treasure to be captured
	 * Depending on the tile entered, it will set one of the treasure booleans
	 * to true.
	 * @param tile Type of tile giving what treasure to be captured
	 */
    public static void setTreasureCapture(TypeEnums tile){
    			switch (tile) {
			case FIRE:
				fire=true;
				break;
			case WATER:
				water=true;
				break;
			case WIND:
				wind=true;
				break;
			case EARTH:
				earth=true;
                break;
            default:
				break; // If a non-treasure tile entered, do nothing. Can't capture treasure here
        }
    }
    
	/**
	 * allCaptured queries if all the treasures are captured
	 * @return True if all treasures are captured
	 */
    public boolean allCaptured(){
        if(wind && fire && water && earth){
            return true;
        }
        else{
            return false;
        }
    }
    
	/**
	 * captured returns a list of all the currently captured treasures
	 * @return List of all captured treasures
	 */
    public List<TypeEnums> captured(){
        List<TypeEnums> h1 = new ArrayList<>();
        if(earth){
            h1.add(TypeEnums.EARTH);
        }
        if(water){
            h1.add(TypeEnums.WATER);
        }
        if(fire){
            h1.add(TypeEnums.FIRE);
        }
        if(wind){
            h1.add(TypeEnums.WIND);
        }
        return h1;
    }
    
	/**
	 * captured returns a list of all the currently captured treasures
	 * @param tile Type of tile to ask whether treasure has been captured of this type or not
	 * @return True if that type of tile has its treasure captured already
	 */
	public boolean queryCaptured(TypeEnums tile) {
        if(tile == TypeEnums.EARTH){
            return earth;
        }
        if(tile == TypeEnums.FIRE){
            return fire;
        }
        if(tile == TypeEnums.WATER){
            return water;
        }
        if(tile == TypeEnums.WIND){
            return wind;
        }
		return false;
	}
}
