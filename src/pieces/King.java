package pieces;

import board.Board;
import board.Spot;

public class King extends Piece {
	
	private boolean castlingDone=false;

	public King(boolean white) {
		super(white);
	}
	
	public boolean isCastlingDone() {
		return this.castlingDone;
	}

	public void setCastlingDone(boolean castlingDone) {
		this.castlingDone=castlingDone;
	}
	
	@Override
	public boolean canMove(Board board, Spot start, Spot end) throws Exception {
		// can only move a piece to a spot that doesn't 
		// hold a piece of the same colour
		if (end.getPiece()!= null && end.getPiece().isWhite()==this.isWhite()) return false;
		
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		
		//king can only move one place either forward/back or right/left
		if (x + y == 1) return true;
		
		return this.isValidCastling(board, start, end);
	}
	
	private boolean isValidCastling(Board board, Spot start, Spot end) throws Exception {
		if (this.isCastlingDone()) return false;
		//check if starting and end positions are correct
		if (!isCastlingMove(start, end)) return false;
		//check if end piece is Rook
		if (!(end.getPiece() instanceof Rook)) return false;
		//check if Spots between King and Rook are free
		if (!isEachHorizontalSpotEmpty(board, start, end)) return false;
		//check if King is in check
		return true;
	}
	
	public boolean isCastlingMove(Spot start, Spot end) {
		//check white pieces
		if (this.isWhite()) {
			//short castling
			if (start.getX() == 0 && start.getY() == 4
					&& end.getX() == 0 && end.getY() == 7)
				return true;
			//long castling
			else if (start.getX() == 0 && start.getY() == 4
					&& end.getX() == 0 && end.getY() == 0)
				return true;
			return false;
		}
		else {
			//short
			if (start.getX() == 7 && start.getY() == 4
					&& end.getX() == 7 && end.getY() == 7)
				return true;
			//long castling
			else if (start.getX() == 7 && start.getY() == 4
					&& end.getX() == 7 && end.getY() == 0)
				return true;
			return false;
		}
	}
	
	private boolean isEachHorizontalSpotEmpty(Board board, Spot start, Spot end) throws Exception {
		int y = start.getY() - end.getY();
		int tempY=start.getY();

		while (tempY != end.getY()) {
			if (y>0) tempY=tempY-1;
			else if (y<0) tempY=tempY+1;
			if (board.getBox(start.getX(), tempY).getPiece() != null) return false;
		}
		return true;
	}

}
