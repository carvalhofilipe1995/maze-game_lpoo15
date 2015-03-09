package Logic;

public class Maze {

    private String[][] maze;
    private String[][] template;

    public Maze() {
        this.template = new String[][]{
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

    public void update(Hero hero, Sword sword, Dragon dragon ){
        maze = template;
        maze[hero.coord.x][hero.coord.y]=hero.getId();
        maze[sword.coord.x][sword.coord.y]=sword.getId();
        maze[dragon.coord.x][dragon.coord.y]=dragon.getId();
    }

    public void draw() {
        for (int i = 0; i < maze.length; i++) {
            System.out.println(maze[i]);
        }
        System.out.print("\n");
    }

}
