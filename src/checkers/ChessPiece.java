package checkers;

import java.awt.Color;
import java.awt.Graphics;

public class ChessPiece implements Constants {

	public int color;
	public char type; // PBRNKQ

	public void draw(Graphics g, int x, int y) {
		g.setColor(color == BLACK ? Color.DARK_GRAY : Color.WHITE);
		switch (type) {
		case 'P':
			g.fillRect(x + 18, y + 48, 24, 8);
			g.fillOval(x + 20, y + 20, 20, 20);
			int[] xar = {x+24, x+36, x+40, x+20};
			int[] yar = {y+32, y+32, y+52, y+52};
			g.fillPolygon(xar, yar, 4);
			break;
		case 'K':
			break;
		case 'Q':
			break;
		case 'R':
			break;
		case 'N':
			break;
		case 'B':
			break;
		default:
			break;
		}
	}
}
