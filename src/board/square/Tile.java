package board.square;

import checker.piece.CheckerPiece;

public class Tile {
	int				x;
	int				y;
	CheckerPiece	piece;

	/**
	 * Set the tile location one the board
	 * @param x the X location of the tile
	 * @param y the Y location of the title
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		piece = null;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the piece
	 */
	public CheckerPiece getPiece() {
		return piece;
	}

	/**
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(CheckerPiece piece) {
		piece.setX(this.x);
		piece.setY(this.y);
		this.piece = piece;
	}
	
	public CheckerPiece clearPiece(){
		CheckerPiece temp = this.piece;
		this.piece = null;
		return temp;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
