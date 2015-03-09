package Logic;

import java.util.Scanner;

public class Hero extends Cell {
    private boolean alive;
    private boolean sword;

    public Hero(int x, int y, String[][] maze) {
        super(x, y, "H", maze);
        this.alive = true;
        this.sword = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public boolean hasSword(){
        return  sword;
    }

    public void kill(){
        alive=false;
    }

    public void grabSword(){
        sword=true;
        this.setId("A");
    }

    public void move() {
         Scanner in = new Scanner(System.in);
        System.out.print("\n");
        System.out.print("Direction(a/d/w/s): ");
        String input;
        input = in.nextLine();

        switch (input) {
            case "a":
                if (!maze[coord.x][coord.y - 1].equals("X")) {
                    if (maze[coord.x][coord.y - 1].equals("E")) {
                       // maze[coord.x][coord.y] = " ";
                       // coord.y--;
                       // hero = "A";
                      //  maze[coord.x][coord.y] = "A";
                        setCoord(coord.x,coord.y - 1);
                        grabSword();
                    } else if (maze[coord.x][coord.y - 1].equals("D") && id.equals("A")) {
                        maze[coord.x][coord.y] = " ";
                        coord.y--;
                        maze[coord.x][coord.y] = "A";
                    } else if (maze[coord.x][coord.y - 1].equals("D") && id.equals("H")) {
                        System.out.print("GameOver! Try again!");
                       // gameState = 1;
                       // return 1;
                    } else {
                        maze[coord.x][coord.y] = " ";
                        coord.y--;
                        maze[coord.x][coord.y] = hero;
                    }
                }
                break;
            case "d":
                if (maze[coord.x][coord.y + 1].equals("S") && hero.equals("A")) {
                    maze[coord.x][coord.y] = " ";
                    coord.y = coord.y + 1;
                    maze[coord.x][coord.y] = hero;
                    System.out.print("YOU WIN!\n");
                    gameState = 1;
                    return 0;
                } else if (maze[coord.x][coord.y + 1].equals("S") && hero.equals("H")) {
                    System.out.print("You need the sword!\n");
                    return 0;
                }
                if (!maze[coord.x][coord.y + 1].equals("X")) {
                    if (maze[coord.x][coord.y + 1].equals("E")) {
                        maze[coord.x][coord.y] = " ";
                        coord.y++;
                        hero = "A";
                        maze[coord.x][coord.y] = "A";
                    } else if (maze[coord.x][coord.y + 1].equals("D") && hero.equals("A")) {
                        maze[coord.x][coord.y] = " ";
                        coord.y++;
                        maze[coord.x][coord.y] = "A";
                    } else if (maze[coord.x][coord.y + 1].equals("D") && hero.equals("H")) {
                        System.out.print("GameOver! Try again!\n");
                        gameState = 1;
                        return 1;
                    } else {
                        maze[coord.x][coord.y] = " ";
                        coord.y++;
                        maze[coord.x][coord.y] = hero;
                    }
                }
                break;
            case "w":
                if (!maze[coord.x - 1][coord.y].equals("X")) {
                    if (maze[coord.x - 1][coord.y].equals("E")) {
                        maze[coord.x][coord.y] = " ";
                        coord.x--;
                        hero = "A";
                        maze[coord.x][coord.y] = hero;
                    } else if (maze[coord.x - 1][coord.y].equals("D") && hero.equals("A")) {
                        maze[coord.x][coord.y] = " ";
                        coord.x--;
                        maze[coord.x][coord.y] = "A";
                    } else if (maze[coord.x - 1][coord.y].equals("D") && hero.equals("H")) {
                        System.out.print("GameOver! Try Again!\n");
                        gameState = 1;
                        return 1;
                    } else {
                        maze[coord.x][coord.y] = " ";
                        coord.x--;
                        maze[coord.x][coord.y] = hero;
                    }
                }
                break;
            case "s":
                if (!maze[coord.x + 1][coord.y].equals("X")) {
                    if (maze[coord.x + 1][coord.y].equals("E")) {
                        maze[coord.x][coord.y] = " ";
                        coord.x++;
                        hero = "A";
                        maze[coord.x][coord.y] = hero;
                    } else if (maze[coord.x + 1][coord.y].equals("D") && hero.equals("A")) {
                        maze[coord.x][coord.y] = " ";
                        coord.x++;
                        maze[coord.x][coord.y] = "A";
                    } else if (maze[coord.x + 1][coord.y].equals("D") && hero.equals("H")) {
                        System.out.print("GameOver! Try Again!\n");
                        gameState = 1;
                        return 1;
                    } else {
                        maze[coord.x][coord.y] = " ";
                        coord.x++;
                        maze[coord.x][coord.y] = hero;
                    }
                }
                break;
            default:
                System.out.print("Invalid input! Try again!\n");
                return -1;
        }
        System.out.print("\n");
        return 0;
    }
}
