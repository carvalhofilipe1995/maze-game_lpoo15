package maze.logic;

public class Sword extends Cell {
    private boolean picked;

    public Sword(int x, int y) {
        super(x, y, "/", true);
        this.picked = false;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }
}
