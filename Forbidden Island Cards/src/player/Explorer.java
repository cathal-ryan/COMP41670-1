package player;

public class Explorer extends Player{

	public Explorer(int playerNum, String playerName) {
		super(playerNum, playerName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getPlayerType() {
		return "Explorer";
	}

}
