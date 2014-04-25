package game;

import java.util.InputMismatchException;
import java.util.Scanner;

import checker.player.AIEasy;
import checker.player.Player;
import board.GameBoard;

public class main {

	public static void main(String[] args) {
		int playerTurn = 0;
		Player[] playerList = new Player[2];
		playerList[0] = Player.ONE;
		playerList[1] = Player.AI;
		AIEasy easyAI = new AIEasy();

		GameBoard gameBoard = new GameBoard(8, 8);
		gameBoard.initilizePieces(playerList[0], playerList[1]);

		// directions
		System.out
				.println("When it is your turn tell your piece to move/jump left or right, top left is 0, 0");
		System.out.println("Example: 0 1 move right");
		System.out.println("Example: 5 3 jump left");

		do {
			int x = 0;
			int y = 0;
			String action = "";
			String direction = "";
			Scanner in = new Scanner(System.in);

			System.out.println("It is player " + playerList[playerTurn]
					+ " Turn\n");
			
			try {
				
				gameBoard.Print();			

				System.out.println("Please insert x y action direction\n");

				// check if jump available
				if (gameBoard.jumpAvailable(playerList[playerTurn])) {
					System.out.println("Must Jump");

					//if its ai player have him do move
					if (playerList[playerTurn] == Player.AI) {
						easyAI.jump(gameBoard);
						if (gameBoard.jumpAvailable(playerList[playerTurn]) == false) {
							playerTurn = updatePlayerTurn(playerTurn);
						}

					} else { //else require human
						x = in.nextInt();
						y = in.nextInt();
						action = in.next();
						direction = in.next();

						// make sure they are trying to jump
						if (action.equals("jump")) {
							gameBoard.jump(playerList[playerTurn], x, y,
									direction);

							// if another jump is availble they can double jump
							if (gameBoard.jumpAvailable(playerList[playerTurn])) {

							} else { // jump not available update player turn
								playerTurn = updatePlayerTurn(playerTurn);
							}
						}

					}
				} else if (gameBoard.moveAvailable(playerList[playerTurn])) {

					Boolean playerTurnFinish = false;
					
					if (playerList[playerTurn] == Player.AI) {
						easyAI.move(gameBoard);
						playerTurn = updatePlayerTurn(playerTurn);

					}else{
						x = in.nextInt();
						y = in.nextInt();
						action = in.next();
						direction = in.next();

						// make sure they are trying to jump
						if (action.equals("jump")) {
							gameBoard.jump(playerList[playerTurn], x, y, direction);
						} else if (action.equals("move")) {
							playerTurnFinish = gameBoard.move(
									playerList[playerTurn], x, y, direction);
						}

						if (playerTurnFinish == true) {
							playerTurn = updatePlayerTurn(playerTurn);
						}						
					}					
					
				} else {
					// if no move/jump available for player check if other
					// player wins/ check for stalemate then update player
					if (gameBoard
							.checkWin(playerList[updatePlayerTurn(playerTurn)]) == true) {
						System.out
								.println(playerList[updatePlayerTurn(playerTurn)]
										+ " Wins!!");
						break;
					} else if (gameBoard.checkStalemate(playerList[0],
							playerList[0]) == true) {
						System.out.println("Stalemate!");
						break;
					} else {
						System.out.println("You have no moves");
						playerTurn = updatePlayerTurn(playerTurn);
					}

				}
			} catch (InputMismatchException e) {
				System.out
						.println("Invalid command use: x y action direction\n");

			}
		} while (true);
	}

	/**
	 * updates the whos turn it is
	 * 
	 * @param player
	 *            The value of the current player
	 * @return the player value who is next
	 */
	public static int updatePlayerTurn(int player) {
		if (player == 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
