package pieces;

import board.Board;
import board.Spot;

public class Pawn extends Piece{

	public Pawn(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		
		if (end.getPiece()!= null && end.getPiece().isWhite()==this.isWhite()) return false;
		
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		if (this.isWhite()) {
			if (x!=-1) return false;
			if (y==0 && end.getPiece()==null) return true;
			if (Math.abs(y)==1 && (end.getPiece().isWhite()!=this.isWhite()))
				return true;
			return false;
		}
		else {
			if (x!=1) return false;
			if (y==0 && end.getPiece()==null) return true;
			if (Math.abs(y)==1 && (end.getPiece().isWhite()!=this.isWhite()))
				return true;
			return false;
		}
	}
	
	public boolean validFirstMove(Board board, Spot start, Spot end) {
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		
		if ((start.getPiece().isWhite() && x>0 && x<=2 && y==0) ||
				(!start.getPiece().isWhite() && x<0 && x>=-2 && y==0)) 
			return false;
		return true;
	}

}
