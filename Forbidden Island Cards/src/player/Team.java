package player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static Team  theTeam;
    private List<Player>       team;

    public static Team getInstance(){
        if(theTeam == null){
        	theTeam = new Team();
        }
        return theTeam;
    }

    private Team() { 
    	this.team = new ArrayList<>();
    }

    public int getNumPlayers() {
    	return team.size();
    }

    public Player getPlayer(int i) {
    	return team.get(i);
    }

    public int getPlayerIndex(Player player){
    	return team.indexOf(player);
    }
    
    public void addPlayer(Player newPlayer) {
    	team.add(newPlayer);
    }
    
    public List<Player> getAllPlayers(){
    	return team;
    }

    public List<Integer> getAllPlayerNums(int exclude){
        List<Integer> allPlayers = new ArrayList<>(); 
        int i=0;
        for (Player j:team){
            if((j.getNum() != exclude)){
                allPlayers.add(i);
            }
            i++;
        }
        return allPlayers;
    }
}
