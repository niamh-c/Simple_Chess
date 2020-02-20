/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import board.Board;
import game.Game;
import pieces.Bishop;
import pieces.Piece;
import players.HumanPlayer;
import players.Player;

/**
 * @author niamh
 *
 */
public class BishopTest {
	
	@Test
	public void downLeftMovement() throws Exception {
		Player player = new HumanPlayer("p1", true);
		Player player1 = new HumanPlayer("p2",false);
		Game game=new Game(player, player1);
		Piece bishop = game.viewSpotOnBoard(0, 2);
		game.playerMove(player,0 , 2, 3, 5);
		assertEquals(bishop, game.viewSpotOnBoard(3, 5));
	}

}
