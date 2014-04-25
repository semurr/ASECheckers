package checker.player;

import checker.piece.CheckerPiece;
import board.GameBoard;
import board.square.Tile;

public class AIEasy {

	public void jump(GameBoard board) {

		for (CheckerPiece piece : board.getPieceList()) {
			if (piece.getPlayer().equals(Player.AI)) {
				// can jump left?
				if (board.canJump(Player.AI, piece.getX(), piece.getY(),
						"left", false)) {
					board.jump(Player.AI, piece.getX(), piece.getY(), "left");
					return;
				}

				// else can jump right
				if (board.canJump(Player.AI, piece.getX(), piece.getY(),
						"right", false)) {
					board.jump(Player.AI, piece.getX(), piece.getY(), "right");
					return;
				}
			}			
		}	
	}

	public void move(GameBoard board) {

		for (CheckerPiece piece : board.getPieceList()) {
			if (piece.getPlayer().equals(Player.AI)) {
				// can move left?
				if (board.canMove(Player.AI, piece.getX(), piece.getY(),
						"left", false)) {
					System.out.println("move left");
					System.out.println("move " + piece.getX() + " " + piece.getY() + " \n");
					board.move(Player.AI, piece.getX(), piece.getY(), "left");
					return;

				}

				// else can move right
				if (board.canMove(Player.AI, piece.getX(), piece.getY(),
						"right", false)) {
					System.out.println("move right");
					System.out.println("move " + piece.getX() + " " + piece.getY() + " \n");
					board.move(Player.AI, piece.getX(), piece.getY(), "right");
					return;

				}
			}			
		}
	}

}
