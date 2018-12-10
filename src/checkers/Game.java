package checkers;

import java.util.*;

/**
 * This is the basic class that handles the game itself
 */
public class Game {
	private Board b;
	private int BLACKdepth, WHITEdepth;
	private boolean display=false;

	/**
	 * Constructor
	 * @param board
	 * @param blackDepth
	 * @param whiteDepth
	 * @param display //TODO um what (DN)
	 */
	public Game(Board board, int blackDepth, int whiteDepth, boolean display) {
		b=board;
		this.display=display;
		if (blackDepth>0) BLACKdepth=blackDepth;
		else BLACKdepth=6;
		if (whiteDepth>0) WHITEdepth=whiteDepth;
		else WHITEdepth=6;
	}
	
	/**
	 * Computes a move for a player
	 * @param turn
	 */
	public void	comp_move(int turn) {
		Move m;
		if (turn == CheckersConstants.BLACK) {
			GameWindow.frame.getLblStatus().setText("Black thinking");
			if (display) {
				System.out.println("Black move - thinking");
			}
			m = bestMove(turn, BLACKdepth, turn);
		}
		else {
			GameWindow.frame.getLblStatus().setText("White thinking");
			if (display) {
				System.out.println("White move - thinking");
			}
			m = bestMove(turn, WHITEdepth, turn);
		}
		if (display) System.out.println(m);
		GameWindow.frame.getLblStatus().setText(" ");
		b.make_move(m);		// make move
	}
	
	/**
	 * Finds the best move for a player
	 * @param whoseMove
	 * @param level
	 * @param turn
	 * @return best move
	 * @deprecated Use {@link #bestMove(int,int,int)} instead
	 */
	@Deprecated
	public Move minmax(int whoseMove, int level, int turn) {
		return bestMove(whoseMove, level, turn);
	}

	/**
	 * Finds the best move for a player
	 * @param whoseMove
	 * @param level
	 * @param turn
	 * @return best move
	 */
	public Move bestMove(int whoseMove, int level, int turn) {
		ArrayList<Move> possible_moves;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		Move chosenMove;
		int best=Integer.MIN_VALUE, current=Integer.MIN_VALUE, worst=Integer.MAX_VALUE;

		// get moves for all checkers of player turn
		possible_moves=b.find_moves(turn);
		chosenMove=possible_moves.get(0);
		for (Move m : possible_moves) {	
			b.make_move(m);
			// recurse
			current = minmaxR(whoseMove, level-1, -1*turn);
			scores.add(current);			
			if (whoseMove==turn) {	// maximize level
				if (current>best) {
					best=current;
					chosenMove=m;
				}
				else if (current==best && Math.random()>.5) {
					chosenMove=m;
				}					
			}
			else {		// minimize level
				if (current<worst) {
					worst=current;
					chosenMove=m;
				}
				else if (current==worst && Math.random()>.5) {
					chosenMove=m;
				}				
			}
			b.unmake_move(m);
		}
		return chosenMove;
	}
	
	/**
	 * Recursive minmax algorithm 
	 * @param whoseMove
	 * @param level
	 * @param turn
	 * @return
	 */
	@SuppressWarnings("boxing")
	public int minmaxR(int whoseMove, int level, int turn) {
		ArrayList<Move> possible_moves;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		int best=Integer.MIN_VALUE, current=Integer.MIN_VALUE, worst=Integer.MAX_VALUE;

		if (b.end_game(turn)) {	// turn player lost
			if (turn==whoseMove) return Integer.MIN_VALUE;
			else return Integer.MAX_VALUE;
		}

		if (level == 0) return turn == CheckersConstants.BLACK ? b.evaluateForPlayingBlack(whoseMove) : b.evaluateForPlayingWhite(whoseMove);
		else {
			// get moves for all checkers of player turn
			possible_moves=b.find_moves(turn);
			for (Move m : possible_moves) {	
				b.make_move(m);
				current = minmaxR(whoseMove, level-1, -1*turn);
				scores.add(current);
				if (whoseMove==turn) {	// maximize level
					if (current>best || (current==best && Math.random()>.5))
						best=current;
				}
				else {		// minimize level
					if (current<worst || (current==worst && Math.random()>.5)) 
						worst=current;
				}
				b.unmake_move(m);
			}
		}

		if (whoseMove==turn) return best;
		else return worst;
	}
}