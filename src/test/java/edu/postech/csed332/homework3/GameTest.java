package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Test;

/**
 * Test the behavior of the class Game, including file I/O. For example, what should happen if an
 * input file is invalid?
 */
public class GameTest {

	/**
	 * This test runs main() method in user interface level, e.g., 1. Read the input file which has
	 * a sudoku puzzle. 2. Write the solution files of the given puzzle. 3. Validate the solution
	 * files. After the test, you have to delete all output files created during the test.
	 */
	@Test
	public void testFileIO() {

		// TODO implement this
		// HACK What will be tested
		// * Non ASCII Files
		// * Files in various encodings
		// * Malformed files
		// * Non-text type files
		// * Empty file

		String[] test = {"1", "2"};

		Game.main(test);

	}

}
