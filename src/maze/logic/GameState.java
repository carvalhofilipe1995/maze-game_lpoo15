package maze.logic;

public class GameState {
	// camps
	protected int game_state;
	Labirinto l;

	
	
	// methods
	public int getGameState() {
		return game_state;
	}

	public void setGameState() {
		if (game_state == 0)
			game_state = 1;
		else
			game_state = 0;
	}
	
	

}
