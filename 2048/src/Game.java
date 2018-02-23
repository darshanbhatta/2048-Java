/**  
 * 2048, simple text based game  
 * @author  Darshan Bhatta
 * @version 1.0 
 */

import java.util.Scanner;

public class Game {
	// game board
	int[][] board;
	final int COLUMN = 4;
	final int ROW = 4;
	// checks to see how far you can adjust 2d array when user selects a certain
	// move
	int boundary = 0;

	// used to get move, graphics will come later
	public static void main(String[] args) {
		Game game = new Game();
		Scanner kb = new Scanner(System.in);
		String quit = "";
		do {
			System.out.println(quit);
			game.spawnA2();
			game.printBoard();
			quit = kb.nextLine();

			if (quit.equals("w")) {
				game.moveUp();

			} else if (quit.equals("s")) {
				game.moveDown();

			} else if (quit.equals("a")) {
				game.moveLeft();

			} else if (quit.equals("d")) {
				game.moveRight();

			} else {
				quit = "N";
			}

		} while (!quit.equals("N"));

		System.out.println("Game over");

	}

	// creates game board
	public Game() {
		board = new int[ROW][COLUMN];

	}

	// prints output in a systematic format
	public void printBoard() {
		String output = "";
		for (int i = 0; i < ROW; i++) {
			String row = "";
			for (int j = 0; j < COLUMN; j++) {
				row += board[i][j] + " ";
			}
			row += "\n";
			output += row;
		}
		System.out.println(output);
	}

	// spawns a 2 in the game board randomly when there is space
	public void spawnA2() {

		boolean foundEmpty = true;

		while (foundEmpty) {
			int ranRow = (int) (Math.random() * 3);
			int ranCol = (int) (Math.random() * 3);
			if (board[ranRow][ranCol] == 0) {
				board[ranRow][ranCol] = 2;
				foundEmpty = false;

			}

		}

	}

	// handles moving up
	public void moveUp() {
		for (int col = 0; col < COLUMN; col++) {
			boundary = 0;
			for (int row = 0; row < ROW; row++) {
				// if not zero, tries to add the digits
				if (board[row][col] != 0) {
					// when space, it moves the numbers as far as it can and adds
					if (boundary <= row) {
						funcUpandDown(row, col, false);
					}
				}
			}
		}
	}

	// handles moving down
	public void moveDown() {
		for (int col = 0; col < COLUMN; col++) {
			boundary = ROW - 1;
			for (int row = ROW - 1; row >= 0; row--) {
				// if not zero, tries to add the digits
				if (board[row][col] != 0) {
					// when space, it moves the numbers as far as it can and adds
					if (boundary >= row) {
						funcUpandDown(row, col, true);
					}
				}
			}
		}
	}

	// handles moving left
	public void moveLeft() {
		for (int row = 0; row < ROW; row++) {
			boundary = 0;
			for (int col = 0; col < COLUMN; col++) {
				// if not zero, tries to add the digits
				if (board[row][col] != 0) {
					// when space, it moves the numbers as far as it can and adds
					if (boundary <= col) {
						funcLeftandRight(row, col, false);
					}
				}
			}
		}
	}

	// handles moving right
	public void moveRight() {
		for (int row = 0; row < ROW; row++) {
			boundary = COLUMN - 1;
			for (int col = COLUMN - 1; col >= 0; col--) {
				// if not zero, tries to add the digits
				if (board[row][col] != 0) {
					// when space, it moves the numbers as far as it can and adds
					if (boundary >= col) {
						funcLeftandRight(row, col, true);
					}
				}
			}
		}
	}

	// handles up and down moves
	private void funcUpandDown(int currentRow, int currentCol, boolean upOrDown) {
		// if we have same tiles we suck them in
		if (board[boundary][currentCol] == 0 || board[boundary][currentCol] == board[currentRow][currentCol]) {
			if (currentRow > boundary || (upOrDown && (boundary > currentRow))) {
				board[boundary][currentCol] += board[currentRow][currentCol];
				board[currentRow][currentCol] = 0;
			}
		} else {
			if (upOrDown) {
				boundary--;
			} else {
				boundary++;
			}
			funcUpandDown(currentRow, currentCol, upOrDown);

		}
	}

	// handles left and right moves
	private void funcLeftandRight(int currentRow, int currentCol, boolean upOrDown) {
		// if we have same tiles we suck them in
		if (board[currentRow][boundary] == 0 || board[currentRow][boundary] == board[currentRow][currentCol]) {
			if (currentCol > boundary || (upOrDown && (boundary > currentCol))) {
				board[currentRow][boundary] += board[currentRow][currentCol];
				board[currentRow][currentCol] = 0;
			}
		} else {
			if (upOrDown) {
				boundary--;
			} else {
				boundary++;
			}
			funcLeftandRight(currentRow, currentCol, upOrDown);
		}
	}

}
