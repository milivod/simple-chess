package game;

import java.util.ArrayList;

import Board.Board;
import Board.Square;
import figures.*;

public class Game {
	
	private Board board;
	private ArrayList<Figure> whiteFigures;
	private ArrayList<Figure> blackFigures;
	
	public Game(Board board) {
		this.board = board;
		this.whiteFigures = new ArrayList<>();
        this.blackFigures = new ArrayList<>();
	
	}
	
	private void createFigures() {
		for (int i = 0; i < 8; i++) {
			whiteFigures.add(new Pawn("P", true, board.getSquare(i, 1)));
			blackFigures.add(new Pawn("P",false, board.getSquare(i, 6)));
		}
		
		whiteFigures.add(new Rook("R",true, board.getSquare(0, 7)));
		whiteFigures.add(new Rook("R",true, board.getSquare(7, 7)));
			
		blackFigures.add(new Rook("R",false, board.getSquare(0, 0)));
		blackFigures.add(new Rook("R",false, board.getSquare(7, 0)));
		
		whiteFigures.add(new Bishop("B",true,board.getSquare(2, 7)));
		whiteFigures.add(new Bishop("B",true,board.getSquare(5, 7)));
		
		blackFigures.add(new Bishop("B",false,board.getSquare(2, 0)));
		blackFigures.add(new Bishop("B",false,board.getSquare(5, 0)));
		
		whiteFigures.add(new Knight("K",true,board.getSquare(1, 7)));
		whiteFigures.add(new Knight("K",true,board.getSquare(6, 7)));
		
		blackFigures.add(new Knight("K",false,board.getSquare(1, 0)));
		blackFigures.add(new Knight("K",false,board.getSquare(6, 0)));
		
		whiteFigures.add(new Queen("Q",true,board.getSquare(3, 7)));
		blackFigures.add(new Queen("Q",false,board.getSquare(3, 0)));
		
		whiteFigures.add(new King("King",true,board.getSquare(4, 7)));
		blackFigures.add(new King("King",false,board.getSquare(4, 0)));
		
	}
	
	private void setFigures() {
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
	}
	
}
