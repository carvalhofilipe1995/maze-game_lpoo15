package maze.logic;

import java.io.Serializable;

public class Sword extends Cell  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean picked;

    public Sword(int x, int y) {
        super(x, y, "/", true);
        this.picked = false;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pickUp() {
        this.picked = true;
        this.setId(" ");
    }
}
