package maze.logic;

public class dragao extends Elementos {
	// camps
	protected int dragaoState;
	
	// Constructor
	public dragao(int x, int y, char represent, int dragonState){
		super(x,y,represent);
		this.dragaoState = dragonState;
	}
	
	// Methods
	public int getDragaoState(){
		return dragaoState;
	}
	
	public void setDragaoState(char n){
		this.dragaoState = n;
	}
	
}
