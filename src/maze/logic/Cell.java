package maze.logic;

import java.awt.*;

public abstract class Cell {
    protected Point coord;
    protected String id;

    public Cell(int x, int y, String id) {
        this.coord = new Point(x, y);
        this.id = id;
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
}