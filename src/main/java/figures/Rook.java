package figures;

import java.util.ArrayList;

import Board.Board;
import Board.Square;

public class Rook extends Figure{

	public Rook(String name, boolean isWhite) {
		super(name, isWhite);
		
	}

	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		
		int currentX = currentSquare.getX();
		int currentY = currentSquare.getY();
		
		int [][] directions = {{1,0}, {0,1} , {-1,0} ,{0,-1} };
		int steps = 1;
		for(int [] dir : directions) {
			
			while(true) {	
				int targetX = currentX + (dir[0] * steps);
				int targetY = currentY + (dir[1] * steps); 
				
				Square target = board.getSquare(targetX, targetY);
				
				if(target == null) {
					break;
				}
				
				if(target.isEmpty()) {
					validMoves.add(target);
					steps++;
				} else {
					if(target.getFigure().isWhite() != this.isWhite()) {
						validMoves.add(target);
						
					}
					break;
				}
				
				
			}
		}
		return validMoves;

	}
}