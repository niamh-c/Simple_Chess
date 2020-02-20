package board;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {
	
	Spot[][] boxes;
	
	public Board() {
		boxes=new Spot[8][8];
		this.resetboard();
	}
	
	public Spot getBox(int x, int y) throws Exception {
		if (x<0 || x>7 || y<0 || y>7) 
			throw new Exception("Index out of bounds");
		
		return boxes[x][y];
	}
	
	public void resetboard() {
		Piece[] whitePieces = new Piece[]{new Rook(true),new Knight(true),
				new Bishop(true), new Queen(true), new King(true),
				new Bishop(true), new Knight(true), new Rook(true)};
		Piece[] blackPieces = new Piece[]{new Rook(false),new Knight(false),
				new Bishop(false), new Queen(false), new King(false),
				new Bishop(false), new Knight(false), new Rook(false)};
		//white pieces
		for (int y=0; y<=7; y++) {
			boxes[0][y]=new Spot(0,y,whitePieces[y]);
			boxes[1][y]=new Spot(1,y,new Pawn(true));
			}
		//black pieces
		for (int y=0; y<=7; y++) {
			boxes[7][y]=new Spot(0,y,blackPieces[y]);
			boxes[6][y]=new Spot(1,y,new Pawn(false));
			}
		// initialize remaining boxes without any piece 
        for (int i = 2; i < 6; i++) { 
            for (int j = 0; j < 8; j++) { 
                boxes[i][j] = new Spot(i, j, null); 
            } 
        } 
	}
}
