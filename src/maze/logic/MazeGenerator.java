package maze.logic;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Stack;
<<<<<<< Updated upstream

public class MazeGenerator implements Serializable {
=======
/**
 * Build maze class
 * @author luiscarvalho
 *
 */
public class MazeGenerator  implements Serializable {
>>>>>>> Stashed changes
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // camps
    protected int height;
    protected int width;
    protected String[][] lab;
    protected String[][] visitedCells;
    protected int visitedCellsDimension;
    protected Stack<Point> pathHistory;
    protected Point guideCell;
    protected Point exit;

    /**
     * MazeGenerator Constructor
<<<<<<< Updated upstream
     *
     * @param height
     * @param width
=======
     * @param height maze height
     * @param width maze width
>>>>>>> Stashed changes
     */
    public MazeGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        this.lab = new String[height][width];
        this.visitedCells = new String[height - 2][width - 2];
        this.visitedCellsDimension = (height - 1) / 2; // height == width
        this.pathHistory = new Stack<>();
        this.guideCell = new Point();
        this.exit = new Point();
    }


    /**
     * @return maze height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return maze width
     */
    public int getWidth() {
        return this.width;
    }


    /**
     * @return the labyrinth
     */
    public String[][] getMaze() {
        return lab;
    }

    /**
<<<<<<< Updated upstream
     * @param x
     * @param y
=======
     * 
     * @param x cell coordinate x
     * @param y cell coordinate y
>>>>>>> Stashed changes
     * @return the cell with this coordinates
     */
    public String getCell(int x, int y) {
        return lab[y][x];
    }

    /**
     * @return the exit of the maze
     */
    public Point getExit() {
        return exit;
    }

    /**
     * Change the exit coordinates
<<<<<<< Updated upstream
     *
     * @param x
     * @param y
=======
     * @param x exit coordinate x
     * @param y exit coordinate y
>>>>>>> Stashed changes
     */
    public void setExit(int x, int y) {
        this.exit = new Point(x, y);
    }

    /**
     * Put the cell with fire
<<<<<<< Updated upstream
     *
     * @param p
=======
     * @param p point on fire
>>>>>>> Stashed changes
     */
    public void setCellAsFire(Point p) {
        setMaze(p, "^");
    }

    /**
<<<<<<< Updated upstream
     * Change maze in this point
     *
     * @param p
     * @param change
=======
     * Change maze in this point 
     * @param p cell to change
     * @param change change that will be made
>>>>>>> Stashed changes
     */
    public void setMaze(Point p, String change) {
        this.lab[(int) p.getY()][(int) p.getX()] = change;
    }

    /**
     * Set cell as visited
<<<<<<< Updated upstream
     *
     * @param p
     * @param change
=======
     * @param p cell to set as visited
     * @param change change to make
>>>>>>> Stashed changes
     */
    public void setVisitedCells(Point p, String change) {
        this.visitedCells[(int) p.getY()][(int) p.getX()] = change;
    }

