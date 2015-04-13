package maze.logic;

import java.io.Serializable;

public class Hero extends Cell  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean alive;
	private boolean sword;
	private boolean shield;
	private boolean victory;
	private int darts;

	public Hero(int x, int y) {
		super(x, y, "H", true);
		this.alive = true;
		this.sword = false;
		this.shield = false;
		this.victory = false;
		this.darts = 0;
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
		if(this.darts > 0)
			return true;
		else
			return false;
	}
	
	public void setDarts(){
		this.darts -= 1;
		if (darts <= 0 && !hasSword()) {
			this.setId("H");
		}
	}

	public void grabDarts() {
		this.darts += 1;
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
