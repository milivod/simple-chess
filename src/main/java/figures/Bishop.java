package figures;

import Board.Square;

public class Bishop extends SlidingFigure{

	public Bishop(String name, boolean isWhite,Square currentSquare) {
		super(name, isWhite, currentSquare);
		
	}

	@Override
	protected int[][] getDirections() {
		return new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	}

	
	
	
}
