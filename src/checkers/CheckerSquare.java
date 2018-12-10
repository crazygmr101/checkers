package checkers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckerSquare extends Canvas implements CheckersConstants {
	private static final long serialVersionUID = -1264723205310791767L;

	public boolean isBlack = false;
	private GameWindow frame;
	boolean isMouseIn = false;
	private char checker;
	private boolean highlighted;
	public int c, r;
	
	public char getChecker() {
		return checker;
	}

	public boolean getHighlighted() {
		return highlighted;
	}
	
	public void setHighlighted(boolean h) {
		this.highlighted = h;
		repaint();
	}
	
	public void setChecker(char checker) {
		highlighted = false;
		if (checker == this.checker)
			return;
		changed = true;
		this.checker = checker;
		repaint();
	}

	private boolean changed = true;

	public CheckerSquare(GameWindow frame) {
		this.frame = frame;
		addMouseListener(new Handler(frame));
	}

	@Override
	public void paint(Graphics g) {
		//this.g = g;
		if (!changed) return;
		//changed = false;
		//g = this.getGraphics();
		g.setColor(isBlack ? Color.DARK_GRAY : Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (isMouseIn && !isBlack) {
			g.setColor(isBlack ? Color.BLACK : Color.LIGHT_GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if (checker == ' ')
			return;
		if (checker == 'b' || checker == 'B')
			g.setColor(Color.BLACK);
		if (checker == 'w' || checker == 'W')
			g.setColor(Color.RED);
		g.fillOval(10, 10, getWidth() - 21, getHeight() - 21);
		if (checker == 'B' || checker == 'W') {
			g.setColor(Color.YELLOW);
			g.fillOval(15, 15, getWidth() - 31, getHeight() - 31);
		}
		if (highlighted) {
			g.setColor(Color.BLUE);
			g.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
		}
		g.setColor(Color.BLUE);
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		if (c == 1)
			g.drawString(String.valueOf(r), 1, 10);
		if (r == 1)
			g.drawString(String.valueOf(c), 1, 10);
	}

}

class Handler implements MouseListener {

	private GameWindow win;

	public Handler(GameWindow win) { this.win = win; }

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((CheckerSquare) e.getComponent()).isMouseIn = true;
		((CheckerSquare) e.getComponent()).repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((CheckerSquare) e.getComponent()).isMouseIn = false;
		((CheckerSquare) e.getComponent()).repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
