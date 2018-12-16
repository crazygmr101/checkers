package checkers.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import checkers.CheckersConstants;

/**
 * Represents a checker square
 * 
 * @author Daniel Nash
 */
public class CheckerSquare extends Canvas implements CheckersConstants {
	private static final long serialVersionUID = -1264723205310791767L;

	BufferedImage bg, bg2 = null;

	/**
	 * whether the square is a black square or not
	 */
	public boolean isBlack = false;
	boolean isMouseIn = false;
	private char checker;
	private boolean highlighted;
	
	/**
	 * Square's column
	 */
	public int c;
	
	/**
	 * Square's row
	 */
	public int r;

	/**
	 * Gets the checker on the board
	 * @return checker
	 * 
	 * @see checkers.CheckersConstants
	 */
	public char getChecker() {
		return checker;
	}

	/**
	 * Sets the checker on the square
	 * @param checker
	 */
	public void setChecker(char checker) {
		highlighted = false;
		if (checker == this.checker)
			return;
		changed = true;
		this.checker = checker;
		repaint();
	}
	
	private boolean changed = true;

	/**
	 * @param frame - frame the game is in
	 * @param isBlack - whether the square is black or not
	 * @see checkers.CheckersConstants
	 */
	public CheckerSquare(GameWindow frame, boolean isBlack) {
		this.isBlack = isBlack;
		//bg = ImageIO.read(new File("src/rc/assets/" + (isBlack ? "black" : "white" ) + "_square_100x.png"));
		//RescaleOp op = new RescaleOp(0.9f, 0, null);
		//bg2 = op.filter(bg, null);
		addMouseListener(new Handler(frame));
	}

	/**
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		//this.g = g;
		if (!changed) return;
		//changed = false;
		//g = this.getGraphics();
		g.setColor(isBlack ? Color.DARK_GRAY : Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		//g.drawImage(bg, 0, 0, getWidth(), getHeight(), 0, 0, bg.getWidth(), bg.getHeight(), null);

		if (isMouseIn && !isBlack) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			//g.drawImage(bg2, 0, 0, getWidth(), getHeight(), 0, 0, bg.getWidth(), bg.getHeight(), null);
		}
		if (checker != ' ' && checker != 'O') {
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
		}

		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 14));
		//g.setFont(g.getFont().deriveFont(Font.BOLD).deriveFont(14f));
		if (r == 1)
			g.drawString(String.valueOf(c), 4, 14);
		if (c == 1)
			g.drawString(String.valueOf(r), 4, 14);
	}

}

class Handler implements MouseListener {

	public Handler(@SuppressWarnings("unused") GameWindow win) { }

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
