package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Board.Board;

public class GameHistory {
	
	private ArrayList<Move> history;
	private int currentMoveIndex = -1;
	
	public GameHistory() {
		this.history = new ArrayList<>();
		
	}
	
	public boolean hasPrevious() {
		return currentMoveIndex >= 0;
	}
	
	public boolean hasNext() {
		return currentMoveIndex < history.size() -1;
	}
	public void addMove(Move move) {
		while(history.size() > currentMoveIndex +1) {
			history.remove(history.size() -1);
		}
		history.add(move);
		currentMoveIndex++;
	}
	
	public Move getPrevious() {
		if(!hasPrevious()) {
			return null;
		}
		Move move = history.get(currentMoveIndex);
		currentMoveIndex --;
		return move;
		
	}
	
}
