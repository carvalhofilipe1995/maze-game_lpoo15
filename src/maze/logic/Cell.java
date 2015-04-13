package maze.logic;

import java.awt.*;
import java.io.Serializable;

public abstract class Cell implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point coord;
    protected String id;
    protected boolean visible;

    public Cell(int x, int y, String id, boolean visible) {
        this.coord = new Point(x, y);
        this.id = id;
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible() {
        this.visible = !visible;
    }

    public Point getCoord() {
        return coord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCoord(int x, int y) {
        this.coord.setLocation(x, y);
    }

    public boolean isNextTo(Cell c) {
        return (this.coord.x == c.getCoord().x - 1 && this.coord.y == c.getCoord().y)
                || (this.coord.x == c.getCoord().x + 1 && this.coord.y == c.getCoord().y)
                || (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y - 1)
                || (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y + 1);
    }

    public boolean isInRange(Cell c, int range) {
        if (Math.abs(this.coord.x - c.getCoord().x) <= range && this.coord.y == c.getCoord().y && !this.coord.equals
                (c.getCoord())) {
            return true;
        } else if (this.coord.x == c.getCoord().x && Math.abs(this.coord.y - c.getCoord().y) <= range && !this.coord
                .equals(c.getCoord())) {
            return true;
        }
        return false;
    }


}