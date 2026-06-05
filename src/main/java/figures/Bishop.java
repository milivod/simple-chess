package figures;

import java.util.ArrayList;

import Board.Board;
import Board.Square;

public class Bishop extends Figure{

	public Bishop(String name, boolean isWhite) {
		super(name, isWhite);
		
	}

	@Override
	public ArrayList<Square> getValidMoves(Board board) {
		
		int [][] directions = {{1,1},{-1,-1},{-1,1},{1,-1}};
		
		
		
		
	}

}
