package checkers.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class TestWin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWin window = new TestWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 480, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameBoard gameBoard = new GameBoard();
		frame.getContentPane().add(gameBoard, BorderLayout.CENTER);
		frame.pack();
	}

}
