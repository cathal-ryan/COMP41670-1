package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    public void showAllHands(){
        for(int i=1;i<=playerList.size();i++){
            Player player = getPlayer(i);
            System.out.println("\n"+player.getName()+"'s hand: ");	
            player.getHand().printHand();
        }
        System.out.println("");
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

	public Player choosePlayer(Scanner inputScanner){
		int userIn = 0;
        for(int i=1;i<=playerList.size();i++){
            Player player = getPlayer(i);
            System.out.println("["+i+"] "+player.getName());	
        }
        System.out.println("");
		boolean validIn = false;
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
				continue;
			}
			if ((userIn >= 0) && (userIn <= playerList.size())) {
				validIn = true;
			}
		}
		return getPlayer(userIn);
    }
}
