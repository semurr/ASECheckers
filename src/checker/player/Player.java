package checker.player;

public enum Player {
	ONE("X"),
	TWO("O"),
	AI("O");
	
	String player;
	
	Player(String player){
		this.player = player;
	}
	
	public String toString(){
		return player;
	}

}
