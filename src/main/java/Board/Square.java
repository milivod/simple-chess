package Board;

import java.io.Serializable;

import figures.*;

public class Square implements Serializable{

	private int x,y;
	
	private Figure figure;
	
	public Square(int x,int y) {
		this.x = x;
		this.y = y;
		figure = null;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setFigure(Figure f) {
		figure = f;
	}
	public void removeFigure() {
		figure = null;
	}
	public boolean isEmpty() {
		return figure == null;
	}
	public Figure getFigure() {
		return figure;
	}
}
