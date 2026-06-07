package figures;

import java.util.ArrayList;

import Board.Board;
import Board.Square;

public abstract class SlidingFigure extends Figure {
	
	public SlidingFigure(String name, boolean isWhite,Square currentSquare) {
		super(name,isWhite, currentSquare);
	}
	
	
	
	@Override
    public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		
		int [][] directions = getDirections();
		int currentX = this.currentSquare.getX();
		int currentY = this.currentSquare.getY();
		int steps = 1;
		
		for(int[] dir : directions) {
			
			
			while(true) {
				int targetX = currentX + (steps * dir[0]);
				int targetY = currentY + (steps * dir[1]);
				
				Square targetSquare = board.getSquare(targetX, targetY);
				
				if(targetSquare == null) {
					break;
				}
				
				if(targetSquare.isEmpty()) {
					validMoves.add(targetSquare);
					steps++;
				
				} else {
					if(targetSquare.getFigure().isWhite() != this.isWhite()) {
						validMoves.add(targetSquare);

					}
					break;
				}
					
			}
			
		}
		
		return validMoves;
	}
}
