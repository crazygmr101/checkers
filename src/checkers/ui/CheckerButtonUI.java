package checkers.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

class CheckerButtonUI extends BasicButtonUI implements java.io.Serializable, MouseListener {

	private static final long serialVersionUID = 1190253732552477616L;

	private final static CheckerButtonUI m_buttonUI = new CheckerButtonUI();

	public MouseListener aux_listener;

	protected boolean isHover = false;
	protected boolean isClick = false;

	public static ComponentUI createUI(JComponent c) {
		return m_buttonUI;
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);

		c.addMouseListener(this);
	}

	@Override
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);
		c.removeMouseListener(this);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		Dimension d = b.getSize();
		
		if (b.getText().indexOf("-") != -1) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, b.getWidth(), b.getHeight());
			return;
		}

		g.setFont(c.getFont());
		FontMetrics fm = g.getFontMetrics();

		g.setColor(b.getForeground());
		String caption = b.getText();
		int x = 5; // (d.width - fm.stringWidth(caption)) / 2;
		int y = (d.height + fm.getAscent()) / 2;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, b.getWidth(), b.getHeight());
		if (!isHover) {
			if (isClick) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(2, 2, b.getWidth() - 3, b.getHeight() - 3);
			}
			g.setColor(Color.BLACK);
			g.drawRect(2, 2, b.getWidth() - 3, b.getHeight() - 3);
			g.setColor(Color.BLACK);
			g.drawString(caption, x - (isHover ? 1 : 0), y - (isHover ? 1 : 0));
		} else if (b.getText().indexOf("-") == -1) {
			if (isClick) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(1, 1, b.getWidth() - 3, b.getHeight() - 3);
			}
			// if hovering, offset button
			g.setColor(Color.BLACK);
			g.drawRect(1, 1, b.getWidth() - 3, b.getHeight() - 3);
			// make a shadow
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(b.getWidth() - 1, 2, b.getWidth() - 1, b.getHeight() - 1);
			g.drawLine(2, b.getHeight() - 1, b.getWidth() - 1, b.getHeight() - 1);
			g.setColor(Color.BLACK);
			g.drawString(caption, x - (isHover ? 1 : 0), y - (isHover ? 1 : 0));
		} else {
			g.setColor(Color.BLACK);
			g.drawRect(2, 2, b.getWidth() - 3, b.getHeight() - 3);
			g.setColor(Color.BLACK);
			g.drawString(caption, x, y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		aux_listener.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		aux_listener.mousePressed(e);
		isClick = true;
		JComponent c = (JComponent) e.getComponent();
		c.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		aux_listener.mouseReleased(e);
		isClick = false;
		JComponent c = (JComponent) e.getComponent();
		c.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		aux_listener.mouseEntered(e);
		isHover = true;
		isClick = (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK);
		JComponent c = (JComponent) e.getComponent();
		c.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		aux_listener.mouseExited(e);
		JComponent c = (JComponent) e.getComponent();
		isHover = false;
		isClick = false;
		c.repaint();
	}

}
