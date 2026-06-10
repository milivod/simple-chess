package figures;

import java.io.Serializable;
import java.util.ArrayList;

import Board.*;

public abstract class Figure implements Serializable {
	
	public boolean hasMoved = false;
	private boolean isWhite;
	protected Square currentSquare;
	private String unicodeSymbol;
	
	public Figure(String unicodeSymbol,boolean isWhite,Square currentSquare) {
		this.unicodeSymbol = unicodeSymbol;
		this.currentSquare = currentSquare;
		this.isWhite = isWhite;
	}
	public Square getCurrentSquare() {
		return currentSquare;
	}
	
	public void setNewCurrentSquare(Square newSquare) {
		currentSquare = newSquare;
		currentSquare.setFigure(this);
	}
	
	public String getSymbol() {
		return unicodeSymbol;
	}
	public boolean isWhite() {
		return isWhite;
	}
	
	public boolean isEnemy(Figure f) {
		return this.isWhite != f.isWhite;
	}
	
	public abstract ArrayList<Square> getValidMoves(Board board);
	protected abstract int[][] getDirections();
	
	
}
