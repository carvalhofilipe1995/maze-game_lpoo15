package maze.test;

import maze.logic.MazeGenerator;
import org.junit.Test;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MazeGeneratorTest {

    private boolean checkBoundaries(MazeGenerator maze) {
        int countExit = 0;
        int n = maze.getHeight();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    if (maze.getMaze()[i][j].equals("S")) {
                        if ((i == 0 || i == n - 1) && (j == 0 || j == n - 1))
                            return false;
                        else
                            countExit++;
                    } else if (!maze.getMaze()[i][j].equals("X"))
                        return false;
                }
            }
        }
        return countExit == 1;
    }


    private boolean hasSquare(MazeGenerator maze, String[][] square) {
        for (int i = 0; i < maze.getHeight() - square.length; i++)
            for (int j = 0; j < maze.getWidth() - square.length; j++) {
                boolean match = true;
                for (int x = 0; x < square.length; x++)
                    for (int y = 0; y < square.length; y++) {
                        if (!Objects.equals(maze.getMaze()[i + x][j + y], square[x][y]))
                            match = false;
                    }
                if (match)
                    return true;
            }
        return false;
    }

    private boolean checkExitReachable(MazeGenerator maze) {
        Point p = maze.getExit();
        String[][] m = deepClone(maze.getMaze());
        m[p.x][p.y] = " ";
        visit(m, p.x, p.y);

        for (String[] aM : m) {
            for (int j = 0; j < m.length; j++) {
                if (!aM[j].equals("X") && !aM[j].equals("V")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void visit(String[][] m, int i, int j) {
        if (i < 0 || i >= m.length || j < 0 || j >= m.length)
            return;
        if (m[i][j].equals("X") || m[i][j].equals("V"))
            return;
        m[i][j] = "V";
        visit(m, i - 1, j);
        visit(m, i + 1, j);
        visit(m, i, j - 1);
        visit(m, i, j + 1);
    }

    private String[][] deepClone(String[][] m) {
        String[][] c = m.clone();
        for (int i = 0; i < m.length; i++)
            c[i] = m[i].clone();
        return c;
    }

    @Test
    public void testRandomMazeGenerator() throws Exception {
        int numMazes = 1000;
        int maxSize = 101;

        String[][] badWalls = {
                {"X", "X", "X"},
                {"X", "X", "X"},
                {"X", "X", "X"}};
        String[][] badSpaces = {
                {" ", " "},
                {" ", " "}};
        String[][] badDiag1 = {
                {"X", " "},
                {" ", "X"}};
        String[][] badDiag2 = {
                {" ", "X"},
                {"X", " "}};
        Random rand = new Random();
        for (int i = 0; i < numMazes; i++) {
            int size = 5 + 2 * rand.nextInt((maxSize - 5) / 2);
            MazeGenerator generator = new MazeGenerator(size, size);
            generator.createMaze();
            assertTrue("Invalid maze boundaries in maze:\n" + generator, checkBoundaries(generator));
            assertTrue("Maze exit not reachable in maze:\n" + generator, checkExitReachable(generator));
            assertNotNull("Invalid walls in maze:\n" + generator, !hasSquare(generator, badWalls));
            assertNotNull("Invalid spaces in maze:\n" + generator, !hasSquare(generator, badSpaces));
            assertNotNull("Invalid diagonals in maze:\n" + generator, !hasSquare(generator, badDiag1));
            assertNotNull("Invalid diagonals in maze:\n" + generator, !hasSquare(generator, badDiag2));
        }
    }
}