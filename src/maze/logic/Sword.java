package maze.logic;

public class Sword extends Cell {
    private boolean picked;
    private boolean visible;

    public Sword(int x, int y) {
        super(x, y, "/");
        this.picked = false;
        this.visible = true;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }
    
    public boolean isVisible(){
    	return this.visible;
    }
    
    public void setVisible(){
    	this.visible = !visible;
    }
}
