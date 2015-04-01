package maze.logic;

public class Dragon extends Cell {
	private boolean alive;
	private boolean awake;
	private boolean visible;

	// 1 - static, 2 - roam, 3 - roam & sleep, 4 - fire

	public Dragon(int x, int y) {
		super(x, y, "D");
	}

	public Dragon(int x, int y, boolean awake) {
		super(x, y, "D");
		this.alive = true;
		this.awake = awake;
		this.visible = true;
	}

	public String getType() {
		return this.getId();
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
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(){
		this.visible = !visible;
	}
}
