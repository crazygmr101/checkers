package checkers;

import java.io.Serializable;
import java.util.*;

import checkers.ui.OutputFrame;

/**
 * Class that represents a board
 */
public class Board implements Serializable {

	private static final long serialVersionUID = 3540754479334928544L;
	
	/**
	 * Character array that represents the board
	 * @see checkers.CheckersConstants
	 */
	public char [][] board;

	/**
	 * @return the board
	 */
	public char[][] getBoard() {
		char[][] board = new char[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				board[i][j] = this.board[i+1][j+1];
		return board;
	}

	/**
	 * Constructor, does all the initializing
	 */
	public Board( ) {
		board = new char[10][10];
		// illegal positions outside edges 
		for (int i=0;i<=9;i++) {
			board[0][i] = CheckersConstants.OUT;
			board[9][i] = CheckersConstants.OUT;
			board[i][0] = CheckersConstants.OUT;
			board[i][9] = CheckersConstants.OUT;
		}

		// illegal positions within board
		for (int i=1;i<=8;i+=2)
			for (int j=2;j<=8;j+=2) {
				board[i][j-1] = CheckersConstants.OUT;
				board[i+1][j] = CheckersConstants.OUT;
			}

		// initial checker positions
		for (int j=2;j<=8;j+=2) {
			board[1][j]   = CheckersConstants.WCHEC;	// white
			board[2][j-1] = CheckersConstants.WCHEC;	// white
			board[3][j]   = CheckersConstants.WCHEC;	// white
			board[4][j-1] = CheckersConstants.AVAIL;	// legal and empty positons
			board[5][j]   = CheckersConstants.AVAIL;	// legal and empty postions
			board[6][j-1] = CheckersConstants.BCHEC;	// black
			board[7][j]   = CheckersConstants.BCHEC;	// black
			board[8][j-1] = CheckersConstants.BCHEC;	// black
		}
	}

	/**
	 * 
	 * @param turn
	 * @return moves for a turn
	 */
	@SuppressWarnings("unused")
	public ArrayList<Move> find_moves(int turn) {
		ArrayList<Move> allMoves = new ArrayList<Move>();
		boolean jumpExists=false;
		if (turn==CheckersConstants.BLACK) {
			for (int i=1;i<=8;i++){
				for (int j=1;j<=8;j++){
					if (board[i][j] == CheckersConstants.BCHEC || board[i][j] == CheckersConstants.BKING) {
						ArrayList<Move> oneCheckerMoves = find_moves(i,j);
						for (Move item: oneCheckerMoves) {
							if (item.getJump()) jumpExists=true; 
							allMoves.add(item);
						}
					}
				}
			}
		}
		else {
			for (int i=1;i<=8;i++){
				for (int j=1;j<=8;j++){
					if (board[i][j] == CheckersConstants.WCHEC || board[i][j] == CheckersConstants.WKING) {
						ArrayList<Move> oneCheckerMoves = find_moves(i,j);
						for (Move item: oneCheckerMoves){
							if (item.getJump()) jumpExists=true; 
							allMoves.add(item);
						}
					}
				}
			}
		}
		// FORCED JUMP LOGIC, IF AT LEAST ONE JUMP AVAILABLE ONLY RETURN JUMP MOVES
		// Turned off (DN)
		if (jumpExists && false) {
			Iterator<Move> itr = allMoves.iterator();
			while (itr.hasNext()) {
				Move m = itr.next();
				if (m.getJump()==false) itr.remove();
			}
		}
		return allMoves;
	}
	
	/**
	 * Finds the moves a checker can take
	 * @param row
	 * @param col
	 * @return moves
	 */
	@SuppressWarnings("incomplete-switch")
	public ArrayList<Move> find_moves(int row, int col) {
		ArrayList<Move> oneCheckerMoves = new ArrayList<Move>();
		Move newMove;
		char piece=board[row][col];
		// for single checker moves
		if (piece == CheckersConstants.WCHEC || piece == CheckersConstants.BCHEC) {
			int k=0;
			// white up, black down
			if (piece == CheckersConstants.WCHEC) k = 1;
			else if (piece == CheckersConstants.BCHEC) k = -1;

			for (int i=0;i<2;i++) { // always 2 possible moves for each checker
				int j;
				if (i == 0) j = 1;
				else j = -1;

				// get the position moving to
				char check = board[row+k][col+j];

				// save non-jump move if space available
				if (check == CheckersConstants.AVAIL) {
					boolean newMoveKing=false;
					if (row+k==1 || row+k==8) newMoveKing=true;
					newMove=new Move(row, col, row+k, col+j, false, newMoveKing, CheckersConstants.AVAIL, null); 
					oneCheckerMoves.add(newMove);
				}
				// if jump then save the jump
				else if ( (piece == CheckersConstants.WCHEC && (check == CheckersConstants.BCHEC || check == CheckersConstants.BKING)) || 
						(piece == CheckersConstants.BCHEC && (check == CheckersConstants.WCHEC || check == CheckersConstants.WKING)) ) {
					if (row+2*k>=1 && row+2*k<=8 && col+2*j>=1 && col+2*j<=8 ) {
						if (board[row+2*k][col+2*j] == CheckersConstants.AVAIL){
							boolean newMoveKing=false;
							if (row+2*k==1 || row+2*k==8) {
								newMoveKing=true;
							}
							newMove=new Move(row, col, row+2*k, col+2*j, true, newMoveKing, check, null);
							make_move(newMove);  // should make king if applicable
							ArrayList<Move> jumpMoves = find_moves(row+2*k, col+2*j);
							boolean continuedJump=false;
							if (!jumpMoves.isEmpty()) { 
								Move moveToExtend = new Move(newMove);
								for (Move continuedMove : jumpMoves){
									// only extend jumps
									if (continuedMove.getJump()) {
										moveToExtend.setNext(continuedMove);
										oneCheckerMoves.add(moveToExtend);
										continuedJump=true;
										moveToExtend = new Move(newMove);
									}
								}
							}
							if (!continuedJump) {	// no continued jumps
								oneCheckerMoves.add(newMove);
							}
							unmake_move(newMove);	// should unmake king if applicable
						}
					}
				} // end of jump
			}	// end of two possible moves for each non-king checker
		}  // end of non-king checker moves

		// for single king checker moves
		if (piece == CheckersConstants.WKING || piece == CheckersConstants.BKING) {
			for (int i=0;i<4;i++) {
				// try all directions
				int j=0, k=0;
				switch(i) {
				case 0: j=1; k=1; break;
				case 1: j=1; k=-1; break;
				case 2: j=-1; k=1; break;
				case 3: j=-1; k=-1; break;
				}

				// get the position moving to
				char check = board[row+k][col+j];

				// save non-jump move if space available
				if (check == CheckersConstants.AVAIL) {
					newMove=new Move(row, col, row+k, col+j, false, false, CheckersConstants.AVAIL, null); 
					oneCheckerMoves.add(newMove);
				}
				// if jump then save the jump
				else if ( (piece == CheckersConstants.WKING && (check == CheckersConstants.BCHEC || check == CheckersConstants.BKING)) || 
						(piece == CheckersConstants.BKING && (check == CheckersConstants.WCHEC || check == CheckersConstants.WKING)) ) {
					if ((row+2*k)>=1 && (row+2*k)<=8 && (col+2*j)>=1 && (col+2*j)<=8 ) {
						if (board[(row+2*k)][(col+2*j)] == CheckersConstants.AVAIL){
							newMove=new Move(row, col, row+2*k, col+2*j, true, false, check, null);
							make_move(newMove);  
							ArrayList<Move> jumpMoves = find_moves(row+2*k, col+2*j);
							boolean continuedJump=false;
							if (!jumpMoves.isEmpty()) {
								Move moveToExtend = new Move(newMove);
								for (Move continuedMove : jumpMoves){
									// only extend jumps
									if (continuedMove.getJump()) {
										moveToExtend.setNext(continuedMove);
										oneCheckerMoves.add(moveToExtend);
										moveToExtend = new Move(newMove);
										continuedJump=true;
									}
								}
							}
							if (!continuedJump) {	// no continued jumps
								oneCheckerMoves.add(newMove);
							}
							unmake_move(newMove);
						}
					}
				} // end of jump
			}	// end of four possible moves for each king checker
		}  // end of king checker moves
		return oneCheckerMoves;
	}
	
	/**
	 * Make a move
	 * @param m
	 */
	public void make_move(Move m) {
		int tx, ty, fx, fy;
		do {
			fx=m.getFromX();
			fy=m.getFromY();
			char piece = board[fx][fy];
			board[fx][fy] = CheckersConstants.AVAIL;

			tx=m.getToX();
			ty=m.getToY();
			int k=0, l=0;
			// left, right, down or up
			if (tx > fx) k = 1; else k = -1;
			if (ty > fy) l = 1; else l = -1;

			// clear the space if it was a jump
			if (m.getJump()) board[fx+k][fy+l] = CheckersConstants.AVAIL;

			// change to king
			if (piece == CheckersConstants.WCHEC && m.getMadeKing()) piece = CheckersConstants.WKING;
			if (piece == CheckersConstants.BCHEC && m.getMadeKing()) piece = CheckersConstants.BKING;

			// update the board with moved piece
			board[tx][ty] = piece;
			m=m.getNextMove();
			

		} while (m != null);
	}	

	/**
	 * Undo a move
	 * @param m
	 */
	public void unmake_move(Move m) {
		ArrayList<Move> moveList = new ArrayList<Move>();
		while (m != null) {
			moveList.add(m);
			m=m.getNextMove();
		}
		Collections.reverse(moveList);
		for (Move mm : moveList) {
			int fx=mm.getToX();
			int fy=mm.getToY();
			char piece = board[fx][fy];
			board[fx][fy] = CheckersConstants.AVAIL;

			int tx=mm.getFromX();
			int ty=mm.getFromY();
			int k=0, l=0;
			// left, right, down or up
			if (tx > fx) k = 1; else k = -1;
			if (ty > fy) l = 1; else l = -1;

			// if it was a jump put back the jumped piece
			if (mm.getJump()) board[fx+k][fy+l] = mm.getJumpedPiece();

			// change back to normal piece if it was a king
			if (piece == CheckersConstants.WKING && mm.getMadeKing()) piece = CheckersConstants.WCHEC;
			if (piece == CheckersConstants.BKING && mm.getMadeKing()) piece = CheckersConstants.BCHEC;

			// update the board with moved piece
			board[tx][ty] = piece;
		}
	}

	/**
	 * Counts checkers for a player
	 * @param who
	 * @return how many checkers a player has
	 */
	public int checkerCount(int who) { // WHITE=-1, CheckersConstants.BLACK=1
		int count=0;
		for (int i=1;i<=8;i++){
			for (int j=1;j<=8;j++){
				if (who==CheckersConstants.WHITE && (board[i][j]==CheckersConstants.WCHEC || board[i][j]==CheckersConstants.WKING))
					count++;
				if (who==CheckersConstants.BLACK && (board[i][j]==CheckersConstants.BCHEC || board[i][j]==CheckersConstants.BKING))
					count++;
			}
		}
		return count;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString( ) {	
		String temp = "\t1\t2\t3\t4\t5\t6\t7\t8\n";
		for (int i=1;i<=8;i++){
			temp=temp+i+"\t";
			for (int j=1;j<=8;j++){
				// display coresponding figures
				switch (board[i][j]){
				case CheckersConstants.WCHEC: temp=temp+"W\t"; break;
				case CheckersConstants.WKING: temp=temp+"WK\t"; break;
				case CheckersConstants.BCHEC: temp=temp+"B\t"; break;
				case CheckersConstants.BKING: temp=temp+"BK\t"; break;
				case CheckersConstants.AVAIL: temp=temp+".\t"; break;
				case CheckersConstants.OUT:   temp=temp+"\t"; break;
				default:
					break;
				}
			}
			temp=temp+"\n";
		}
		return temp;
	}
	
	/**
	 * Checks to see if the game is ended on a player's turn
	 * @param player
	 * @return true/false 
	 */
	public boolean end_game(int player) {
		ArrayList<Move> data=find_moves(player);
		if (data.isEmpty()) return true;
		else return false;
	}

	/**
	 * Board scoring<br>
	 *  - 2 point for each checker on the board (NOT KING, NOT ABOUT TO KING)<br/>
	 *  - 3 points for each checker on the board about to king<br>
	 *  - 4 points for each king<br>
	 *  - add points for the longest capture chain<br>
	 * @param player  CheckersConstants.BLACK or CheckersConstants.WHITE
	 * @return score
	 */
	public int evaluateForPlayingBlack(int player) {
		// validate player 
		assert ( player == CheckersConstants.BLACK || player == CheckersConstants.WHITE);
		
		int score = 2 * normalCheckerCount(player) + 4 * kingCheckerCount(player) + 3 * checkersAboutToKing(player);
		

		return (player == CheckersConstants.BLACK? score : -score);
	}
	
	/**
	 * Board scoring<br>
	 *  - 2 point for each checker on the board (NOT KING, NOT ABOUT TO KING)<br/>
	 *  - 3 points for each checker on the board about to king<br>
	 *  - 4 points for each king<br>
	 *  - add points for the longest capture chain<br>
	 * @param player  CheckersConstants.BLACK or CheckersConstants.WHITE
	 * @return score
	 */
	public int evaluateForPlayingWhite(int player) {
		// validate player 
		assert ( player == CheckersConstants.BLACK || player == CheckersConstants.WHITE);
		
		int score = 2 * normalCheckerCount(player) + 4 * kingCheckerCount(player) + 3 * checkersAboutToKing(player);
		
		return (player == CheckersConstants.WHITE ? score : -score);
	}
	
	/**
	 * Counts normal checkers on the board
	 * @param who CheckersConstants.BLACK or CheckersConstants.WHITE
	 * @return Number of checkers
	 */
	public int normalCheckerCount(int who) { // WHITE=-1, CheckersConstants.BLACK=1
		int count=0;
		for (int i=1;i<=8;i++){
			for (int j=1;j<=8;j++){
				if (who==CheckersConstants.WHITE && (board[i][j]==CheckersConstants.WCHEC))
					count++;
				if (who==CheckersConstants.BLACK && (board[i][j]==CheckersConstants.BCHEC))
					count++;
			}
		}
		return count;
	}
	
	/**
	 * Counts kings on the board
	 * @param who CheckersConstants.BLACK or CheckersConstants.WHITE
	 * @return Number of kings
	 */
	public int kingCheckerCount(int who) { // WHITE=-1, CheckersConstants.BLACK=1
		int count=0;
		for (int i=1;i<=8;i++){
			for (int j=1;j<=8;j++){
				if (who==CheckersConstants.WHITE && (board[i][j]==CheckersConstants.WKING))
					count++;
				if (who==CheckersConstants.BLACK && (board[i][j]==CheckersConstants.BKING))
					count++;
			}
		}
		return count;
	}
	
	/**
	 * Counts moves that could lead to a king arising
	 * from the depths of the checker underworld
	 * @param who CheckersConstants.BLACK or CheckersConstants.WHITE
	 * @return Number of checkers about to summon a king from the underworld
	 */
	public int checkersAboutToKing(int who) {
		int count = 0;
		ArrayList<Move> ar = find_moves(who);
		for (Move m : ar)
			if (m.getMadeKing())
				count++;
		return count;
	}
}	