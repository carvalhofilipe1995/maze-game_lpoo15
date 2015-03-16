package maze.cli;

import java.util.Scanner;

public class Cli {

    public void drawMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(" " + maze[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void victory() {
        System.out.print(
                "**********************************************\n" +
                        "****************!!  VICTORY !!****************\n" +
                        "**********************************************\n");
    }

    public void defeat() {
        System.out.print(
                "**********************************************\n" +
                        "****************!!  Defeat !!*****************\n" +
                        "**********************************************\n");
    }

    public int getChooseMaze() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print(
                "**********************************************\n" +
                        "******************?DAR NOME?******************\n" +
                        "**********************************************\n" +
                        "\t1. Use the default maze\n" +
                        "\t2. Generate a new maze\n");
        do {
            System.out.print("Option: ");
            if (in.hasNextInt()) {
                input = in.nextInt();
            } else {
                in.next();
            }
        } while (input != 1 && input != 2);
        return input;
    }

    public int getHeight() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print("**********************************************\n");
        do {
            System.out.print("Maze height (odd value between 5 and 99): ");
            if (in.hasNextInt()) {
                input = in.nextInt();
            } else {
                in.next();
            }
        } while (input < 5 || input > 99 || input % 2 == 0);
        return input;
    }

    public int getWidth() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print("**********************************************\n");
        do {
            System.out.print("Maze width (odd value between 5 and 99): ");
            if (in.hasNextInt()) {
                input = in.nextInt();
            } else {
                in.next();
            }
        } while (input < 5 || input > 99 || input % 2 == 0);
        return input;
    }

    public int getNDragons() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print("**********************************************\n");
        do {
            System.out.print("Number of dragons (value between 1 and 3): ");
            if (in.hasNextInt()) {
                input = in.nextInt();
            } else {
                in.next();
            }
        } while (input < 1 || input > 3);
        return input;
    }

    public int getTDragons() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print("**********************************************\n" +
                        "Type of dragons:\n" +
                        "\t1. Static\n" +
                        "\t2. Roam\n" +
                        "\t3. Roam and Sleep\n"
        );
        do {
            System.out.print("Option: ");
            if (in.hasNextInt()) {
                input = in.nextInt();
            } else {
                in.next();
            }
        } while (input < 1 || input > 3);
        return input;
    }

    public String getDirection() {
        Scanner in = new Scanner(System.in);
        System.out.print("Direction(a/d/w/s): ");
        String input;
        input = in.nextLine();
        return input;
    }
}
