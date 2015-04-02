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

    public boolean isNextTo(Cell c) {
        if (this.coord.x == c.getCoord().x - 1 && this.coord.y == c.getCoord().y) {
            return true;
        }
        if (this.coord.x == c.getCoord().x + 1 && this.coord.y == c.getCoord().y) {
            return true;
        }
        if (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y - 1) {
            return true;
        }
        if (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y + 1) {
            return true;
        }
        return false;
    }

    public boolean isInRange(Cell c, int range) {
        if (Math.abs(this.coord.x - c.getCoord().x) <= range && this.coord.y == c.getCoord().y) {
            return true;
        }
        if (this.coord.x == c.getCoord().x && Math.abs(this.coord.y - c.getCoord().y) <= range) {
            return true;
        }
        return false;
    }
    
   
    
}