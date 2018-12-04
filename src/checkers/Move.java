
package checkers;

import java.io.Serializable;

/**
 * This class represents a move in the game
 */
public class Move implements Serializable {
	private static final long serialVersionUID = -5399958376653578616L;
	private int fx, fy; // FROM row, FROM column
	private int tx, ty; // TO row, TO column
	private boolean jump, madeKing;
	private char jumpedPiece;
	private Move next;

	/**
	 * 
	 * @param fromx
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @param isJumped
	 * @param isMadeKing
	 * @param jumpedPiece
	 * @param newNextMove
	 */
	public Move(int fromx, int fromY, int toX, int toY, boolean isJumped, boolean isMadeKing, char jumpedPiece,
			Move newNextMove) {
		setMoveCoords(fromx, fromY, toX, toY);
		jump = isJumped;
		madeKing = isMadeKing;
		setJumpedPiece(jumpedPiece);
		setNext(newNextMove);
	}

	/**
	 * Copy constructor
	 * 
	 * @param move
	 */
	public Move(Move move) { // copy constructor
		setMoveCoords(move.fx, move.fy, move.tx, move.ty);
		jump = move.jump;
		madeKing = move.madeKing;
		setJumpedPiece(move.jumpedPiece);
		setNext(move.next);
	}

	/**
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @deprecated Use {@link #setMoveCoords(int,int,int,int)} instead
	 */
	@Deprecated
	public void setFromTo(int fromX, int fromY, int toX, int toY) {
		setMoveCoords(fromX, fromY, toX, toY);
	}

	/**
	 * Sets the coords for the move
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void setMoveCoords(int fromX, int fromY, int toX, int toY) {
		if (fromX >= 1 && fromX <= 8 && toX >= 1 && toY <= 8) {
			fx = fromX;
			fy = fromY;
			tx = toX;
			ty = toY;
		} else
			System.err.println("Invalid Move (" + fromX + "," + fromY + ") to(" + toX + "," + toY + ")");
	}

	/**
	 * Sets jumped piece
	 * 
	 * @param newJumpedPiece
	 */
	public void setJumpedPiece(char newJumpedPiece) {
		if (newJumpedPiece == CheckersConstants.BCHEC || newJumpedPiece == CheckersConstants.BKING
				|| newJumpedPiece == CheckersConstants.WCHEC || newJumpedPiece == CheckersConstants.WKING
				|| newJumpedPiece == CheckersConstants.AVAIL)
			jumpedPiece = newJumpedPiece;
		else
			System.err.println("Invalid Jumped Piece " + newJumpedPiece);
	}

	/**
	 * Sets next move
	 * 
	 * @param newNext
	 */
	// TODO is this used in chains?? (DN)
	public void setNext(Move newNext) {
		if (newNext == null)
			next = newNext;
		else if (this.tx == newNext.fx && this.ty == newNext.fy)
			next = newNext;
		else
			System.err.println("Invalid Next Move " + newNext);
	}

	public int getFromX() {
		return fx;
	}

	public int getFromY() {
		return fy;
	}

	public int getToX() {
		return tx;
	}

	public int getToY() {
		return ty;
	}

	public boolean getJump() {
		return jump;
	}

	public boolean getMadeKing() {
		return madeKing;
	}

	public char getJumpedPiece() {
		return jumpedPiece;
	}

	public Move getNextMove() {
		return next;
	}

	/**
	 * toString for describing each move
	 */
	// TODO this needs desperate work
	// potential format :
	// (2,4) to (4,5) jump black king
	// (2,4) to (4,5) make new king
	// (2,4) to (4,5) jump black king, make new king
	@Override
	public String toString() {
		String temp = "fx=" + fx + " fy=" + fy + " tx=" + tx + " ty=" + ty + " jump=" + jump + " newKing=" + madeKing
				+ " jumped=" + jumpedPiece;
		if (next != null)
			temp = temp + " next=[" + next.toString() + "]";
		return temp;
	}
}