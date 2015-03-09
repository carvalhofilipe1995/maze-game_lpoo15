package Logic;

public class Sword extends Cell {
    private boolean picked;

    public Sword(int x, int y, String[][] maze) {
        super(x, y, "S", maze);
        this.picked = false;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }

    public void move(){}
}
