package Logic;

public class Dragon extends Cell {
    private boolean alive;
    private boolean awake;
    private String type;

    public Dragon(int x, int y, String[][] maze, boolean awake, String type) {
        super(x, y, "D", maze);
        this.awake = awake;
        this.type = type;
    }

    public void move(){
        //TODO
    }
}
