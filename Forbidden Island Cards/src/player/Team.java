package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.TreasureCardEnums;
import gameplay.Choices;

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
    	this.team = new ArrayList<Player>();
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

    public List<Integer> getPlayerswithSpecials(){
        List<Integer> eligible = new ArrayList<>();
        int i=0;
        for (Player p1 : team){
            if(p1.getHand().checkContains(TreasureCardEnums.HELICOPTER_LIFT) || p1.getHand().checkContains(TreasureCardEnums.SANDBAGS)){
                eligible.add(i);
            }
            i++;
        }
        return eligible;
    }

    public boolean enquirePlayers(Scanner inputScanner, boolean asked){
        List<Integer> eligible = getPlayerswithSpecials();
        if(eligible.isEmpty()){
            if(asked){
                System.out.println("No one has a special card...");
            }
            return false;
        }
        if(!Choices.getYesOrNo(inputScanner,"Does anyone want to play their special card?", "No", "Yes")){
            return false;
        }
        showAllHands();
        System.out.println("\nWho will play their special card?");
        Player player1 = choosePlayer(inputScanner,eligible);
        if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT) && player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            if(Choices.getYesOrNo(inputScanner,"Do you want to play Helicopter Lift or Sandbags", "Sandbags", "Helicopter Lift")){
                return useHelicopterLift(inputScanner, player1);
            }
            else{
                useSandbags(player1);
                return false;
            }
        }
        else if(player1.checkHasCard(TreasureCardEnums.HELICOPTER_LIFT)){
            return useHelicopterLift(inputScanner, player1);
        }
        else if(player1.checkHasCard(TreasureCardEnums.SANDBAGS)){
            useSandbags(player1);
            return false;
        }
        return false;
    }

	private boolean canWin() {
        // Check the position of all players if theyre on fools landing
        return false;
    }

}
