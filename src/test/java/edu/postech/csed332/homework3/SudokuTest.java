package edu.postech.csed332.homework3;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the behavior of the class Sudoku.
 */
public class SudokuTest {
	private static Logger log = LoggerFactory.getLogger(SudokuTest.class);

	/**
	 * This test read some sudokus from file and check it.
	 */
	@Test
	public void testConstructor() throws Exception {

		log.info("Testing Constructors");

		Sudoku spenceGame = new Sudoku("example/spence.sudoku");

		char[][] spenceBoard = spenceGame.getRawBoard();
		char[][] spenceBoardRaw = {
			{ '*', '.', '9', '.', '*', '*', '*', '.', '.' },
			{ '.', '8', '*', '*', '.', '1', '.', '9', '2' },
			{ '5', '*', '.', '.', '9', '6', '*', '8', '.' },
			{ '9', '.', '5', '.', '*', '*', '.', '*', '*' },
			{ '2', '*', '*', '.', '.', '.', '.', '.', '4' },
			{ '.', '.', '*', '*', '*', '.', '1', '.', '8' },
			{ '.', '9', '.', '6', '7', '*', '*', '*', '5' },
			{ '8', '5', '.', '9', '*', '.', '*', '6', '.' },
			{ '*', '*', '*', '*', '.', '.', '3', '.', '.' }
		};

		log.debug("Spence Array [Expected] = {}", Arrays.toString(spenceBoardRaw));
		log.debug("Spence Array [Result] = {}", Arrays.toString(spenceBoard));

		assertTrue(Arrays.deepEquals(spenceBoardRaw, spenceBoard));

	}

	/**
	 * This test checks if the solve method works correctly. In this test, you should create a
	 * specific sudoku puzzle, call the solve method, and checks whether a set of correct solutions
	 * is obtained.
	 */
	@Test
	public void testSolve() {
		// TODO implement this
	}

	/**
	 * This test checks if the isEven method works correctly. It tests if, given an even/odd sudoku
	 * puzzle, isEven returns 'true' for even cells, and 'false' for odd cells.
	 */
	@Test
	public void testIsEven() {
		// TODO implement this
	}

	/**
	 * This test checks if the getValue method works correctly. It tests if, given an even/odd
	 * Sudoku puzzle, getValue returns null for empty cells or invalid input, and designated number
	 * for cells with initial values.
	 */
	@Test
	public void testGetValue() {
		// TODO implement this
	}
}
