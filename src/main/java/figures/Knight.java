package figures;

import java.util.ArrayList;

import Board.Board;
import Board.Square;

public class Knight extends Figure {

	public Knight(String name, boolean isWhite, Square currentSquare) {
		super(name, isWhite, currentSquare);
		
	}

	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		
		int x = this.currentSquare.getX();
		int y = this.currentSquare.getY();
		
		int[][] knightMoves = {
		        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
		        {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
		    };
		
		for(int[] moves : knightMoves) {
			int targetX = x + moves[0];
			int targetY = y + moves[1];
			
			
			
			Square target = board.getSquare(targetX, targetY);
			
			if(target != null) {
				if(target.getFigure().isWhite() != this.isWhite()) {
					validMoves.add(target);
				}
			}
		}
		return validMoves;
		

	}
	
	

}
