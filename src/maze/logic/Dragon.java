package maze.logic;

public class Dragon extends Cell {
    private boolean alive;
    private boolean awake;
    private int type; //1 - static, 2 - roam, 3 - roam & sleep

    public Dragon(int x, int y, boolean awake, int type) {
        super(x, y, "D");
        this.alive = true;
        this.awake = awake;
        this.type = type;
    }

    public int getType() {
        return type;
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

