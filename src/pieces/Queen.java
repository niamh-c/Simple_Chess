package pieces;

import board.Board;
import board.Spot;

public class Queen extends Piece{

	public Queen(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) throws Exception {
		
		if (end.getPiece()!= null && end.getPiece().isWhite() == this.isWhite()) return false;
		//check Board pieces in between are empty
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		if (x==0)
			return isEachHorizontalSpotEmpty(board, start, end);
		if (y==0)
			return isEachVerticalSpotEmpty(board, start, end);
		if (x == y)
			return isEachDiagonalSpotEmpty(board, start, end);
		return false;
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
	private boolean isEachVerticalSpotEmpty(Board board, Spot start, Spot end) throws Exception {
		int x = start.getX() - end.getX();
		int tempX=start.getX();

		while (tempX != end.getX()) {
			if (x>0) tempX=tempX-1;
			else if (x<0) tempX=tempX+1;
			if (board.getBox(tempX, start.getY()).getPiece() != null) return false;
		}
		return true;
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
