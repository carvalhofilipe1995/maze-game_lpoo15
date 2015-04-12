package maze.logic;

public class Shield extends Cell {
    private boolean picked;

    public Shield(int x, int y) {
        super(x, y, "e", true);
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
