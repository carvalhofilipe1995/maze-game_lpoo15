package Logic;

import java.awt.Point;

public abstract class Cell {
    protected Point coord;
    protected String id;
    protected String[][] maze;

    public Cell(int x, int y, String id, String[][] maze) {
        this.coord = new Point(x, y);
        this.id = id;
        this.maze=maze;
    }

    public Point getCoord() {
        return coord;
    }

    public String getId() {
        return id;
    }

    public void setCoord(int x, int y) {
        this.coord.setLocation(x, y);
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void move();

}
