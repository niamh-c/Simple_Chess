package pieces;

import board.Board;
import board.Spot;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) throws Exception{
		
		if (end.getPiece()!= null && end.getPiece().isWhite() == this.isWhite()) return false;
		
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		
		//check diagional
		if (Math.abs(x) != Math.abs(y))  return false;
		//check Board pieces in between are empty
		return isEachDiagonalSpotEmpty(board, start, end);
	}
	
	private boolean isEachDiagonalSpotEmpty(Board board, Spot start, Spot end) throws Exception {
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		int tempX=start.getX();
		int tempY=start.getY();

		while (tempX != end.getX()) {
			if (x>0) tempX=tempX-1;
			else if (x<0) tempX=tempX+1;
			if (y>0) tempY=tempY-1;
			else if (y<0) tempY=tempY+1;
			if (board.getBox(tempX, tempY).getPiece() != null) return false;
		}
		return true;
	}
}