    /**
     * @return 0 if maze was created
     */
    public int createMaze() {
        Random rand = new Random();

        // fill everything with walls 'X' or ' '
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2) != 0 && (j % 2) != 0) {
                    Point p = new Point(j, i);
                    setMaze(p, " ");
                } else {
                    Point change = new Point(j, i);
                    setMaze(change, "X");
                }
            }
        }

        // fill the visited cells array

        for (int i = 0; i < visitedCells.length; i++) {
            for (int j = 0; j < visitedCells.length; j++)
                visitedCells[i][j] = ".";
        }

        generateExit(); // Randomly get the exit

        startGuideCell(); // Initialize Guide cell

        setVisitedCells(guideCell, "+");

        pathHistory.push(new Point((int) guideCell.getX(), (int) guideCell
                .getY()));

        do {

            while (!checkMovements()) {
                pathHistory.pop();
                if (pathHistory.empty())
                    break;
                else
                    guideCell = pathHistory.peek();
            }

            if (pathHistory.empty())
                break;

            int direction;
            do {
                direction = rand.nextInt(4);
            } while (!checkIfMoveIsValid(direction));

            switch (direction) {
                case 0: // up
                    Point change_up = new Point((int) guideCell.getX() * 2 + 1,
                            (int) guideCell.getY() * 2);
                    setMaze(change_up, " ");
                    break;
                case 1: // down
                    Point change_down = new Point((int) guideCell.getX() * 2 + 1,
                            (int) (guideCell.getY() + 1) * 2);
                    setMaze(change_down, " ");
                    break;
                case 2: // right
                    Point change_right = new Point(
                            (int) (guideCell.getX() + 1) * 2,
                            (int) guideCell.getY() * 2 + 1);
                    setMaze(change_right, " ");
                    break;
                case 3: // left
                    Point change_left = new Point((int) guideCell.getX() * 2,
                            (int) guideCell.getY() * 2 + 1);
                    setMaze(change_left, " ");
                    break;
                default:
                    break;
            }

            updateGuideCell(direction);
            // Point p = guideCell;
            pathHistory.push(new Point((int) guideCell.getX(), (int) guideCell
                    .getY()));

        } while (!pathHistory.empty());

        return 0;
    }

    /**
     * Generates randomly the exit
     */
    public void generateExit() {
        Random rand = new Random();

        int exitZ;
        do {
            exitZ = rand.nextInt(lab.length - 2) + 1;
        } while (exitZ % 2 == 0);

        // getting random border to place exit at
        switch (rand.nextInt(4)) {
            case 0: // top
                exit.setLocation(exitZ, exit.getY());
                break;
            case 1: // right
                exit.setLocation(lab.length - 1, exitZ);
                break;
            case 2: // bottom
                exit.setLocation(exitZ, lab.length - 1);
                break;
            case 3: // left
                exit.setLocation(exit.getX(), exitZ);
                break;
            default:
                exit.setLocation(1, exit.getY());
                break;
        }

        // exit
        lab[(int) exit.getY()][(int) exit.getX()] = "S";
    }

    /**
     * Start the guide cell
     */
    public void startGuideCell() {
        Point nearExit = new Point((int) exit.getX(), (int) exit.getY());

        if (exit.getX() == 0) {
            nearExit.setLocation(nearExit.getX() + 1, nearExit.getY());
        } else if (exit.getX() == lab.length - 1) {
            nearExit.setLocation(nearExit.getX() - 1, nearExit.getY());
        } else if (exit.getY() == 0) {
            nearExit.setLocation(nearExit.getX(), nearExit.getY() + 1);
        } else if (exit.getY() == lab.length - 1)
            nearExit.setLocation(nearExit.getX(), nearExit.getY() - 1);

        int guideCellX = ((int) nearExit.getX() - 1) / 2;
        int guideCellY = ((int) nearExit.getY() - 1) / 2;

        guideCell.setLocation(guideCellX, guideCellY);
    }

    /**
     * @return true is move is valid
     */
    public boolean checkMovements() {
        for (int i = 0; i < 4; i++) {
            if (checkIfMoveIsValid(i)) {
                return true;
            }
        }
        return false;
    }

    /**
<<<<<<< Updated upstream
     * @param direction
=======
     * 
     * @param direction cell to visit
>>>>>>> Stashed changes
     * @return true if cell has been visited
     */
    public boolean cellHasBeenVisited(int direction) {
        switch (direction) {
            case 0: // up
                if (visitedCells[(int) guideCell.getY() - 1][(int) guideCell.getX()]
                        .equals("+"))
                    return true;
                break;
            case 1: // down
                if (visitedCells[(int) guideCell.getY() + 1][(int) guideCell.getX()]
                        .equals("+"))
                    return true;
                break;
            case 2: // right
                if (visitedCells[(int) guideCell.getY()][(int) guideCell.getX() + 1]
                        .equals("+"))
                    return true;
                break;
            case 3: // left
                if (visitedCells[(int) guideCell.getY()][(int) guideCell.getX() - 1]
                        .equals("+"))
                    return true;
                break;
            default:
                break;
        }
        return false;
    }

    /**
<<<<<<< Updated upstream
     * @param direction
=======
     * 
     * @param direction direction to check
>>>>>>> Stashed changes
     * @return true if direction is valid
     */
    public boolean checkIfMoveIsValid(int direction) {
        switch (direction) {
            case 0: // up
                if (guideCell.getY() - 1 < 0) {
                    return false;
                }
                break;
            case 1: // down
                if (guideCell.getY() + 1 >= visitedCellsDimension) {
                    return false;
                }
                break;
            case 2: // right
                if (guideCell.getX() + 1 >= visitedCellsDimension) {
                    return false;
                }
                break;
            case 3: // left
                if (guideCell.getX() - 1 < 0) {
                    return false;
                }
                break;
            default:
                break;
        }
        return !cellHasBeenVisited(direction);
    }

<<<<<<< Updated upstream
    /**
     * Update the guide cell
     *
     * @param direction
     */
=======
   /**
    * Update the guide cell
    * @param direction direction to update
    */
>>>>>>> Stashed changes
    public void updateGuideCell(int direction) {
        switch (direction) {
            case 0: // up
                guideCell.setLocation(guideCell.getX(), guideCell.getY() - 1);
                setVisitedCells(guideCell, "+");
                break;
            case 1: // down
                guideCell.setLocation(guideCell.getX(), guideCell.getY() + 1);
                setVisitedCells(guideCell, "+");
                break;
            case 2: // right
                guideCell.setLocation(guideCell.getX() + 1, guideCell.getY());
                setVisitedCells(guideCell, "+");
                break;
            case 3: // left
                guideCell.setLocation(guideCell.getX() - 1, guideCell.getY());
                setVisitedCells(guideCell, "+");
                break;
            default:
                break;
        }
    }

}
