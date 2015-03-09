package maze.logic;

import java.util.Random;
import java.util.Stack;

public class Labirinto {
	// camps
	protected int sizex;
	protected int sizey;
	protected String[][] lab;
	protected String[][] visitedCells;
	protected int visitedCellsDimension;
	protected Stack<Coordenadas> pathHistory;

	// constructor
	public Labirinto(int x, int y) {
		this.sizex = x;
		this.sizey = y;
		this.lab = new String[50][50];
		this.visitedCells = new String[30][30];
		this.visitedCellsDimension = (sizex - 1) / 2;
		this.pathHistory = new Stack<Coordenadas>();
	}

	public int getX() {
		return this.sizex;
	}

	public int getY() {
		return this.sizey;
	}

	public void setLab(int x, int y, String change) {
		this.lab[x][y] = change;
	}

	// Create map
	public int createLab() {
		Random rand = new Random();

		// fill everything with walls
		for (int i = 0; i < sizex; i++) {
			for (int j = 0; j < sizey; j++) {
				setLab(i, j, "X");
			}
		}

		// put the cells that have odd coordinates with " "
		for (int i = 0; i < sizex - 1; i++) {
			for (int j = 0; j < sizey - 1; j++) {
				if ((i % 2) != 0 && (j % 2) != 0)
					setLab(i, j, " ");
			}
		}

		// Randomly get the exit
		generateExit();

		// Visited Cells

		// fill the visited cells array

		for (int i = 0; i < visitedCellsDimension; i++) {
			for (int j = 0; j < visitedCellsDimension; j++) {
				visitedCells[i][j] = ".";
			}
		}

		int line = rand.nextInt(visitedCellsDimension - 1), collumn = rand
				.nextInt(visitedCellsDimension - 1);

		visitedCells[line][collumn] = "+";

		Coordenadas guideCell = new Coordenadas(line, collumn);
		pathHistory.push(guideCell);



		return 0;
	}

	public int generateExit() {
		Random rand = new Random();
		int r = rand.nextInt(3);
		if (r == 0) {
			// collumn = 0
			int line = rand.nextInt(sizex - 1);
			setLab(line, 0, "S");

		} else if (r == 1) {
			// line = 0
			int collumn = rand.nextInt(sizey - 1);
			setLab(0, collumn, "S");

		} else if (r == 2) {
			// collumn = sizey - 1
			int line = rand.nextInt(sizex - 1);
			setLab(line, sizey - 1, "S");

		} else if (r == 3) {
			// line = sizex - 1
			int collumn = rand.nextInt(sizey - 1);
			setLab(sizex - 1, collumn, "S");
		}
		return 0;
	}

	public boolean checkVisitedCells(int x, int y) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (visitedCells[i][j] != "+")
					return false;
			}
		}
		return true;
	}

	public boolean checkIfCantMove(int direction, int line, int collumn) {
		if (direction == 0) {
			if (collumn + 1 >= visitedCellsDimension) {
				return true;
			}
		} else if (direction == 1) {
			if (collumn - 1 < 0) {
				return true;
			}
		} else if (direction == 2) {
			if (line + 1 >= visitedCellsDimension) {
				return true;
			}
		} else if (direction == 3) {
			if (line - 1 < 0) {
				return true;
			}
		}
		return false;
	}

	// Print map
	public void printLabirinto() {
		for (int i = 0; i < getX(); i++) {
			for (int j = 0; j < getY(); j++) {
				System.out.print(this.lab[i][j]);
			}
			System.out.print("\n");
		}
	}

	public static void main(String args[]) {
		Labirinto maze = new Labirinto(7, 7);
		maze.createLab();
		maze.printLabirinto();
	}

}
