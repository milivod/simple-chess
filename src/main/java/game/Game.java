package game;

import java.util.ArrayList;

import Board.Board;
import Board.Square;
import figures.*;

public class Game {
	
	private Board board;

	private boolean isWhiteTurn;
	private GameHistory history;
	
	public Game(Board board) {
		this.board = board;
        this.isWhiteTurn = true;
	}
	
	public void switchTurn() {
		isWhiteTurn = !isWhiteTurn; 
	}
	
	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	
	public void startChess() {
		board.setupPieces();
		history = new GameHistory();
		
	}
	public Board getBoard() {
		return board;
	}
	


	
	public void moveFigure(Figure f, Square targetSquare) {			
		Move move = new Move(f.getCurrentSquare(), targetSquare);
		history.addMove(move);
		board.moveFigure(f, targetSquare);
		
		switchTurn();
		
	}	
	
	public void undo() {
		if (canUndo()) {
	        Move lastMove = history.getPrevious();
	        if (lastMove != null) {
	        	
	            Square originalSquare = board.getSquare(lastMove.fromX, lastMove.fromY);
	            Square currentSquare = board.getSquare(lastMove.toX, lastMove.toY);
	            
	           
	            currentSquare.setFigure(null);
	            originalSquare.setFigure(lastMove.selectedFigure);
	            lastMove.selectedFigure.setNewCurrentSquare(originalSquare);
	            
	            
	            if (lastMove.defeatedFigure != null) {
	                currentSquare.setFigure(lastMove.defeatedFigure);
	                lastMove.defeatedFigure.setNewCurrentSquare(currentSquare);
	            }
	            if(undoFirstMoveOfPawn(lastMove)) {
	            	lastMove.selectedFigure.hasMoved = false;
	            }
	            
	            switchTurn();
	        }
	    }        
	}
	public boolean undoFirstMoveOfPawn(Move move) {
		Figure f = move.selectedFigure;
		
		if(f instanceof Pawn) {
			Pawn pawn = (Pawn) f;
			return pawn.isOnStartPosition();
		}
		return false;
	}

	public boolean canUndo() {
	    return history.hasPrevious();
	}
	
	public boolean proofCheck(boolean isWhite) {
		King k = board.getKing(isWhite);
		if(board.KingIsTargeted(k.getCurrentSquare(), k)) {
			k.isInCheck = true;
			return true;
		} else {
			k.isInCheck = false;
		}
		return false;
	}
	public Square getCheckedSquare() {
		return board.getCheckedKingsSquare();
	}
	
	
}
		


	

	

