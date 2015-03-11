package maze.logic;

public class Hero extends Cell {
    private boolean alive;
    private boolean sword;
    private boolean shield;
    private boolean victory;

    public Hero(int x, int y) {
        super(x, y, "H");
        this.alive = true;
        this.sword = false;
        this.shield = false;
        this.victory = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public boolean hasSword() {
        return sword;
    }

    public void grabSword() {
        sword = true;
        this.setId("A");
    }

    public boolean hasShield() {
        return shield;
    }

    public void grabShield() {
        this.shield = true;
    }

    public boolean getVictory() {
        return victory;
    }

    public void setVictory() {
        victory = true;
    }
}

