package maze.logic;

public class Coordenadas {
	// Camps
	protected int x;
	protected int y;
	
	
	// Constructor
	public Coordenadas(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Methods

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int line){
		this.x = line;
	}
	
	public void setY(int collumn){
		this.y = collumn;
	}
}

