package figures;

import java.util.ArrayList;

import Board.*;

public class Pawn extends Figure {
	
	public Pawn(String name, boolean isWhite, Square currentSquare) {
		super(name,isWhite, currentSquare);
	}

	
	
	private boolean isBlockedPath(Square square) {
		return square == null || !square.isEmpty();
	}
	
	
	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		ArrayList<Square> validMoves = new ArrayList<>();
		int forward = this.isWhite() ? -1 : 1;
		
		
		int [][] directions = getDirections();
		
		for(int[] dir : directions) {
			
			int x = this.getCurrentSquare().getX();
			int y = this.getCurrentSquare().getY();
			
			int targetX = x + dir[0];
			int targetY = y + (forward * dir[1]);
			
			Square targetSquare = board.getSquare(targetX, targetY);
			
			if(targetSquare == null) {
				continue;
		}
			
			switch(dir[0]) {
			
				case 0 : {
						switch(dir[1]) {
							case  1 : {
								if(targetSquare.isEmpty())
									validMoves.add(targetSquare);
									
								break;
							}
							
							case 2 : {
								Square inBetween = board.getSquare(targetX, y + (forward * 1));
								if(targetSquare.isEmpty() && !isBlockedPath(inBetween)) 
									validMoves.add(targetSquare);
								break;
								
								}
							}
							break;
						}
				case  1:
				case -1: {
						if(!targetSquare.isEmpty() && targetSquare.getFigure().isEnemy(this))
							validMoves.add(targetSquare);
							
					break;	
				}
				
			}
			
		}
		
		
		return validMoves;
	}


	@Override
	protected int[][] getDirections() {		
		if(hasMoved) {
			return new int[][] {{0,1},{1,1} ,{-1,1}};
		} else {
			return new int[][] {{0,1},{1,1} ,{-1,1},{0,2}};
		}
	}
	
	
}