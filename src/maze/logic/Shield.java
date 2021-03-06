package maze.logic;

import java.io.Serializable;
/**
 * Represents Shield element
 * @author luiscarvalho
 *
 */
public class Shield extends Cell implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean picked;

    /**
     * Shield Constructor
     *
     * @param x coordinate x
     * @param y coordinate y
     */
    public Shield(int x, int y) {
        super(x, y, "e", true);
        this.picked = false;
    }

    /**
     * @return true if shield was picked by hero
     */
    public boolean isPicked() {
        return picked;
    }

    /**
     * Hero gets the shield
     */
    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }
}
