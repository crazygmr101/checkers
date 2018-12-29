package checkers.ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import checkers.ChessPiece;
import checkers.Constants;

@SuppressWarnings("serial")
public class GameBoard extends Canvas implements Constants {
	
	Square[][] squares;
	char[] s1 = "PPPPPPPP".toCharArray();
	char[] s2 = "RNBKQBNR".toCharArray();
	char[] s3 = "        ".toCharArray();
	final char[][] board = {s2, s1, s3, s3, s3, s3, s1, s2};
	
	public GameBoard() {
		// enable anti-aliasing
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		squares = new Square[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square();
				squares[i][j].color = ((i + j) % 2 == 0 ? BLACK : WHITE);
				squares[i][j].piece = new ChessPiece();
				squares[i][j].piece.type = board[j][i];
				if (j == 0 || j == 1) {
					squares[i][j].piece.color = BLACK;
				}
				if (j == 7 || j == 6) {
					squares[i][j].piece.color = WHITE;
				}
			}
	}
	
	/**
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				squares[i][j].paint(g, 60 * i, 60 * j);
			}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(480, 480);
		
	}
}
