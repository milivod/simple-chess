package game;

import Board.Board;
import Board.Square;
import figures.Figure;

public class Move {

	public int fromX;
	public int fromY;
	public int toX;
	public int toY;
	
	private final Square selectedSquare;
	private final Square targetSquare;
	
	public Figure selectedFigure;
	public Figure defeatedFigure;
	
		public Move(Square selectedSquare , Square targetSquare) {
			this.selectedSquare = selectedSquare;
			this.targetSquare = targetSquare;
			initializeMoveInformations();
		}
		
		private void initializeMoveInformations() {
			this.fromX = selectedSquare.getX();
			this.fromY = selectedSquare.getY();
			
			this.toX = targetSquare.getX();
			this.toY = targetSquare.getY();
			
			this.selectedFigure = selectedSquare.getFigure();
			this.defeatedFigure = targetSquare.getFigure();
		}
		

		
}
