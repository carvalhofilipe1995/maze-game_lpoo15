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

	/**
	 * Hero Consctructor
	 * @param x
	 * @param y
	 */
	public Hero(int x, int y) {
		super(x, y, "H", true);
		this.alive = true;
		this.sword = false;
		this.shield = false;
		this.victory = false;
		this.darts = 0;
	}

	/**
	 * 
	 * @return true if hero is alive
	 */
	public boolean isAlive() {
		return this.alive;
	}
	
	/**
	 * Hero was kill
	 */
	public void kill() {
		this.alive = false;
	}
	/**
	 * 
	 * @return true if hero has the sword
	 */
	public boolean hasSword() {
		return this.sword;
	}
	/**
	 * 
	 * @return true if hero has darts
	 */
	public boolean hasDarts() {
		if(this.darts > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Take darts from hero
	 */
	public void setDarts(){
		this.darts -= 1;
		if (darts <= 0 && !hasSword()) {
			this.setId("H");
		}
	}
	/**
	 * Put hero with darts
	 */
	public void grabDarts() {
		this.darts += 1;
		this.setId("A");
	}
	/**
	 * Put hero with sword
	 */
	public void grabSword() {
		this.sword = true;
		this.setId("A");
	}
	
	/**
	 * 
	 * @return true if hero has the shield
	 */
	public boolean hasShield() {
		return this.shield;
	}

	/**
	 * Put hero with shield
	 */
	public void grabShield() {
		this.shield = true;
	}

	/**
	 * 
	 * @return true if hero wins
	 */
	public boolean getVictory() {
		return this.victory;
	}

	/**
	 * Change victory
	 */
	public void setVictory() {
		this.victory = true;
	}
}
