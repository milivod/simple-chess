package figures;

import java.util.ArrayList;

import Board.*;

public class Pawn extends Figure {
	
	public Pawn(String name, boolean isWhite, Square currentSquare) {
		super(name,isWhite, currentSquare);
	}

	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		int forward = this.isWhite() ? -1 : 1;
		
		int x = this.currentSquare.getX();
		int y = this.currentSquare.getY() + forward;
		
		Square oneStep = board.getSquare(x, y);
		Square twoSteps = board.getSquare(x, (y + forward));
		
		int right = x +1, left = x -1;
		
		Square rightStep = board.getSquare(right, y);
		Square leftStep = board.getSquare(left, y);
		
		if(oneStep != null && oneStep.isEmpty()) {
			validMoves.add(oneStep);
		}
		if(twoSteps != null && oneStep.isEmpty() && twoSteps.isEmpty() && !alreadyMoved) {
			validMoves.add(twoSteps);
		}
		if(rightStep != null && !rightStep.isEmpty() && rightStep.getFigure().isWhite() != this.isWhite()) {
			validMoves.add(rightStep);
		}
		if(leftStep != null && !leftStep.isEmpty()&& leftStep.getFigure().isWhite() != this.isWhite()) {
			validMoves.add(leftStep);
		}
		
		return validMoves;
	}
}