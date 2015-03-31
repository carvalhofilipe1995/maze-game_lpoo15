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
	private boolean HeroMoves;
	private Vector<Point> emptyCells;

	public Game() {

		maze = new MazeGenerator(11, 11);
		hero = new Hero(0, 0);
		sword = new Sword(0, 0);
		shield = new Shield(0, 0);

		GameState = false;

		exitOpen = false;

		emptyCells = new Vector<Point>(0, 1);

		dragons = new ArrayList<Dragon>();
		dragons.add(new Dragon(0, 0));
		dragons.add(new Dragon(0, 0));
		dragons.add(new Dragon(0, 0));
	}

	public Game(int height, int width, int number_dragons, int type_dragons) {

		maze = new MazeGenerator(height, width);
		hero = new Hero(0, 0);
		sword = new Sword(0, 0);
		shield = new Shield(0, 0);

		GameState = false;

		exitOpen = false;

		emptyCells = new Vector<Point>(0, 1);

		dragons = new ArrayList<Dragon>();

		for (int i = 0; i < number_dragons; i++) {
			dragons.add(new Dragon(0, 0, true, type_dragons));
		}

	}
	
	public boolean getGameState(){
		return GameState;
	}
	
	public Hero getHero(){
		return hero;
	}

	// Update Game State

	public boolean updateGameState(String direction, int type_dragons) {

		boolean done = false;

		/*if (maze.getCell((int) hero.getCoord().x, (int) hero.getCoord().y) == "S") {
			GameState = true;
			done = true;
		}

		if (dragons.size() == 0)
			exitOpen = true;*/

		moveDragons(type_dragons);

		heroCanMove(direction);

		return done;

	}

	// Hero Movements

	public boolean heroCanMove(String direction) {
		switch (direction) {
		case "a":
			if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero
					.getCoord().getY()) != "S") {
				checkMoveLeft();
				return true;
			}
			break;
		case "d":
			if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero
					.getCoord().getY()) != "S") {
				checkMoveRight();
				return true;
			}
			break;
		case "w":
			if (maze.getCell((int) hero.getCoord().getX(), (int) hero
					.getCoord().getY() - 1) != "S") {
				checkMoveUp();
				return true;
			}
			break;
		case "s":
			if (maze.getCell((int) hero.getCoord().getX(), (int) hero
					.getCoord().getY() + 1) != "S") {
				checkMoveDown();
				return true;
			}
			break;
		default:
			return true;
		}
		return false;
	}

	public void checkMoveLeft() {
		if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero.getCoord()
				.getY()) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero.getCoord()
				.getY()) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero.getCoord()
				.getY()) == "e") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero.getCoord()
				.getY()) == "D") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			if (hero.hasSword()) {
				hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						maze.setMaze(dragon.getCoord(), " ");
						dragons.remove(dragon);
					}
				}
			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX() - 1, (int) hero.getCoord()
				.getY()) == "d") {
			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x - 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
					}
				}
			}

		}
	}

	public void checkMoveRight() {
		if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero.getCoord()
				.getY()) == " ") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());
		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero.getCoord()
				.getY()) == "/") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			hero.grabSword();
			sword.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero.getCoord()
				.getY()) == "e") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero.getCoord()
				.getY()) == "D") {
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			if (hero.hasSword()) {
				hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						maze.setMaze(dragon.getCoord(), " ");
						dragons.remove(dragon);
					}
				}
			} else {
				hero.kill();
			}

		} else if (maze.getCell((int) hero.getCoord().getX() + 1, (int) hero.getCoord()
				.getY()) == "d") {

			if (hero.hasSword()) {
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						" ");
				hero.setCoord(hero.getCoord().x + 1, hero.getCoord().y);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons)
					if (dragon.getCoord().equals(hero.getCoord()))
						dragon.kill();
			}

		}
	}

	public void checkMoveUp() {
		if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() - 1) == " ") {
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

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			if (hero.hasSword()) {
				hero.setCoord(hero.getCoord().x, hero.getCoord().y - 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons) {
					if (dragon.getCoord().equals(hero.getCoord())) {
						dragon.kill();
						maze.setMaze(dragon.getCoord(), " ");
						dragons.remove(dragon);
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
					}
				}
			}

		}
	}

	public void checkMoveDown() {
		if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == " ") {
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
				.getCoord().getY() + 1) == "E") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
			hero.grabShield();
			shield.pickUp();
			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
					hero.getId());

		} else if (maze.getCell((int) hero.getCoord().getX(), (int) hero
				.getCoord().getY() + 1) == "D") {

			maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y), " ");
			if (hero.hasSword()) {
				hero.setCoord(hero.getCoord().x, hero.getCoord().y + 1);
				maze.setMaze(new Point(hero.getCoord().x, hero.getCoord().y),
						hero.getId());
				for (Dragon dragon : dragons){
					if (dragon.getCoord().equals(hero.getCoord())){
						dragon.kill();
						maze.setMaze(dragon.getCoord(), " ");
						dragons.remove(dragon);
					}
				}

			} else{
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
					if (dragon.getCoord().equals(hero.getCoord()))
						dragon.kill();
			}

		}
	}

	// Dragons Movements

	public void moveDragons(int type) {
		if (type == 2) { // room
			for (Dragon i : dragons) {
				int direction = random.nextInt(4);
				int sleep = random.nextInt(2);

				switch (direction) {
				case 0: // up
					if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == " "
							|| maze.getCell(i.getCoord().x, i.getCoord().y - 1) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x, i.getCoord().y - 1);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 1: // down
					if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == " "
							|| maze.getCell(i.getCoord().x, i.getCoord().y + 1) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x, i.getCoord().y + 1);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 2: // right
					if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == " "
							|| maze.getCell(i.getCoord().x + 1, i.getCoord().y) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x + 1, i.getCoord().y);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 3: // left
					if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == " "
							|| maze.getCell(i.getCoord().x - 1, i.getCoord().y) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x - 1, i.getCoord().y);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				default:
					break;

				}
			}
		} else if (type == 3) { // room and sleep
			for (Dragon i : dragons) {
				int direction = random.nextInt(4);
				int sleep = random.nextInt(2);

				switch (direction) {
				case 0: // up
					if (maze.getCell(i.getCoord().x, i.getCoord().y - 1) == " "
							|| maze.getCell(i.getCoord().x, i.getCoord().y - 1) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x, i.getCoord().y - 1);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 1: // down
					if (maze.getCell(i.getCoord().x, i.getCoord().y + 1) == " "
							|| maze.getCell(i.getCoord().x, i.getCoord().y + 1) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x, i.getCoord().y + 1);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 2: // right
					if (maze.getCell(i.getCoord().x + 1, i.getCoord().y) == " "
							|| maze.getCell(i.getCoord().x + 1, i.getCoord().y) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x + 1, i.getCoord().y);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				case 3: // left
					if (maze.getCell(i.getCoord().x - 1, i.getCoord().y) == " "
							|| maze.getCell(i.getCoord().x - 1, i.getCoord().y) == "E") {
						maze.setMaze(i.getCoord(), " ");
						i.setCoord(i.getCoord().x - 1, i.getCoord().y);
						maze.setMaze(i.getCoord(), "D");
						i.setType(sleep);
					}
					break;
				default:
					break;

				}
			}
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
							.get(position).y), "D");
			emptyCells.remove(position);
		}
	}
	
	public void printGame(){
		for (int i = 0; i < maze.height; i++) {
			System.out.print("  ");
			for (int j = 0; j < maze.width; j++) {
				System.out.print(maze.getCell(j,i) + ' ');
			}
			System.out.print("\n");
		}
	}

}
