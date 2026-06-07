package figures;

import java.util.ArrayList;

import Board.Board;
import Board.Square;

public class King extends Figure{

	private final int MAX_STEPS = 1;
	
	public King(String name, boolean isWhite,Square currentSquare) {
		super(name, isWhite, currentSquare);
	
	}
		
	protected int [][] getDirections() {
		return new int [][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1},		//straight
		  					{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};	//diagonal

	}

	private boolean attacksOtherKing(Square otherSquare) {
		
		if(otherSquare.getFigure() instanceof King) {
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		int [][] directions = getDirections();
		int currentX = this.currentSquare.getX();
		int currentY = this.currentSquare.getY();
		
		
		for(int[] dir : directions) {
			
				int targetX = currentX + (MAX_STEPS * dir[0]);
				int targetY = currentY + (MAX_STEPS * dir[1]);
				
				Square targetSquare = board.getSquare(targetX, targetY);
				
				if(targetSquare == null) {
					continue;
				}
				
				if(targetSquare.isEmpty()) {
					validMoves.add(targetSquare);
					
				
				} else {
					if(targetSquare.getFigure().isWhite() != this.isWhite() && !attacksOtherKing(targetSquare)) {
						validMoves.add(targetSquare);

					}				
				}			
			}
			

		
		return validMoves;
	}
	
	
}

	
