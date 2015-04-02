package maze.cli;

import maze.logic.Game;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Interface {

	protected static Game game;
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
		int type_dragons = 0;

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

	public static void updateGame() {

		System.out.print("\n\n");

		game.printGame();

		do {

			System.out.print("\n  Direction ( w/s || a/d || e ) -> ");
			String direction = scan.next();

			switch (direction) {
			case "e":
				if (game.getHero().hasDarts()) {
					System.out.print("\n\n   -> Direction: ");
					String darts = scan.next();
					game.checkDartsDirection(darts);
				} else {
					System.out.print("\n\n   Hero doesn't have darts!  \n\n\n");
				}
				game.printGame();
				break;
			default:
				System.out.print("\n\n");
				game.updateGameState(direction);
				game.checkIfDragonIsNear();
				game.printGame();
			}


		} while (!game.getGameState() && game.getHero().isAlive());

	}

	public static void main(String args[]) {
		int option = 0;
		boolean done = false;

		printMenu();
		option = scan.nextInt();

		switch (option) {
		case 1:
			createGame();
			updateGame();
			break;
		case 2:
			System.out.print("\n    Quitting game! \n");
			break;
		default:
			System.out.print("\n\n 	Invalid input! Try again!\n");
			break;

		}

		if (game.getHero().isAlive() == true) {
			System.out.print("\n"
					+ "-------- Congratulations! You Win! ----------\n\n");
		} else {
			System.out.print("\n"
					+ "-------- Try Again! You Lose! ----------\n\n");
		}

		scan.close();
	}

}
