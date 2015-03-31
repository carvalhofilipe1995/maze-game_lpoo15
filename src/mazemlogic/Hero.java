package maze.logic;

public class Hero extends Cell {

	private boolean alive;
	private boolean sword;
	private boolean shield;
	private boolean victory;
	private boolean darts;

	public Hero(int x, int y) {
		super(x, y, "H");
		this.alive = true;
		this.sword = false;
		this.shield = false;
		this.victory = false;
		this.darts = false;
	}


	public boolean isAlive() {
		return this.alive;
	}

	public void kill() {
		this.alive = false;
	}

	public boolean hasSword() {
		return this.sword;
	}

	public boolean hasDarts() {
		return this.darts;
	}

	public void grabDarts() {
		this.darts = true;
		this.setId("A");
	}

	public void grabSword() {
		this.sword = true;
		this.setId("A");
	}

	public boolean hasShield() {
		return this.shield;
	}

	public void grabShield() {
		this.shield = true;
	}

	public boolean getVictory() {
		return this.victory;
	}

	public void setVictory() {
		this.victory = true;
	}
}
