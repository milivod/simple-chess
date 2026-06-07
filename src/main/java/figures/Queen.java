package figures;

import Board.Square;

public class Queen extends SlidingFigure{

	public Queen(String name, boolean isWhite, Square currentSquare) {
		super(name, isWhite, currentSquare);
		
	}

	@Override
	protected int[][] getDirections() {
		return new int [][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1},		//straight
            			  	{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};	//diagonal
	}



	
}
