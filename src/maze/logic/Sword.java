package maze.logic;

import java.io.Serializable;
/**
 * Represent Sword element
 * @author luiscarvalho
 *
 */
public class Sword extends Cell implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean picked;

    /**
     * Sword Constructor
     *
     * @param x coordinate x
     * @param y coordinate y
     */
    public Sword(int x, int y) {
        super(x, y, "/", true);
        this.picked = false;
    }

    /**
     * @return true if hero gets the sword
     */
    public boolean isPicked() {
        return picked;
    }

    /**
     * Sword was picked by hero
     */
    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }
}
