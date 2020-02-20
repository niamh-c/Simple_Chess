package players;

public class HumanPlayer extends Player{
	private String name;
	public HumanPlayer(String name, boolean whiteSide) {
		this.name = name;
		this.whiteSide=whiteSide;
		this.humanPlayer=true;
	}
	
	public String getName() {
		return this.name;
	}

}
