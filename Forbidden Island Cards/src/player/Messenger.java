package player;

public class Messenger extends Player{

	public Messenger(int playerNum, String playerName) {
		super(playerNum, playerName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getPlayerType() {
		return "Messenger";
	}

}
