package player;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Class for managing the entire team of players in Forbidden Island, 
 * Mainly a list of all players
 * 
 * @author  Cathal Ryan and Conor Kneafsey
 */
public class Team {
    private static Team     theTeam;
    private List<Player>    team;

    /**
     * Get the single instance of the team of Players in the game
     * @return The Team object
     */
    public static Team getInstance(){
        if(theTeam == null){
        	theTeam = new Team();
        }
        return theTeam;
    }

    /**
     * contructor of a new PlayerList
     */
    private Team() { 
    	this.team = new ArrayList<>();
    }

    /** 
     *@return size of the team
     */ 
    public int getNumPlayers() {
    	return team.size();
    }

    /** 
    * @return Player at an index i
     */ 
    public Player getPlayer(int i) {
    	return team.get(i);
    }

    /**
     * Get the index of a player in team
     * @param player Player in question
     * @return index of the player
     */
    public int getPlayerIndex(Player player){
    	return team.indexOf(player);
    }

    /** Adds a player to the team
     * @param newPlayer Player for add
     */
    public void addPlayer(Player newPlayer) {
    	team.add(newPlayer);
    }

    /** Returns a list of all players in team
     * @return team the list of all players in the team
     */
    public List<Player> getAllPlayers(){
    	return team;
    }

    /** Returns a list of all players indexes in team
     * @return allPlayers the list of all players' indexes in the team
     */
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
