package maze.logic;

public class Maze {

    private String[][] maze;

    public Maze() {
        this.maze = new String[][]{
                {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", "X", "X", " ", "X", " ", "X", " ", "X"},
                {"X", " ", "X", "X", " ", "X", " ", "X", " ", "X"},
                {"X", " ", "X", "X", " ", "X", " ", "X", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X", " ", "S"},
                {"X", " ", "X", "X", " ", "X", " ", "X", " ", "X"},
                {"X", " ", "X", "X", " ", "X", " ", "X", " ", "X"},
                {"X", " ", "X", "X", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}};
    }

    public String[][] getMaze() {
        return maze;
    }

    public void setMaze(String[][] maze) {
        this.maze = maze;
    }

    public String getCell(int x, int y) {
        return maze[x][y];
    }

    public void setCell(int x, int y, String s) {
        this.maze[x][y] = s;
    }

}
