package maze.logic;

public class Dragon extends Cell {
    private boolean alive;
    private boolean awake;
    private String type;

    public Dragon(int x, int y, boolean awake, String type) {
        super(x, y, "D");
        this.alive = true;
        this.awake = awake;
        this.type = type;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAwake() {
        return awake;
    }

    public void setAsleep() {
        awake = false;
        this.setId("d");
    }

    public void setAwake() {
        awake = true;
        this.setId("D");
    }

}

