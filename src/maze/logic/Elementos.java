package maze.logic;

public class Elementos {
	// camps
	protected int line;
	protected int collumn;
	protected char representation;
	
	// Constructor
	public Elementos(int x, int y, char represent){
		this.line = x;
		this.line = y;
		this.representation = represent;
	}
	
	
	// Methods
	public int getLine(){
		return line;
	}
	
	public int getCollumn(){
		return collumn;
	}
	
	public char getRepresentation(){
		return representation;
	}
	
	public void setLine(int x){
		this.line = x;
	}
	
	public void setCollumn(int y){
		this.collumn = y;
	}
	
	public void setRepresentation(char r){
		this.representation = r;
	}
}
