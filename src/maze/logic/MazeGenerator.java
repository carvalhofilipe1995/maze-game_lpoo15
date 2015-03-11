package maze.logic;

import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    // camps
    protected int height;
    protected int width;
    protected String[][] lab;
    protected String[][] visitedCells;
    protected int visitedCellsDimension;
    protected Stack<Point> pathHistory;
    protected Point guideCell;
    protected Point exit;

    // constructor
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

    public static void main(String args[]) {
        MazeGenerator maze = new MazeGenerator(21, 21);
        maze.createMaze();
        maze.printLabirinto();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setMaze(Point p, String change) {
        this.lab[(int) p.getY()][(int) p.getX()] = change;
    }

    public void setVisitedCells(Point p, String change) {
        this.visitedCells[(int) p.getY()][(int) p.getX()] = change;
    }

    public String[][] getMaze() {
        return lab;
    }

    // Create Maze
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

    // Done
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

    // Done
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

    // Done
    public boolean checkMovements() {
        for (int i = 0; i < 4; i++) {
            if (checkIfMoveIsValid(i)) {
                return true;
            }
        }
        return false;
    }

    // Done
    public boolean cellHasBeenVisited(int direction) {
        switch (direction) {
            case 0: // up
                if (visitedCells[(int) guideCell.getY() - 1][(int) guideCell.getX()].equals("+"))
                    return true;
                break;
            case 1: // down
                if (visitedCells[(int) guideCell.getY() + 1][(int) guideCell.getX()].equals("+"))
                    return true;
                break;
            case 2: // right
                if (visitedCells[(int) guideCell.getY()][(int) guideCell.getX() + 1].equals("+"))
                    return true;
                break;
            case 3: // left
                if (visitedCells[(int) guideCell.getY()][(int) guideCell.getX() - 1].equals("+"))
                    return true;
                break;
            default:
                break;
        }
        return false;
    }

    // Done
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

    // Done
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

    // Print map
    public void printLabirinto() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(this.lab[i][j] + ' ');
            }
            System.out.print("\n");
        }
    }

}
