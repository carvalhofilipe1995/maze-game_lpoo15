package maze.logic;

import java.awt.*;
import java.io.Serializable;


/**
 * 	Class for all elements in the maze
 * 
 * @author luiscarvalho
 *
 */
public abstract class Cell implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Point coord;
    protected String id;
    protected boolean visible;

    /**
     * Constructor Cell
<<<<<<< Updated upstream
     *
     * @param x
     * @param y
     * @param id
     * @param visible
=======
     * @param x coordinate x
     * @param y coordinate y
     * @param id type
     * @param visible visibility
>>>>>>> Stashed changes
     */
    public Cell(int x, int y, String id, boolean visible) {
        this.coord = new Point(x, y);
        this.id = id;
        this.visible = visible;
    }

    /**
     * @return true if a cell is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Change cell visibility
     */
    public void setVisible() {
        this.visible = !visible;
    }

    /**
     * @return cell coordinates
     */
    public Point getCoord() {
        return coord;
    }

    /**
     * @return cell id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id of the cell
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set coordinates of the cell
<<<<<<< Updated upstream
     *
     * @param x
     * @param y
=======
     * @param x coordinate x
     * @param y coordinate y
>>>>>>> Stashed changes
     */
    public void setCoord(int x, int y) {
        this.coord.setLocation(x, y);
    }

    /**
<<<<<<< Updated upstream
     * @param c
=======
     * 
     * @param c cell to check if is next
>>>>>>> Stashed changes
     * @return true if a cell is next to other cell
     */
    public boolean isNextTo(Cell c) {
        return (this.coord.x == c.getCoord().x - 1 && this.coord.y == c.getCoord().y)
                || (this.coord.x == c.getCoord().x + 1 && this.coord.y == c.getCoord().y)
                || (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y - 1)
                || (this.coord.x == c.getCoord().x && this.coord.y == c.getCoord().y + 1);
    }

    /**
<<<<<<< Updated upstream
     * @param c
     * @param range
=======
     * 
     * @param c cell to check
     * @param range range
>>>>>>> Stashed changes
     * @return true if a cell is in the same range than other
     */
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