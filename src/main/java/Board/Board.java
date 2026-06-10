package Board;

import java.io.Serializable;
import java.util.ArrayList;

import figures.Bishop;
import figures.Figure;
import figures.King;
import figures.Knight;
import figures.Pawn;
import figures.Queen;
import figures.Rook;

public class Board implements Serializable {
	
	private Square [][] field; 
	private ArrayList<Figure> whiteFigures;
	private ArrayList<Figure> blackFigures;
	private ArrayList<King> kings;
	
	public Board() {
		field = new Square[8][8];
		this.whiteFigures = new ArrayList<>();
        this.blackFigures = new ArrayList<>();
        this.kings = new ArrayList<>();
		setupField();
	}
	public void moveFigure(Figure f, Square targetSquare) {			
		if(!targetSquare.isEmpty()) {
			removeFigure(targetSquare);
		}
		f.getCurrentSquare().setFigure(null);
		f.setNewCurrentSquare(targetSquare);		
		f.hasMoved =true;
		
	}
	public ArrayList<Figure> findEnemyFigures(boolean checkWhite){
		ArrayList<Figure> enemyFigures = checkWhite ? blackFigures : whiteFigures;
		return enemyFigures;
	}
	
	public King getKing(boolean isWhite) {
		for(King k : kings) {
			if(k.isWhite() == isWhite) {
				return k;
			}		
		}
		return null;
	}
	
	private void removeFigure(Square targetSquare) {
		Figure f = targetSquare.getFigure();
		
		if(f.isWhite()) {
			whiteFigures.remove(f);
		} else {
			blackFigures.remove(f);
		}
	}
	
	private void setupField() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				field[x][y] = new Square(x, y);
			}
		}
	}
	public void setupPieces() {
		for (int i = 0; i < 8; i++) {
			whiteFigures.add(new Pawn("\u2659", true, getSquare(i, 6)));
			blackFigures.add(new Pawn("\u265F",false, getSquare(i, 1)));
		}
		
		whiteFigures.add(new Rook("\u2656",true, getSquare(0, 7)));
		whiteFigures.add(new Rook("\u2656",true, getSquare(7, 7)));
			
		blackFigures.add(new Rook("\u265C",false, getSquare(0, 0)));
		blackFigures.add(new Rook("\u265C",false, getSquare(7, 0)));
		
		whiteFigures.add(new Bishop("\u2657",true,getSquare(2, 7)));
		whiteFigures.add(new Bishop("\u2657",true,getSquare(5, 7)));
		
		blackFigures.add(new Bishop("\u265D",false,getSquare(2, 0)));
		blackFigures.add(new Bishop("\u265D",false,getSquare(5, 0)));
		
		whiteFigures.add(new Knight("\u2658",true,getSquare(1, 7)));
		whiteFigures.add(new Knight("\u2658",true,getSquare(6, 7)));
		
		blackFigures.add(new Knight("\u265E",false,getSquare(1, 0)));
		blackFigures.add(new Knight("\u265E",false,getSquare(6, 0)));
		
		whiteFigures.add(new Queen("\u2655",true,getSquare(3, 7)));
		blackFigures.add(new Queen("\u265B",false,getSquare(3, 0)));
		King whiteKing = new King("\u2654",true,getSquare(4, 7));
		King blackKing = new King("\u265A",false,getSquare(4, 0));
		kings.add(whiteKing);
		kings.add(blackKing);		
		whiteFigures.add(whiteKing);
		blackFigures.add(blackKing);
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
	
	public Square[][] getField(){
		return field;
	}
	
	
	public boolean isValidCoordinate(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;	
	}
	
	public Square getSquare(int x, int y) {
		if(isValidCoordinate(x,y)) {
			return field[x][y];
		}
		return null;
	}
	
//	public boolean KingIsTargeted(Square targetedSquare, King k) {
//		ArrayList<Figure> enemies = findEnemyFigures(targetedSquare.getFigure().isWhite());
//		ArrayList<Square> allValidMoves = getAllValidMoves(enemies);
//		
//		if(allValidMoves.contains(targetedSquare)) {
//			k.isInCheck = true;
//			return true;
//		
//		} else {
//			k.isInCheck = false;
//		}
//		return false;
//		
//	}
//	private ArrayList<Square> getAllValidMoves(ArrayList<Figure> enemies){
//		ArrayList<Square> allValidMoves = new ArrayList<>();
//		for(Figure enemy : enemies) {
//			allValidMoves.addAll(enemy.getValidMoves(this));
//		}
//		return allValidMoves;
//	}
//	public Square getCheckedKingsSquare() {
//		for(King k : kings) {
//			if(k.isInCheck) {
//				return k.getCurrentSquare();
//			}
//		}
//		return null;
//	}
}
