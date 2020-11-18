package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.TreasureCardEnums;

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

	public Player choosePlayer(Scanner inputScanner, Player currentPlayer){
		int userIn = 0;
        for(int i=1;i<=playerList.size();i++){
            Player player = getPlayer(i);
            if(currentPlayer != getPlayer(i)){
                System.out.println("["+i+"] "+player.getName());	
            }
        }
		boolean validIn = false;
		while (!validIn) {
			String userString = inputScanner.nextLine();
			try {
				userIn = Integer.parseInt(userString);
			} catch (NumberFormatException e) {
				continue;
			}
			if ((userIn >= 0) && (userIn <= playerList.size()) && (userIn != playerList.indexOf(currentPlayer)+1) ) {
				validIn = true;
			}
		}
		return getPlayer(userIn);
    }

    public void enquirePlayers(Scanner inputScanner, boolean asked){
        List<Integer> eligible = new ArrayList<>();
        int i=0;
        for (Player p1 : playerList){
            if(p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT) || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)){
                eligible.add(i);
            }
            i++;
        }
        for(int j=1;i<=eligible.size();j++){
            Player player = getPlayer(eligible.get(j));
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
			if ((userIn >= 0) && (userIn <= playerList.size()) && (userIn != playerList.indexOf(currentPlayer)) ) {
				validIn = true;
			}
		}
		return getPlayer(userIn);

    }
}
