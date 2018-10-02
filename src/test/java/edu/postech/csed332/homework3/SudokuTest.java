package edu.postech.csed332.homework3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.postech.csed332.homework3.Game.WrongGameException;

/**
 * Test the behavior of the class Sudoku.
 */
public class SudokuTest {
	private static Logger log = LoggerFactory.getLogger(SudokuTest.class);

	/**
	 * This test read some sudokus from file and check it.
	 */
	@Test
	public void testConstructor() throws IOException {

		log.info("Testing constructor...");

		log.info("Test 1: Normal file & its data");
		Sudoku spenceGame = new Sudoku("examples/spence.sudoku");
		char[][] spenceBoard = spenceGame.getRawBoard();
		char[][] spenceBoardRaw = {{'*', '.', '9', '.', '*', '*', '*', '.', '.'},
				{'.', '8', '*', '*', '.', '1', '.', '9', '2'},
				{'5', '*', '.', '.', '9', '6', '*', '8', '.'},
				{'9', '.', '5', '.', '*', '*', '.', '*', '*'},
				{'2', '*', '*', '.', '.', '.', '.', '.', '4'},
				{'.', '.', '*', '*', '*', '.', '1', '.', '8'},
				{'.', '9', '.', '6', '7', '*', '*', '*', '5'},
				{'8', '5', '.', '9', '*', '.', '*', '6', '.'},
				{'*', '*', '*', '*', '.', '.', '3', '.', '.'}};

		assertTrue(Arrays.deepEquals(spenceBoardRaw, spenceBoard));

		log.info("Test 2: Over/undersized file (No throw)");
		Sudoku wrongSizeGame = new Sudoku("examples/wrong/size.sudoku");

		assertNull(wrongSizeGame.getValue(5, 9));
		assertEquals(3, wrongSizeGame.getValue(5, 1).intValue());

		log.info("Test 3: Non-file");

		assertThrows(IOException.class, () -> new Sudoku("examples/"));
		assertThrows(IOException.class, () -> new Sudoku("--non-exist-dir-or-file--"));

		log.info("Test 4: Non-ASCII-named file");
		Sudoku hangulGame = new Sudoku("examples/얼릭.sudoku");

		assertEquals(4, hangulGame.getValue(9, 8).intValue());

		log.info("Test 5: Empty filename");

		assertThrows(IOException.class, () -> new Sudoku(null));
		assertThrows(IOException.class, () -> new Sudoku(""));

		log.info("Test completed!");

	}

	/**
	 * This test checks if the solve method works correctly. In this test, you should create a
	 * specific sudoku puzzle, call the solve method, and checks whether a set of correct solutions
	 * is obtained.
	 */
	@Test
	public void testSolve() throws Exception {

		log.info("Testing solver...");

		log.info("Test 1: Single answers (aulick)");
		Sudoku aulickGame = new Sudoku("examples/aulick.sudoku");
		Set<Solution> aulickSolutions = aulickGame.solve();

		assertEquals(1, aulickSolutions.size());
		assertTrue(new Solution("examples/aulick.golden.solution").existsIn(aulickSolutions));

		log.info("Test 2: Lack of rows");
		Sudoku rowLackGame = new Sudoku("examples/wrong/small-row.sudoku");

		assertThrows(WrongGameException.class, () -> rowLackGame.solve());

		log.info("Test 3: Invalid number (x16)");
		Sudoku invalidNumberGame = new Sudoku("examples/wrong/invalid-full.sudoku");

		assertThrows(WrongGameException.class, () -> invalidNumberGame.solve());

		log.info("Test 4: Multiple answers (terry blind)");
		Sudoku terryGame = new Sudoku("examples/terry.blind.sudoku");
		Set<Solution> terrySolutions = terryGame.solve();

		assertEquals(6, terrySolutions.size());
		assertTrue(new Solution("examples/terry.blind_1.golden.solution").existsIn(terrySolutions));
		assertTrue(new Solution("examples/terry.blind_2.golden.solution").existsIn(terrySolutions));
		assertTrue(new Solution("examples/terry.blind_3.golden.solution").existsIn(terrySolutions));
		assertTrue(new Solution("examples/terry.blind_4.golden.solution").existsIn(terrySolutions));
		assertTrue(new Solution("examples/terry.blind_5.golden.solution").existsIn(terrySolutions));
		assertTrue(new Solution("examples/terry.blind_6.golden.solution").existsIn(terrySolutions));

		log.info("Test 5: Solving non-text file");
		assertThrows(WrongGameException.class,
				() -> new Sudoku("examples/wrong/content.sudoku").solve());

		log.info("Test completed!");

	}

	/**
	 * This test checks if the isEven method works correctly. It tests if, given an even/odd sudoku
	 * puzzle, isEven returns 'true' for even cells, and 'false' for odd cells.
	 */
	@Test
	public void testIsEven() throws Exception {

		log.info("Testing isEven function...");

		// NOTE: Non-ASCII character will be calculated as "two chars"!
		Sudoku invalidSudoku = new Sudoku("examples/wrong/invalid-part.sudoku");

		// Even
		assertTrue(invalidSudoku.isEven(1, 1).booleanValue());
		assertTrue(invalidSudoku.isEven(1, 3).booleanValue());

		// Odd
		assertFalse(invalidSudoku.isEven(7, 3).booleanValue());
		assertFalse(invalidSudoku.isEven(7, 4).booleanValue());

		// Out of range
		assertThrows(WrongGameException.class, () -> invalidSudoku.isEven(0, 5));
		assertThrows(WrongGameException.class, () -> invalidSudoku.isEven(100, 10));

		// Wrong character
		assertThrows(WrongGameException.class, () -> invalidSudoku.isEven(4, 5));
		assertThrows(WrongGameException.class, () -> invalidSudoku.isEven(7, 7));

		log.info("Test completed!");

	}

