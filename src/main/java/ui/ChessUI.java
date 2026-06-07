package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Board.*;
import figures.Figure;
import game.Game;

public class ChessUI extends JFrame {

	    
	private final Color HIGHLIGHT_COLOR = new Color(130, 195, 255);
	private final Color ENEMY_COLOR = new Color(255, 110, 110);
	private Game game;
	private JButton[][] buttonGrid;
	private Figure selectedFigure = null;
	
	public ChessUI(Game game) {
		this.game = game;
		this.buttonGrid = new JButton[8][8];
		
		setTitle("Simple Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		
		setLayout(new GridLayout(8, 8));
		
		initializeContent();
		setLocationRelativeTo(null); 
		setVisible(true);
	}
	
	public void start() {
		game.startChess();
		updateUI(); 
	}
	
	private void initializeContent() {
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				JButton button = new JButton();
				button.setFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.PLAIN, 50));
			
				buttonGrid[x][y] = button;
				add(button);
				
				setSquareColor(x, y);
				
				final int finalX = x;
				final int finalY = y;
				
				button.addMouseListener(new MouseAdapter() {
					
				
					
					
					@Override
					public void mouseClicked(MouseEvent e) {
						onSquareClick(finalX, finalY);
					}
				});
			}
		}
	}
		
	private void onSquareClick(int x, int y) {
		Square clickedSquare = game.getBoard().getSquare(x, y);
		
		if (selectedFigure == null) {
			if (!clickedSquare.isEmpty()) {
				
				if(clickedSquare.getFigure().isWhite() != game.isWhiteTurn()) {
					return;
				}
				
				selectNewFigure(clickedSquare);
				
				
			}
		} else { 
		    if (!clickedSquare.isEmpty() && clickedSquare.getFigure().isWhite() == game.isWhiteTurn()) {
		        selectNewFigure(clickedSquare);
		        return; 
		    }
		    
		    ArrayList<Square> validMoves = selectedFigure.getValidMoves(game.getBoard());
		    
		    if (validMoves.contains(clickedSquare)) {
		        game.moveFigure(selectedFigure, clickedSquare);
		    }
		    
		    selectedFigure = null; 
		    updateUI();
		}
	}
	private void selectNewFigure(Square square) {
	    selectedFigure = square.getFigure();
	    ArrayList<Square> validMoves = selectedFigure.getValidMoves(game.getBoard());
	    highlightValidMoves(validMoves);
	}

	private void updateUI() {
		for (int y= 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) { 
				
				Square square = game.getBoard().getSquare(x, y);
				JButton button = buttonGrid[x][y];

				if (square != null && !square.isEmpty()) {
					button.setText(square.getFigure().getSymbol());
				} else {
					button.setText("");
				}
				setSquareColor(x, y);
			}
		}
	}
	
	private void setSquareColor(int x, int y) {
		if ((x + y) % 2 == 0) {
			buttonGrid[x][y].setBackground(new Color(240, 217, 181)); 
		} else {
			buttonGrid[x][y].setBackground(new Color(181, 136, 99));  
		}
	}


	private void highlightSquare(int x, int y, Color color) {
		buttonGrid[x][y].setBackground(color);
	}

	public void highlightValidMoves(ArrayList<Square> validMoves) {
	    updateUI();
	    for (Square move : validMoves) {
	        if (move.getFigure() != null) {
	            highlightSquare(move.getX(), move.getY(), ENEMY_COLOR);
	        } else {
	            highlightSquare(move.getX(), move.getY(), HIGHLIGHT_COLOR);
	        }
	    }
	}
	
	public static void main(String[] args) {
		
		Game game = new Game(new Board()); 
		ChessUI ui = new ChessUI(game);
		ui.start();
	}
}