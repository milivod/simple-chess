package game;

import java.util.ArrayList;

import Board.Board;
import Board.Square;
import figures.*;

public class Game {
	
	private Board board;
	private ArrayList<Figure> whiteFigures;
	private ArrayList<Figure> blackFigures;
	private boolean isWhiteTurn;
	
	public Game(Board board) {
		this.board = board;
		this.whiteFigures = new ArrayList<>();
        this.blackFigures = new ArrayList<>();
        this.isWhiteTurn = true;
	}
	
	public void switchTurn() {
		isWhiteTurn = !isWhiteTurn; 
	}
	
	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	
	public void startChess() {
		setupPieces();
		
	}
	public Board getBoard() {
		return board;
	}
	
	private void setupPieces() {
		for (int i = 0; i < 8; i++) {
			whiteFigures.add(new Pawn("\u2659", true, board.getSquare(i, 6)));
			blackFigures.add(new Pawn("\u265F",false, board.getSquare(i, 1)));
		}
		
		whiteFigures.add(new Rook("\u2656",true, board.getSquare(0, 7)));
		whiteFigures.add(new Rook("\u2656",true, board.getSquare(7, 7)));
			
		blackFigures.add(new Rook("\u265C",false, board.getSquare(0, 0)));
		blackFigures.add(new Rook("\u265C",false, board.getSquare(7, 0)));
		
		whiteFigures.add(new Bishop("\u2657",true,board.getSquare(2, 7)));
		whiteFigures.add(new Bishop("\u2657",true,board.getSquare(5, 7)));
		
		blackFigures.add(new Bishop("\u265D",false,board.getSquare(2, 0)));
		blackFigures.add(new Bishop("\u265D",false,board.getSquare(5, 0)));
		
		whiteFigures.add(new Knight("\u2658",true,board.getSquare(1, 7)));
		whiteFigures.add(new Knight("\u2658",true,board.getSquare(6, 7)));
		
		blackFigures.add(new Knight("\u265E",false,board.getSquare(1, 0)));
		blackFigures.add(new Knight("\u265E",false,board.getSquare(6, 0)));
		
		whiteFigures.add(new Queen("\u2655",true,board.getSquare(3, 7)));
		blackFigures.add(new Queen("\u265B",false,board.getSquare(3, 0)));
		
		whiteFigures.add(new King("\u2654",true,board.getSquare(4, 7)));
		blackFigures.add(new King("\u265A",false,board.getSquare(4, 0)));
		
		placeFigures();
	}
	
	private void placeFigures() {
		for(Figure f : whiteFigures) {
			f.getCurrentSquare().setFigure(f);
		}
		for (Figure f : blackFigures) {
			f.getCurrentSquare().setFigure(f);
		}
	}
	private void removeFigure(Square targetSquare) {
		Figure f = targetSquare.getFigure();
		
		if(f.isWhite()) {
			whiteFigures.remove(f);
		} else {
			blackFigures.remove(f);
		}
	}
	
	public void moveFigure(Figure f, Square targetSquare) {			
		if(!targetSquare.isEmpty()) {
			removeFigure(targetSquare);
		}
		f.getCurrentSquare().setFigure(null);
		f.setNewCurrentSquare(targetSquare);		
		switchTurn();
		f.moved();
	}
	
}