	/**
	 * This test checks if the getValue method works correctly. It tests if, given an even/odd
	 * Sudoku puzzle, getValue returns null for empty cells or invalid input, and designated number
	 * for cells with initial values.
	 */
	@Test
	public void testGetValue() throws Exception {

		log.info("Test getValue function...");

		Sudoku invalidSudoku = new Sudoku("examples/wrong/invalid-part.sudoku");

		assertNull(invalidSudoku.getValue(0, 0));
		assertNull(invalidSudoku.getValue(9, 9));
		assertNull(invalidSudoku.getValue(100, 10));

		assertEquals(2, invalidSudoku.getValue(1, 1).intValue());
		assertNull(invalidSudoku.getValue(1, 2));
		assertNull(invalidSudoku.getValue(1, 3));
		assertNull(invalidSudoku.getValue(1, 4));
		assertNull(invalidSudoku.getValue(1, 5));
		assertNull(invalidSudoku.getValue(1, 6));
		assertEquals(4, invalidSudoku.getValue(1, 7).intValue());
		assertEquals(7, invalidSudoku.getValue(1, 8).intValue());
		assertNull(invalidSudoku.getValue(1, 9));

		assertEquals(7, invalidSudoku.getValue(2, 1).intValue());
		assertNull(invalidSudoku.getValue(2, 2));
		assertNull(invalidSudoku.getValue(2, 3));
		assertNull(invalidSudoku.getValue(2, 4));
		assertNull(invalidSudoku.getValue(2, 5));
		assertNull(invalidSudoku.getValue(2, 6));
		assertNull(invalidSudoku.getValue(2, 7));
		assertNull(invalidSudoku.getValue(2, 8));
		assertNull(invalidSudoku.getValue(2, 9));

		assertNull(invalidSudoku.getValue(3, 1));
		assertEquals(9, invalidSudoku.getValue(3, 2).intValue());
		assertNull(invalidSudoku.getValue(3, 3));
		assertEquals(2, invalidSudoku.getValue(3, 4).intValue());
		assertEquals(4, invalidSudoku.getValue(3, 5).intValue());
		assertNull(invalidSudoku.getValue(3, 6));
		assertNull(invalidSudoku.getValue(3, 7));
		assertNull(invalidSudoku.getValue(3, 8));
		assertEquals(6, invalidSudoku.getValue(3, 9).intValue());

		assertNull(invalidSudoku.getValue(4, 1));
		assertNull(invalidSudoku.getValue(4, 2));
		assertNull(invalidSudoku.getValue(4, 3));
		assertEquals(1, invalidSudoku.getValue(4, 4).intValue());
		assertNull(invalidSudoku.getValue(4, 5));
		assertNull(invalidSudoku.getValue(4, 6));
		assertEquals(9, invalidSudoku.getValue(4, 7).intValue());
		assertNull(invalidSudoku.getValue(4, 8));
		assertNull(invalidSudoku.getValue(4, 9));

		assertEquals(3, invalidSudoku.getValue(5, 1).intValue());
		assertNull(invalidSudoku.getValue(5, 2));
		assertNull(invalidSudoku.getValue(5, 3));
		assertNull(invalidSudoku.getValue(5, 4));
		assertNull(invalidSudoku.getValue(5, 5));
		assertNull(invalidSudoku.getValue(5, 6));
		assertNull(invalidSudoku.getValue(5, 7));
		assertNull(invalidSudoku.getValue(5, 8));
		assertNull(invalidSudoku.getValue(5, 9));

		assertNull(invalidSudoku.getValue(6, 1));
		assertNull(invalidSudoku.getValue(6, 2));
		assertNull(invalidSudoku.getValue(6, 3));
		assertNull(invalidSudoku.getValue(6, 4));
		assertNull(invalidSudoku.getValue(6, 5));
		assertEquals(9, invalidSudoku.getValue(6, 6).intValue());
		assertNull(invalidSudoku.getValue(6, 7));
		assertEquals(2, invalidSudoku.getValue(6, 8).intValue());
		assertEquals(3, invalidSudoku.getValue(6, 9).intValue());

		assertEquals(9, invalidSudoku.getValue(7, 1).intValue());
		assertEquals(4, invalidSudoku.getValue(7, 2).intValue());
		assertEquals(1, invalidSudoku.getValue(7, 3).intValue());
		assertNull(invalidSudoku.getValue(7, 4));
		assertNull(invalidSudoku.getValue(7, 5));
		assertNull(invalidSudoku.getValue(7, 6));
		assertNull(invalidSudoku.getValue(7, 7));
		assertNull(invalidSudoku.getValue(7, 8));
		assertEquals(8, invalidSudoku.getValue(7, 9).intValue());

		assertNull(invalidSudoku.getValue(8, 1));
		assertNull(invalidSudoku.getValue(8, 2));
		assertNull(invalidSudoku.getValue(8, 3));
		assertNull(invalidSudoku.getValue(8, 4));
		assertNull(invalidSudoku.getValue(8, 5));
		assertNull(invalidSudoku.getValue(8, 6));
		assertNull(invalidSudoku.getValue(8, 7));
		assertNull(invalidSudoku.getValue(8, 8));
		assertNull(invalidSudoku.getValue(8, 9));

		assertNull(invalidSudoku.getValue(9, 1));
		assertNull(invalidSudoku.getValue(9, 2));
		assertNull(invalidSudoku.getValue(9, 3));
		assertEquals(6, invalidSudoku.getValue(9, 4).intValue());
		assertEquals(5, invalidSudoku.getValue(9, 5).intValue());
		assertNull(invalidSudoku.getValue(9, 6));

		log.info("Test completed!");

	}

}
