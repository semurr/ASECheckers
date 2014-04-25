package checker.piece;

import checker.player.Player;

public class CheckerPiece {
	int x;
	int y;
	Player player;
	
	/**
	 * 
	 */
	public CheckerPiece(Player player){
		this.player = player;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	public int moveLeft(){
		return this.x - 1;
	}
	
	public int moveRight(){
		return this.x + 1;
	}
	
	public int moveForward(){
		if(player.equals(Player.ONE)){
			return this.y + 1;
		}else {
			return this.y - 1;
		}
	}
	

	public int jumpLeft(){
		return this.x - 2;
	}
	
	public int jumpRight(){
		return this.x + 2;
	}
	
	public int jumpForward(){
		if(player.equals(Player.ONE)){
			return this.y + 2;
		}else {
			return this.y - 2;
		}
	}
}
