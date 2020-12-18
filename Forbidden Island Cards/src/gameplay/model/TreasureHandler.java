package gameplay.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.TreasureCardEnums;
import enums.TypeEnums;
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
				System.out.println("I shouldn't be here!");
        }
    }

    public boolean allCaptured(){
        if(wind && fire && water && earth){
            return true;
        }
        else{
            return false;
        }
    }

    public List<TypeEnums> captured(){
        List<TypeEnums> h1 = new ArrayList<TypeEnums>();
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
