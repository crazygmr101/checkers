package checkers.ui;

import java.awt.Color;
import java.awt.Graphics;

import checkers.ChessPiece;
import checkers.Constants;

public class Square implements Constants {
	public boolean black;
	public int color;
	public ChessPiece piece;
	
	public void paint(Graphics g, int x, int y) {
		if (color == BLACK)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.RED);
		g.fillRect(x, y, 60, 60);
		if (piece != null)
			piece.draw(g, x, y);
	}
}
