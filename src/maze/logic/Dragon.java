package maze.logic;

import java.awt.*;
import java.util.Vector;

public class Dragon extends Cell {
	private boolean alive;
	private boolean awake;
	private Vector<Point> cellsOnFire;

	// 1 - static, 2 - roam, 3 - roam & sleep

	public Dragon(int x, int y) {
		super(x, y, "D", true);
		this.alive = true;
		this.awake = true;
		cellsOnFire = new Vector<>(0, 1);
	}

	public Dragon(int x, int y, boolean awake) {
		super(x, y, "D", true);
		this.alive = true;
		this.awake = awake;
		cellsOnFire = new Vector<>(0, 1);
	}

	public String getType() {
		return this.getId();
	}

	public boolean isAlive() {
		return alive;
	}

	public void addCellFire(Point p) {
		cellsOnFire.add(p);
	}
	
	public Vector<Point> getCellsOnFire(){
		return cellsOnFire;
	}

	public void removeCellsOnFire() {
		
		cellsOnFire.removeAllElements();

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
