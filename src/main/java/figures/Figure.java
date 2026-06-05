package figures;

import java.util.ArrayList;

import Board.*;

public abstract class Figure {
	
	protected boolean alreadyMoved = false;
	private boolean isWhite;
	protected Square currentSquare;
	private String name;
	
	public Figure(String name,boolean isWhite) {
		this.name = name;
		currentSquare = null;
		this.isWhite = isWhite;
	}
	
	public void setSquare(Square s) {
		currentSquare = s;
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
