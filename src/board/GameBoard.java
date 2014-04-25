package board;

import java.util.ArrayList;
import java.util.List;

import checker.piece.CheckerPiece;
import checker.player.Player;
import board.square.Tile;

/**
 * @author stephen
 * 
 */
public class GameBoard {
	private Tile[][]			board;
	private List<CheckerPiece>	pieceList;
	private int					width;
	private int					height;
	private String				LEFT	= "left";
	private String				RIGHT	= "right";

	public GameBoard(int width, int height) {
		this.width = width;
		this.height = height;
		board = new Tile[width][height];
		// set tiles for the board
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[x][y] = new Tile(x, y);
			}
		}
		pieceList = new ArrayList<CheckerPiece>();
	}

	public void initilizePieces(Player player1, Player player2) {
		// player one pieces
		board[1][0].setPiece(new CheckerPiece(player1));
		board[3][0].setPiece(new CheckerPiece(player1));
		board[5][0].setPiece(new CheckerPiece(player1));
		board[7][0].setPiece(new CheckerPiece(player1));
		board[0][1].setPiece(new CheckerPiece(player1));
		board[2][1].setPiece(new CheckerPiece(player1));
		board[4][1].setPiece(new CheckerPiece(player1));
		board[6][1].setPiece(new CheckerPiece(player1));
		board[1][2].setPiece(new CheckerPiece(player1));
		board[3][2].setPiece(new CheckerPiece(player1));
		board[5][2].setPiece(new CheckerPiece(player1));
		board[7][2].setPiece(new CheckerPiece(player1));
		pieceList.add(board[1][0].getPiece());
		pieceList.add(board[3][0].getPiece());
		pieceList.add(board[5][0].getPiece());
		pieceList.add(board[7][0].getPiece());
		pieceList.add(board[0][1].getPiece());
		pieceList.add(board[2][1].getPiece());
		pieceList.add(board[4][1].getPiece());
		pieceList.add(board[6][1].getPiece());
		pieceList.add(board[1][2].getPiece());
		pieceList.add(board[3][2].getPiece());
		pieceList.add(board[5][2].getPiece());
		pieceList.add(board[7][2].getPiece());

		// player two pieces
		board[0][5].setPiece(new CheckerPiece(player2));
		board[2][5].setPiece(new CheckerPiece(player2));
		board[4][5].setPiece(new CheckerPiece(player2));
		board[6][5].setPiece(new CheckerPiece(player2));
		board[1][6].setPiece(new CheckerPiece(player2));
		board[3][6].setPiece(new CheckerPiece(player2));
		board[5][6].setPiece(new CheckerPiece(player2));
		board[7][6].setPiece(new CheckerPiece(player2));
		board[0][7].setPiece(new CheckerPiece(player2));
		board[2][7].setPiece(new CheckerPiece(player2));
		board[4][7].setPiece(new CheckerPiece(player2));
		board[6][7].setPiece(new CheckerPiece(player2));
		pieceList.add(board[0][5].getPiece());
		pieceList.add(board[2][5].getPiece());
		pieceList.add(board[4][5].getPiece());
		pieceList.add(board[6][5].getPiece());
		pieceList.add(board[1][6].getPiece());
		pieceList.add(board[3][6].getPiece());
		pieceList.add(board[5][6].getPiece());
		pieceList.add(board[7][6].getPiece());
		pieceList.add(board[0][7].getPiece());
		pieceList.add(board[2][7].getPiece());
		pieceList.add(board[4][7].getPiece());
		pieceList.add(board[6][7].getPiece());
	}

	public void Print() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (board[x][y].getPiece() == null) {
					builder.append(".");
				} else {
					builder.append(board[x][y].getPiece().getPlayer());
				}
				builder.append(" ");
			}
			builder.append("\n");
		}
		System.out.println(builder.toString());
	}

	public boolean canMove(Player player, int currentX, int currentY,
			String direction, Boolean messaging) {
		Tile currentTile = null;

		// check if given a correct starting position
		try {
			currentTile = board[currentX][currentY];
		} catch (ArrayIndexOutOfBoundsException e) {
			if (messaging == true) {
				System.out.println("Invalid tile location please use x y");
			}

			return false;
		}

		// check if current location is not null
		if (currentTile.getPiece() != null) {

			// check if piece at position is correct for player
			if (currentTile.getPiece().getPlayer().equals(player)) {

				// check if able to move piece in that direction
				if (direction.equalsIgnoreCase(LEFT)) {
					try {
						Tile futureTile = board[currentTile.getPiece()
								.moveLeft()][currentTile.getPiece()
								.moveForward()];
						// if the future tile does not have a piece in it
						if (futureTile.getPiece() == null) {
							return true;

						}
					} catch (ArrayIndexOutOfBoundsException e) {
						if (messaging == true) {
							System.out
									.println("Unable to move piece off game board");
						}
					}
				} else if (direction.equalsIgnoreCase(RIGHT)) {
					try {
						Tile futureTile = board[currentTile.getPiece()
								.moveRight()][currentTile.getPiece()
								.moveForward()];
						// if the future tile does not have a piece in it
						if (futureTile.getPiece() == null) {
							return true;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						if (messaging == true) {
							System.out
									.println("Unable to move piece off game board");
						}
					}

				} else {
					if (messaging == true) {
						System.out
								.println("Invalid move direction please use either 'left' or 'right'");
					}

				}

			} else {
				if (messaging == true) {
					System.out.println("That is not your piece");
				}

			}

		} else {
			if (messaging == true) {
				System.out.println("No piece at location");
			}

		}

		return false;

	}

	public boolean move(Player player, int currentX, int currentY,
			String direction) {

		if (canMove(player, currentX, currentY, direction, true) == true) {
			Tile currentTile = board[currentX][currentY];
			if (direction.equalsIgnoreCase(LEFT)) {
				Tile futureTile = board[currentTile.getPiece().moveLeft()][currentTile
						.getPiece().moveForward()];
				futureTile.setPiece(currentTile.getPiece());
				currentTile.clearPiece();
			} else {
				Tile futureTile = board[currentTile.getPiece().moveRight()][currentTile
						.getPiece().moveForward()];
				futureTile.setPiece(currentTile.getPiece());
				currentTile.clearPiece();
			}
			return true;
		}
		return false;
	}

	public boolean canJump(Player player, int currentX, int currentY,
			String direction, Boolean messaging) {
		Tile currentTile = null;

		// check if given a correct starting position
		try {
			currentTile = board[currentX][currentY];
		} catch (ArrayIndexOutOfBoundsException e) {
			if (messaging == true) {
				System.out.println("Invalid tile location please use x y");
			}
			return false;
		}

		// check if current location is not null
		if (currentTile.getPiece() != null) {

			// check if piece at position is correct for player
			if (currentTile.getPiece().getPlayer().equals(player)) {

				// check if able to move piece in that direction
				if (direction.equalsIgnoreCase(LEFT)) {
					try {
						Tile futureTile = board[currentTile.getPiece()
								.jumpLeft()][currentTile.getPiece()
								.jumpForward()];
						// if the future tile does not have a piece in it
						if (futureTile.getPiece() == null) {
							// check if tile in between has a piece of opposite
							// player
							if (oppositePlayerPiece(
									player,
									(futureTile.getX() + currentTile.getX()) / 2,
									(futureTile.getY() + currentTile.getY()) / 2,
									messaging) == true) {
								return true;
							}

						}
					} catch (ArrayIndexOutOfBoundsException e) {
						if (messaging == true) {
							System.out
									.println("Unable to move piece off game board");
						}
					}
				} else if (direction.equalsIgnoreCase(RIGHT)) {
					try {
						Tile futureTile = board[currentTile.getPiece()
								.jumpRight()][currentTile.getPiece()
								.jumpForward()];
						// if the future tile does not have a piece in it
						if (futureTile.getPiece() == null) {
							// check if tile in between has a piece of opposite
							// player
							if (oppositePlayerPiece(
									player,
									(futureTile.getX() + currentTile.getX()) / 2,
									(futureTile.getY() + currentTile.getY()) / 2,
									messaging) == true) {
								return true;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						if (messaging == true) {
							System.out
									.println("Unable to move piece off game board");
						}
					}
				} else {
					if (messaging == true) {
						System.out
								.println("Invalid move direction please use either 'left' or 'right'");
					}
				}

			} else {
				if (messaging == true) {
					System.out.println("That is not your piece");
				}

			}

		} else {
			if (messaging == true) {
				System.out.println("No piece at location");

			}

		}

		return false;
	}

	public boolean oppositePlayerPiece(Player player, int pieceX, int pieceY,
			boolean messaging) {

		try {
			Tile checkTile = board[pieceX][pieceY];

			if (checkTile.getPiece().getPlayer() != player) {
				return true;
			} else {
				if (messaging == true) {
					System.out.println("incorrect piece to jump");
				}

			}
		} catch (Exception e) {
			if (messaging == true) {
				System.out.println("no piece to jump");
			}
		}

		return false;
	}

	public boolean jump(Player player, int currentX, int currentY,
			String direction) {

		if (canJump(player, currentX, currentY, direction, true) == true) {
			Tile currentTile = board[currentX][currentY];
			if (direction.equalsIgnoreCase(LEFT)) {
				Tile futureTile = board[currentTile.getPiece().jumpLeft()][currentTile
						.getPiece().jumpForward()];
				Tile jumpTile = board[(futureTile.getX() + currentTile.getX()) / 2][(futureTile
						.getY() + currentTile.getY()) / 2];
				// set the piece to location
				futureTile.setPiece(currentTile.getPiece());
				currentTile.clearPiece();

				// remove the jumped piece
				pieceList.remove(jumpTile.clearPiece());

			} else {
				Tile futureTile = board[currentTile.getPiece().jumpRight()][currentTile
						.getPiece().jumpForward()];
				futureTile.setPiece(currentTile.getPiece());
				Tile jumpTile = board[(futureTile.getX() + currentTile.getX()) / 2][(futureTile
						.getY() + currentTile.getY()) / 2];
				currentTile.clearPiece();

				// remove the jumped piece
				pieceList.remove(jumpTile.clearPiece());
			}
			return true;
		}

		return false;
	}

	/**
	 * Check if jump is available for player
	 * 
	 * @param player
	 *            Player pieces to check
	 * @return True if able to jump
	 */
	public boolean jumpAvailable(Player player) {

		// for each piece in the list
		for (CheckerPiece piece : pieceList) {
			// check to make sure its correct player piece
			if (piece.getPlayer().equals(player)) {
				// check if it can jump left
				if (canJump(player, piece.getX(), piece.getY(), LEFT, false) == true) {
					return true;
				}

				// check if it can jump right
				if (canJump(player, piece.getX(), piece.getY(), RIGHT, false) == true) {
					return true;
				}

			}
		}
		return false;
	}

	public boolean moveAvailable(Player player) {

		// for each piece in the list
		for (CheckerPiece piece : pieceList) {
			// check to make sure its correct player piece
			if (piece.getPlayer().equals(player)) {
				// check if it can jump left
				if (canMove(player, piece.getX(), piece.getY(), LEFT, false) == true) {
					return true;
				}

				// check if it can jump right
				if (canMove(player, piece.getX(), piece.getY(), RIGHT, false) == true) {
					return true;
				}

			}
		}
		return false;

	}

	/**
	 * check if given player wins
	 * 
	 * @param player
	 *            THe player to check if the won
	 * @return True if the won, false if the didn't
	 */
	public boolean checkWin(Player player) {
		for (CheckerPiece piece : pieceList) {
			if (piece.getPlayer() != player) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Check if both players have no more moves/jumps available
	 * 
	 * @param player1
	 *            Player 1
	 * @param player2
	 *            Player 2
	 */
	public boolean checkStalemate(Player player1, Player player2) {
		Boolean player1Move = moveAvailable(player1);
		Boolean player1Jump = jumpAvailable(player1);
		Boolean player2Move = moveAvailable(player2);
		Boolean player2Jump = jumpAvailable(player2);

		// no more moves available for both players
		if (player1Move == false && player1Jump == false
				&& player2Move == false && player2Jump == false) {
			return true;
		}
		return false;
	}

	/**
	 * @return the pieceList
	 */
	public List<CheckerPiece> getPieceList() {
		return pieceList;
	}
}
