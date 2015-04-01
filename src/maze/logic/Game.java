package maze.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Game {

	Random random = new Random();

	private MazeGenerator maze;
	private Hero hero;
	private Shield shield;
	private Sword sword;
	private static ArrayList<Dragon> dragons;

	private boolean GameState;
	private boolean exitOpen;
	private Vector<Point> emptyCells;
	private Vector<Point> doubleCells;

	public Game() {

		maze = new MazeGenerator(11, 11);
		hero = new Hero(0, 0);
		sword = new Sword(0, 0);
		shield = new Shield(0, 0);

		GameState = false;

		exitOpen = false;

		emptyCells = new Vector<Point>(0, 1);
		doubleCells = new Vector<Point>(0, 1);

		dragons = new ArrayList<Dragon>();
		dragons.add(new Dragon(0, 0));
		dragons.add(new Dragon(0, 0));
		dragons.add(new Dragon(0, 0));
	}

	public Game(int height, int width, int number_dragons) {

		maze = new MazeGenerator(height, width);
		hero = new Hero(0, 0);
		sword = new Sword(0, 0);
		shield = new Shield(0, 0);

		GameState = false;

		exitOpen = false;

		emptyCells = new Vector<Point>(0, 1);
		doubleCells = new Vector<Point>(0, 1);

		dragons = new ArrayList<Dragon>();

		for (int i = 0; i < number_dragons; i++) {
			dragons.add(new Dragon(0, 0, true));
		}

	}

	public boolean getGameState() {
		return GameState;
	}

	public Hero getHero() {
		return hero;
	}

	// Update Game State

	public void updateGameState(String direction, int type_dragons) {

		boolean swordVisible = true;
		boolean dragonVisible = true;

		heroCanMove(direction);

		if (type_dragons == 2)
			moveDragons();
		else if (type_dragons == 3) {
			moveDragons();
			sleepDragons();
		}

		for (Dragon i : dragons) {
			if (i.getCoord().x == sword.getCoord().x
					&& i.getCoord().y == sword.getCoord().y) {
				swordVisible = false;
			}
		}

		for (Dragon i : dragons) {

			if (hero.isNextTo(i) && !hero.hasSword() && i.getId() == "D") {
				hero.kill();
				return;
			}

		}

		for (Dragon i : dragons) {
			if (!i.isVisible()) {
				if (hero.getCoord().x != i.getCoord().x
						|| hero.getCoord().y != i.getCoord().y) {
					i.setVisible();
					maze.setMaze(i.getCoord(), i.getId());
					break;
				}
			}
		}

		if (swordVisible && !sword.isPicked()) {
			maze.setMaze(sword.getCoord(), sword.getId());
		}

		if (dragons.size() - 1 == 0)
			exitOpen = true;

		if (maze.getCell((int) hero.getCoord().x, (int) hero.getCoord().y) == "S"
				&& exitOpen == true) {
			GameState = true;
			return;
		}

	}

	// Hero Movements

	public void heroCanMove(String direction) {
		switch (direction) {
		case "a":
			if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
					.getCoord().getY()) != "S") {
				checkMoveLeft();
			} else if (exitOpen == true) {
				GameState = true;
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
			}
			break;
		case "d":
			if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
					.getCoord().getY()) != "S") {
				checkMoveRight();
			} else if (exitOpen == true) {
				GameState = true;
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
			}
			break;
		case "w":
			if (maze.getCell((int) hero.getCoord().getX(), (int) hero
					.getCoord().getY() - 1) != "S") {
				checkMoveUp();
			} else if (exitOpen == true) {
				GameState = true;
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
			}
			break;
		case "s":
			if (maze.getCell((int) hero.getCoord().getX(), (int) hero
					.getCoord().getY() + 1) != "S") {
				checkMoveDown();
			} else if (exitOpen == true) {
				GameState = true;
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
			}
			break;
		default:
			break;
		}

	}

	public void checkMoveLeft() {
		if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
				.getCoord().getY()) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
				.getCoord().getY()) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
				.getCoord().getY()) == "e") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
				.getCoord().getY()) == "D") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}
			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
				.getCoord().getY()) == "d") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}
			} else {
				for (Dragon i : dragons) {
					if (hero.getCoord().x + 1 == i.getCoord().x
							&& hero.getCoord().y == i.getCoord().y) {
						i.setVisible();
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								" ");
						hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								hero.getId());
						break;
					}
				}
			}

		}
	}

	public void checkMoveRight() {
		if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
				.getCoord().getY()) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());
		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
				.getCoord().getY()) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
				.getCoord().getY()) == "e") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
				.getCoord().getY()) == "D") {
			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}
			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
				.getCoord().getY()) == "d") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons)
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
			} else {
				for (Dragon i : dragons) {
					if (hero.getCoord().x + 1 == i.getCoord().x
							&& hero.getCoord().y == i.getCoord().y) {
						i.setVisible();
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								" ");
						hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								hero.getId());
						break;
					}
				}
			}

		}
	}

	public void checkMoveUp() {
		if (maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
				.getY() - 1) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() - 1) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() - 1) == "e") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() - 1) == "D") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}
			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() - 1) == "d") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}
			} else {
				for (Dragon i : dragons) {
					if (hero.getCoord().x == i.getCoord().x
							&& hero.getCoord().y - 1 == i.getCoord().y) {
						i.setVisible();
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								" ");
						hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								hero.getId());
						break;
					}
				}
			}

		}
	}

	public void checkMoveDown() {
		if (maze.getCell((int) hero.getCoord().getX(), (int) hero.getCoord()
				.getY() + 1) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == "e") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == "D") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());

				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
				}

			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == "d") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons)
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						dragons.remove(dragon);
						break;
					}
			} else {
				for (Dragon i : dragons) {
					if (hero.getCoord().x == i.getCoord().x
							&& hero.getCoord().y + 1 == i.getCoord().y) {
						i.setVisible();
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								" ");
						hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
						maze.setMaze(
								new Point(hero.getCoord().x, hero.getCoord().y),
								hero.getId());
						break;
					}
				}
			}

		}
	}

	// Dragons Movements

	public void moveDragons() {
		for (Dragon i : dragons) {
			int direction = random.nextInt(4);
			int sleep = random.nextInt(2);

			switch (direction) {
			case 0: // up
				if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == " ") {
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x, i.getCoord().y - 1);
					maze.setMaze(i.getCoord(), i.getType());
				} else if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == "/") {
					sword.setVisible();
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x, i.getCoord().y - 1);
					maze.setMaze(i.getCoord(), "F");
					doubleCells.add(i.getCoord());
				}
				break;
			case 1: // down
				if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == " ") {
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x, i.getCoord().y + 1);
					maze.setMaze(i.getCoord(), i.getType());
				} else if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == "/") {
					sword.setVisible();
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x, i.getCoord().y + 1);
					maze.setMaze(i.getCoord(), "F");
					doubleCells.add(i.getCoord());
				}
				break;
			case 2: // right
				if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == " ") {
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x + 1, i.getCoord().y);
					maze.setMaze(i.getCoord(), i.getType());
				} else if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == "/") {
					sword.setVisible();
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x + 1, i.getCoord().y);
					maze.setMaze(i.getCoord(), "F");
					doubleCells.add(i.getCoord());
				}
				break;
			case 3: // left
				if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == " ") {
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x - 1, i.getCoord().y);
					maze.setMaze(i.getCoord(), i.getType());
				} else if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == "/") {
					sword.setVisible();
					maze.setMaze(i.getCoord(), " ");
					i.setCoord(i.getCoord().x - 1, i.getCoord().y);
					maze.setMaze(i.getCoord(), "F");
					doubleCells.add(i.getCoord());
				}
				break;
			default:
				break;

			}
		}
	}

	public void sleepDragons() {
		int sleep = random.nextInt(2);
		if (dragons.size() > 1) {
			int position = random.nextInt(dragons.size());
			if (sleep == 0)
				dragons.get(position).setAsleep();
			else
				dragons.get(position).setAwake();
		}
	}

	// Populate lab with the elements

	public void initializePositionsElements(int number_dragons) {

		maze.createMaze();
		checkEmptyCells();
		choosePositionHero();
		choosePositionSword();
		choosePositionShield();
		choosePositionDragons(number_dragons);

	}

	public void checkEmptyCells() {
		for (int i = 0; i < maze.height; i++)
			for (int j = 0; j < maze.width; j++)
				if (maze.getCell(i, j).equals(" "))
					emptyCells.add(new Point(i, j));

	}

	public void choosePositionHero() {
		int position = random.nextInt(emptyCells.size());
		hero.setCoord(emptyCells.elementAt(position).x,
				emptyCells.elementAt(position).y);
		maze.setMaze(
				new Point(emptyCells.get(position).x,
						emptyCells.get(position).y), "H");
		emptyCells.remove(position);
	}

	public void choosePositionSword() {
		int position = random.nextInt(emptyCells.size());
		sword.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
		maze.setMaze(
				new Point(emptyCells.get(position).x,
						emptyCells.get(position).y), "/");
		emptyCells.remove(position);
	}

	public void choosePositionShield() {
		int position = random.nextInt(emptyCells.size());
		shield.setCoord(emptyCells.get(position).x, emptyCells.get(position).y);
		maze.setMaze(
				new Point(emptyCells.get(position).x,
						emptyCells.get(position).y), "e");
		emptyCells.remove(position);
	}

	public void choosePositionDragons(int number_dragons) {
		for (int i = 0; i < number_dragons; i++) {
			int position = random.nextInt(emptyCells.size());
			dragons.get(i).setCoord(emptyCells.get(position).x,
					emptyCells.get(position).y);
			maze.setMaze(
					new Point(emptyCells.get(position).x, emptyCells
							.get(position).y), dragons.get(i).getType());
			emptyCells.remove(position);
		}
	}

	public void printGame() {
		for (int i = 0; i < maze.height; i++) {
			System.out.print("  ");
			for (int j = 0; j < maze.width; j++) {
				System.out.print(maze.getCell(j, i) + ' ');
			}
			System.out.print("\n");
		}
	}

}
