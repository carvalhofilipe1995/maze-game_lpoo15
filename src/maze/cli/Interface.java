package maze.cli;

import maze.logic.Game;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Interface {

	protected static Game game;
	protected static int type_dragons;
	static Scanner scan = new Scanner(System.in);

	public static void printMenu() {

		System.out.print(">>>>>>>>>> The Labirinth >>>>>>>>>>\n\n");

		System.out.print("	1. Play \n");
		System.out.print("	2. Quit \n");

		System.out.print("\n   Action -> ");
	}

	public int getHeroMoves() {
		return 0;
	}

	public static void createGame() {

		int size = 0;
		int number_dragons = 0;

		System.out.print("\n\n>>>>>>>>>>>>> Options >>>>>>>>>>>>>>\n\n");
		System.out.print(" -> Choose which maze do you want to play: "
				+ "\n\n    1. Default    2. Generate a new Maze   \n\n\n");

		System.out.print("    Option -> ");

		int maze_preferences = scan.nextInt();

		if (maze_preferences == 2) {

			while (size < 5 || size % 2 == 0) {
				System.out
						.print("\n\n---> Maze size (odd value between 5 and 99):  ");
				size = scan.nextInt();
			}

			System.out.print("\n\n---> Number of dragons:   ");
			number_dragons = scan.nextInt();

			System.out.print("\n\n---> Type of dragons: " + "\n\n  1. Static\n"
					+ "\n  2. Roam\n" + "\n  3. Roam and Sleep\n");
			System.out.print("\n    Action -> ");
			type_dragons = scan.nextInt();
		}

		switch (maze_preferences) {
		case 1:
			game = new Game();
			game.initializePositionsElements(2);
			break;
		case 2:
			game = new Game(size, size, number_dragons, type_dragons);
			game.initializePositionsElements(number_dragons);
			break;
		default:
			System.out.print("\n    Invalid input! Try again! \n");
			break;
		}

	}

	public static void initializeGame() {
		
		System.out.print("\n\n");

		do {
			
			game.printGame();
			
			System.out.print("\n  Direction (w/s || a/d) -> ");
			String direction = scan.next();
			System.out.print("\n");
			
			game.updateGameState(direction, type_dragons);


		} while (!game.getGameState() && game.getHero().isAlive());

		System.out.println();
	}

	public static void main(String args[]) {
		int option = 0;
		boolean done = false;
		
		printMenu();
		option = scan.nextInt();

		switch (option) {
		case 1:
			createGame();
			initializeGame();
			break;
		case 2:
			System.out.print("\n    Quitting game! \n");
			break;
		default:
			System.out.print("\n\n 	Invalid input! Try again!\n");
			break;

		}
		scan.close();
	}

}
