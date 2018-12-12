/**
 * DO NOT MODIFY THIS FILE
 * This file is currently being worked on in WindowBuilder, and
 * may not parse correctly if modified
 */
package checkers;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * Application window
 * 
 * @author Daniel Nash
 *
 */
public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7384928133320272861L;
	JPanel contentPane;
	private JPanel movePanel;
	private JPanel boardPanel;
	private JLabel lblStatus;
	private JScrollPane scrollPanel;
	volatile Board board;
	volatile boolean cont = true;
	volatile static GameWindow frame;
	volatile boolean changed = true;
	volatile CheckerSquare[][] cs = new CheckerSquare[8][8];
	volatile int choice = 0;
	volatile JButton moves[] = new JButton[10];
	volatile ArrayList<Move> movesList;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @param b - board for the window
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public static void main(@SuppressWarnings("unused") String[] args, Board b) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new GameWindow(b);
					colorBoard(frame);
					setSizes(frame);
					frame.setVisible(true);
					frame.board = new Board();
					return;
					// frame.update();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sets the sizes of the window components
	 * 
	 * @param frame
	 */
	private static void setSizes(GameWindow frame) {
		frame.setSize(1000, 500);
		for (Component cnv : frame.movePanel.getComponents()) {
			int height = cnv.getHeight();
			if (cnv instanceof Canvas)
				((Canvas) cnv).setSize(500, height);
			cnv.setPreferredSize(new Dimension(500, height));
		}
		frame.pack();
	}

	/**
	 * Colors the squares on the board
	 * 
	 * @param frame
	 */
	private static void colorBoard(GameWindow frame) {
		Component[] components = frame.boardPanel.getComponents();
		for (Component cmp : components) {
			if (!(cmp instanceof Canvas))
				continue;
			Canvas cnv = (Canvas) cmp;
			int s = Integer.parseInt(cnv.getName());
			int r = s % 10;
			int c = (s - r) / 10;
			if ((r + c) % 2 == 0) {
				cnv.setBackground(Color.BLACK);
			} else {
				cnv.setBackground(Color.WHITE);
			}
		}
	}

	/**
	 * Create the frame.
	 * @param b - board
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public GameWindow(Board b) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.board = b;
		setTitle("Checkers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 379);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblStatus = new JLabel("Checkers Game");
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblStatus, BorderLayout.NORTH);

		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(500, 500));
		boardPanel.setSize(new Dimension(500, 500));
		boardPanel.setMaximumSize(new Dimension(500, 500));
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0));

		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++) {
				cs[r][c] = new CheckerSquare(frame, ((r + c) % 2 == 0));
				cs[r][c].isBlack = ((r + c) % 2 == 0);
				cs[r][c].setName(String.valueOf(r) + String.valueOf(c));
				boardPanel.add(cs[r][c]);
				cs[r][c].setChecker(board.board[r + 1][c + 1]);
				cs[r][c].c = c + 1;
				cs[r][c].r = r + 1;
			}

		movePanel = new JPanel();
		movePanel.setMinimumSize(new Dimension(250, 10));
		scrollPanel = new JScrollPane(movePanel);
		contentPane.add(scrollPanel, BorderLayout.EAST);
		movePanel.setLayout(new GridLayout(10, 1, 0, 0));
		movePanel.setName("movePnl");

		for (int i = 0; i < moves.length; i++) {
			moves[i] = new JButton(String.valueOf("-----"));
			moves[i].setName(String.valueOf(i));
			moves[i].addMouseListener(new MoveHandler(this));
			movePanel.add(moves[i]);
		}
	}

	JPanel getMovePanel() {
		return movePanel;
	}

	JPanel getBoardPanel() {
		return boardPanel;
	}

	JLabel getLblStatus() {
		return lblStatus;
	}

	void updateCheckers() {
		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++)
				cs[r][c].setChecker(board.board[r + 1][c + 1]);

	}
}

class MoveHandler implements MouseListener {

	private GameWindow win;

	public MoveHandler(GameWindow gameWindow) {
		win = gameWindow;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().indexOf("-") != -1)
			return;
		win.choice = Integer.parseInt(e.getComponent().getName());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
