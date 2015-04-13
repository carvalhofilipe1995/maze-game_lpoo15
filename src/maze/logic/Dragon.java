package maze.logic;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;
/**
 * Element dragon
 * 
 * @author luiscarvalho
 *
 */
public class Dragon extends Cell implements Serializable {
<<<<<<< Updated upstream
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean alive;
    private boolean awake;
    private Vector<Point> cellsOnFire;


    /**
     * Dragon constructor
     *
     * @param x
     * @param y
     */
    public Dragon(int x, int y) {
        super(x, y, "D", true);
        this.alive = true;
        this.awake = true;
        cellsOnFire = new Vector<>(0, 1);
    }

    /**
     * Dragon constructor
     *
     * @param x
     * @param y
     * @param awake
     */
    public Dragon(int x, int y, boolean awake) {
        super(x, y, "D", true);
        this.alive = true;
        this.awake = awake;
        cellsOnFire = new Vector<>(0, 1);
    }

    /**
     * @return type of dragon
     */

    public String getType() {
        return this.getId();
    }

    /**
     * @return true if dragon is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Put cells on fire next to dragon
     *
     * @param p
     */

    public void addCellFire(Point p) {
        cellsOnFire.add(p);
    }

    /**
     * @return cells with fire next to dragon
     */
    public Vector<Point> getCellsOnFire() {
        return cellsOnFire;
    }

    /**
     * remove cells on fire next to dragon
     */
    public void removeCellsOnFire() {

        cellsOnFire.removeAllElements();

    }

    /**
     * kill dragon
     */

    public void kill() {
        alive = false;
    }

    /**
     * @return true if dragon is awake
     */
    public boolean isAwake() {
        return awake;
    }

    /**
     * change type dragon to sleep
     */
    public void setAsleep() {
        awake = false;
        this.setId("d");
    }

    /**
     * change type dragon to awake
     */
    public void setAwake() {
        awake = true;
        this.setId("D");
    }
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean alive;
	private boolean awake;
	private Vector<Point> cellsOnFire;

	
	/**
	 * Dragon constructor
	 * @param x dragon coordinate x
	 * @param y dragon coordinate y
	 */
	public Dragon(int x, int y) {
		super(x, y, "D", true);
		this.alive = true;
		this.awake = true;
		cellsOnFire = new Vector<>(0, 1);
	}

	/**
	 * Dragon constructor
	 * @param x dragon coordinate x
	 * @param y dragon coordinate y
	 * @param awake type dragon
	 */
	public Dragon(int x, int y, boolean awake) {
		super(x, y, "D", true);
		this.alive = true;
		this.awake = awake;
		cellsOnFire = new Vector<>(0, 1);
	}
	
	/**
	 * 
	 * @return type of dragon
	 */

	public String getType() {
		return this.getId();
	}

	/**
	 * 
	 * @return true if dragon is alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Put cells on fire next to dragon
	 * @param p cell to put on fire
	 */

	public void addCellFire(Point p) {
		cellsOnFire.add(p);
	}
	/**
	 * 
	 * @return cells with fire next to dragon
	 */
	public Vector<Point> getCellsOnFire(){
		return cellsOnFire;
	}

	/**
	 *  remove cells on fire next to dragon
	 */
	public void removeCellsOnFire() {
		
		cellsOnFire.removeAllElements();

	}
	
	/**
	 * kill dragon
	 */

	public void kill() {
		alive = false;
	}

	/**
	 * 
	 * @return true if dragon is awake
	 */
	public boolean isAwake() {
		return awake;
	}

	/**
	 * change type dragon to sleep
	 */
	public void setAsleep() {
		awake = false;
		this.setId("d");
	}

	/**
	 * change type dragon to awake
	 */
	public void setAwake() {
		awake = true;
		this.setId("D");
	}
>>>>>>> Stashed changes
}
