package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private static PlayerList  thePlayerList;
    private List<Player>       playerList;
    

    public static PlayerList getInstance(){
        if(thePlayerList == null){
        	thePlayerList = new PlayerList();
        }
        return thePlayerList;
    }
    

    private PlayerList() { 
    	this.playerList = new ArrayList<Player>();
    }

    public int getNumPlayers() {
    	return playerList.size();
    }

    public Player getPlayer(int i) {
    	return playerList.get(i-1);
    }
    

    public int getPlayerIndex(Player player){
    	return playerList.indexOf(player)+1;
    }

    public void setPlayer(int i, Player updatedPlayer) {
    	playerList.set(i-1, updatedPlayer);
    }
    

    public void addPlayer(Player newPlayer) {
    	playerList.add(newPlayer);
    }
    

    public List<Player> getAllPlayers(){
    	return playerList;
    }

}
