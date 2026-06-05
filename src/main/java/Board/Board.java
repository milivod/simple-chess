package Board;

public class Board {
	
	private Square [][] squares; 
	private static final Board INSTANCE = new Board();
	private Board() {
		squares = new Square[8][8];
	}
	
	public Board getInstance() {
		return INSTANCE;
	}
	
	public Square[][] getSquares(){
		return squares;
	}
	
	public boolean isValidCoordinate(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;	
	}
	
	public Square getSquare(int x, int y) {
		if(isValidCoordinate(x,y)) {
			return squares[x][y];
		}
		return null;
	}
}
