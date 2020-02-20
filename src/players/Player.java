package players;

public abstract class Player {
	
	public boolean whiteSide;
	public boolean humanPlayer;
	public String name;
	
	
	public boolean isWhiteSide() {
		return whiteSide;
	}
	
	public boolean isHumanPlayer() {
		return humanPlayer;
	}
	
	public String getName() {
		return this.name;
	}

}
