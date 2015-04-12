package maze.test;

import maze.logic.Cell;
import maze.logic.Dragon;
import maze.logic.Hero;
import maze.logic.Sword;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void testCell() {
        Cell cell1, cell2, cell3;

        cell1 = new Hero(1, 1);
        cell2 = new Sword(2, 1);
        cell3 = new Dragon(4, 2);
        cell3.setVisible();

        assertTrue(cell1.isVisible());
        assertFalse(cell3.isVisible());
        assertEquals("D", cell3.getId());
        assertTrue(cell1.isNextTo(cell2));
        assertTrue(cell1.isInRange(cell2, 2));
        assertFalse(cell1.isInRange(cell3, 2));
    }
}
