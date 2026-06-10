package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;

import Board.*;
import figures.Figure;
import game.Game;

public class ChessUI extends JFrame {

	private final Color backgroundColor = new Color(38, 37, 34);
	private final Color HIGHLIGHT_COLOR = new Color(130, 195, 255);
	private final Color ENEMY_COLOR = new Color(255, 110, 110);
	private Game game;
	private JButton[][] buttonGrid;
	private Figure selectedFigure = null;
	private JButton undoButton;

	
	public ChessUI(Game game) {
		this.game = game;
		this.buttonGrid = new JButton[8][8];
		
		setTitle("Simple Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1050, 800);
		
		getContentPane().setBackground(backgroundColor);
		
		setLayout(new BorderLayout(10, 0));
	
		
		JPanel boardPanel = new JPanel(new GridLayout(8,8));
		
		initializeContent(boardPanel);
		add(boardPanel, BorderLayout.CENTER);
		
		JPanel sidePanel = createSidePanel();
		add(sidePanel, BorderLayout.EAST);
		
		setLocationRelativeTo(null); 
		
		setVisible(true);
	}
	
	public void start() {
		game.startChess();
		updateUI(); 
	}
	
	public JPanel createSidePanel() {
		JPanel sidePanel = new JPanel(new GridLayout(2,1,10,10));
		sidePanel.setPreferredSize(new Dimension(200,800));
		sidePanel.setBackground(backgroundColor);

		undoButton = new JButton("◀◀");
		

		undoButton.setFont(new Font("Segoe UI", java.awt.Font.BOLD, 20));
		
		
	    undoButton.addActionListener(e -> {
			game.undo();
			updateUI();
			selectedFigure = null;
		});
		
		
		
		sidePanel.add(undoButton);
		return sidePanel;
	}
	
	
	private void initializeContent(JPanel boardPanel) {		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				JButton button = new JButton();
				button.setFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.PLAIN, 50));
			
				buttonGrid[x][y] = button;
				boardPanel.add(button);
				
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
		if (undoButton != null) {
	        undoButton.setEnabled(game.canUndo());
	        
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