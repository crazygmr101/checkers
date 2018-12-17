/**
 * Im not even going to bother commenting out this code
 * IMO, it need so much work that it'll all be recommented
 * anyways
 */
package checkers;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import checkers.ui.GameWindow;
import checkers.ui.OutputFrame;

/**
 * Main application
 */
public class Checkers implements CheckersConstants {
	static ArrayList<Move> possible;
	static GameWindow gwin;

	/**
	 * Entry point for application
	 * 
	 * @param args
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		Board b = new Board();
		int depth = 7, totalMoves = 1500;
		boolean display = true;
		GameWindow.main(null, b);
		OutputFrame.main(null);
		gwin = GameWindow.frame;

		gwin.choice = -1;
		gwin.getLblStatus().setText("Select a difficulty");
		gwin.moves[0].setText("Easiest");
		gwin.moves[1].setText("Easy");
		gwin.moves[2].setText("Medium");
		gwin.moves[3].setText("Hard");
		while (gwin.choice < 0 || gwin.choice >= 4) {
			// do nothing
		}

		depth = (new int[] { 1, 3, 5, 7 })[gwin.choice];

		System.out.println("Black Moves First");
		System.out.print("Who goes first, Human or Computer (H or C)? ");
		Game g = new Game(b, depth, depth, display);
		gwin.choice = -1;
		gwin.getLblStatus().setText("Who moves first?");
		gwin.moves[0].setText("Human first");
		gwin.moves[1].setText("Computer first");
		gwin.moves[2].setText("-----");
		gwin.moves[3].setText("-----");
		while (gwin.choice != 1 && gwin.choice != 0) {
			// do nothing
		}
		
		gwin.gameStarted = true;
		
		boolean computerFirst = gwin.choice == 1;
		int counter = 0;
		if (computerFirst) { // Computer first (black)
			System.out.println("Computer is Black");
			while (counter < totalMoves) {
				if (b.end_game(CheckersConstants.BLACK)) {
					gwin.getLblStatus().setText("Human Win");
					System.out.println("Human Win");
					break;
				}
				g.comp_move(CheckersConstants.BLACK);
				counter++;
				gwin.board = b;
				gwin.updateCheckers();
				System.out.println(b);

				if (b.end_game(CheckersConstants.WHITE)) {
					System.out.println("Computer Win");
					gwin.getLblStatus().setText("Computer Win");
					break;
				}
				// Find and display available human moves
				possible = b.find_moves(CheckersConstants.WHITE);
				System.out.println("White move");
				printMoves();
				gwin.getLblStatus().setText("Make a move");
				int n = getChoice();
				b.make_move(possible.get(n));
				OutputFrame.frame.add("White:" + possible.get(n));
				counter++;
				// System.out.println(b);
			}
			if (counter == totalMoves)
				System.out.print("TieComputer(" + b.checkerCount(CheckersConstants.BLACK) + ")Human("
						+ b.checkerCount(CheckersConstants.WHITE) + ")  ");
		} else { // Human first (black)
			System.out.println("Computer is White");
			while (counter < totalMoves) {
				if (b.end_game(CheckersConstants.BLACK)) {
					gwin.getLblStatus().setText("Human Win");
					System.out.println("Human Win");
					break;
				}
				// Find and display available human moves
				possible = b.find_moves(CheckersConstants.BLACK);
				gwin.getLblStatus().setText("Make a move");
				System.out.println("Black move");

				printMoves();
				int n = getChoice();
				b.make_move(possible.get(n));
				OutputFrame.frame.add("Black:" + possible.get(n));
				counter++;
				gwin.board = b;
				gwin.updateCheckers();
				System.out.println(b);

				if (b.end_game(CheckersConstants.WHITE)) {
					gwin.getLblStatus().setText("Computer Win");
					System.out.println("Computer Win");
					break;
				}
				g.comp_move(CheckersConstants.WHITE);
				counter++;
				gwin.board = b;
				gwin.updateCheckers();
				System.out.println(b);
			}
			if (counter == totalMoves)
				System.out.print("TieHuman(" + b.checkerCount(CheckersConstants.BLACK) + ")Computer("
						+ b.checkerCount(CheckersConstants.WHITE) + ")  ");
			gwin.getLblStatus().setText("TieHuman(" + b.checkerCount(CheckersConstants.BLACK) + ")Computer("
					+ b.checkerCount(CheckersConstants.WHITE) + ")  ");
		}
	}

	private static int getChoice() throws InterruptedException {
		gwin.choice = -1;
		while (gwin.choice < 0) {
			if (gwin.choice == -1)
				continue;
			if (gwin.moves[gwin.choice].getText().indexOf("-") != -1)
				gwin.choice = -1;
		}
		for (int i = 0; i < gwin.moves.length; i++)
			gwin.moves[i].setText("---------");
		return gwin.choice;
	}

	private static void printMoves() {
		gwin.movesList = new ArrayList<Move>();
		int i = 0;
		for (Move m : possible) {
			gwin.moves[i].setText(String.valueOf(i) + ':' + m);
			gwin.movesList.add(m);
			System.out.println(i + ": " + m);
			i++;
		}
	}
}