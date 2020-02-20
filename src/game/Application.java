package game;

import java.util.Scanner;

import players.HumanPlayer;
import players.Player;

public class Application {
	
	public static void main(String[] args) throws Exception {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Player 1, please enter your name. You will be white.");
		String name = myObj.nextLine();
		Player player = new HumanPlayer(name, true);
		System.out.println("Player 2, please enter your name. You will be black.");
		name = myObj.nextLine();
		Player player1 = new HumanPlayer(name,false);
		Game game=new Game(player, player1);
		
		while (game.getStatus()==GameStatus.ACTIVE) {
			String currPlayer = game.getCurrentTurn().getName();
			System.out.println(currPlayer+" will move next.");
			System.out.println("Please input the coordinates of the piece you would like to move (x,y)");
			String coordinate = myObj.nextLine();
		    String[] parts = coordinate.split(",");
		    parts[0]=parts[0].trim();
		    parts[1]=parts[1].trim();
		    int startX = Integer.parseInt(parts[0]);
		    int startY = Integer.parseInt(parts[1]);
			System.out.println("Please input the coordinates of where you would like to move the piece(x,y)");
			coordinate = myObj.nextLine();
		    parts = coordinate.split(",");
			parts[0]=parts[0].trim();
		    parts[1]=parts[1].trim();
		    int endX = Integer.parseInt(parts[0]);
		    int endY = Integer.parseInt(parts[1]);
			game.playerMove(game.getCurrentTurn(), startX, startY, endX, endY);
		}
	}

}
