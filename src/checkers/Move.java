
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
	Move(int fromx, int fromY, int toX, int toY, boolean isJumped, boolean isMadeKing, char jumpedPiece,
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
	Move(Move move) { // copy constructor
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
	void setFromTo(int fromX, int fromY, int toX, int toY) {
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
	void setMoveCoords(int fromX, int fromY, int toX, int toY) {
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
	void setJumpedPiece(char newJumpedPiece) {
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
	void setNext(Move newNext) {
		if (newNext == null)
			next = newNext;
		else if (this.tx == newNext.fx && this.ty == newNext.fy)
			next = newNext;
		else
			System.err.println("Invalid Next Move " + newNext);
	}

	int getFromX() {
		return fx;
	}

	int getFromY() {
		return fy;
	}

	int getToX() {
		return tx;
	}

	int getToY() {
		return ty;
	}

	boolean getJump() {
		return jump;
	}

	boolean getMadeKing() {
		return madeKing;
	}

	char getJumpedPiece() {
		return jumpedPiece;
	}

	Move getNextMove() {
		return next;
	}

	/**
	 * toString for describing each move
	 */
	@Override
	public String toString() {
		String jp = "";
		if (jump) {
			switch (jumpedPiece) {
			case 'w':
				jp = "white";
				break;
			case 'B':
				jp = "black king";
				break;
			case 'W':
				jp = "white king";
				break;
			case 'b':
				jp = "black";
				break;
			default:
				break;
			}
		}
		String temp = "(" + fx + "," + fy + ") to (" + tx + "," + ty + ")" +
						(jump ? ", Jumped: " + jp : "") +
						(madeKing ? ", make new king" : "");
		if (next != null)
			temp = temp + " next=[" + next.toString() + "]";
		return temp;
	}
}