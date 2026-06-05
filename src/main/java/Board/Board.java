package Board;

public class Board {
	
	private Square [][] field; 
	
	
	public Board() {
		field = new Square[8][8];
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
}
