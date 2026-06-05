package figures;

import java.util.ArrayList;

import Board.*;

public abstract class Figure {
	
	protected boolean alreadyMoved = false;
	private boolean isWhite;
	protected Square currentSquare;
	private String name;
	
	public Figure(String name,boolean isWhite,Square currentSquare) {
		this.name = name;
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
	public void moved() {
		alreadyMoved = true;
	}
	
	public String getName() {
		return name;
	}
	public boolean isWhite() {
		return isWhite;
	}
	
	public abstract ArrayList<Square> getValidMoves(Board board);
}
