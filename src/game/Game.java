package game;

import board.Board;
import board.Spot;
import pieces.King;
import pieces.Piece;
import players.Player;
import game.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player[] players;
	private Board board;
	private Player currentTurn;
	private GameStatus status;
	private List<Move> movesPlayed;
	
	public Game(Player p1, Player p2) {
		players= new Player[]{p1, p2};
		board=new Board();
		if (p1.isWhiteSide()) this.currentTurn = p1;
		else this.currentTurn = p2;
		status = GameStatus.ACTIVE;
		movesPlayed=new ArrayList();
	}
	
	public boolean isEnd() {
		return this.getStatus() != GameStatus.ACTIVE;
	}
	
	public GameStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(GameStatus status) {
		this.status=status;
	}
	
	public boolean playerMove(Player player, int startX, int startY, 
			int endX, int endY) throws Exception {
		Spot startBox = board.getBox(startX, startY);
		Spot endBox = board.getBox(endX, endY);
		Move move = new Move(player, startBox, endBox);
		return this.makeMove(move, player);
		
	}
	
	private boolean makeMove(Move move, Player player) throws Exception {
		Piece sourcePiece = move.getStart().getPiece();
		if (sourcePiece == null) {
			System.out.println("There is no piece to play on the selected spot.");
			return false;
		}
		//valid player
		if (player != currentTurn) {
			System.out.println("It is not your turn to play.");
			return false;
		}
		//valid move?
		if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
			System.out.println("That is an invalid move for the selected piece.");
			return false;
		}
			
		//kill?
		Piece destPiece = move.getStart().getPiece();
		if (destPiece !=null) {
			destPiece.setKilled(true);
			move.setPieceKilled(destPiece);
		}
		//castling?
		if (sourcePiece != null && sourcePiece instanceof King) {
			King sourcePieceKing = (King) sourcePiece;
			if (sourcePieceKing.isCastlingMove(move.getStart(), move.getEnd()))
					move.setCastlingMove(true);
		}
		
		//store the move
		movesPlayed.add(move);
		
		//move piece from the stat box to end box
		move.getEnd().setPiece(move.getStart().getPiece());
		move.getStart().setPiece(null);
		
		if(destPiece != null && destPiece instanceof King) {
			if (player.isWhiteSide()) {
				System.out.println("White Wins!");
				this.setStatus(GameStatus.WHITE_WIN);
			}
			else {
				System.out.println("Black Wins!");
				this.setStatus(GameStatus.BLACK_WIN);
			}
		}
		
		//set the current turn to the other player
		if (this.currentTurn == players[0]) this.currentTurn = players[1];
		else this.currentTurn = players[0];
			
		return true;
	}
	
	public Piece viewSpotOnBoard(int x, int y) throws Exception {
		return board.getBox(x, y).getPiece();
	}
	
	public Player getCurrentTurn() {
		return this.currentTurn;
	}
	 
}
