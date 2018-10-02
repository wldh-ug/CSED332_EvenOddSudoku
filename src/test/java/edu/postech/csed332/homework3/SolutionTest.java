package edu.postech.csed332.homework3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.postech.csed332.homework3.Solution.Difference;

/**
 * Test the behavior of the class Solution, including getValue().
 */
public class SolutionTest {
	private static Logger log = LoggerFactory.getLogger(SolutionTest.class);

	/**
	 * Tests constructor of "Solution" class: load from file / variable tested in
	 * SudokuTest.testSolve.
	 */
	@Test
	public void testConstructor() throws Exception {

		log.info("Testing constructor...");

		log.info("Test 1: Normal solution file");
		Sudoku spenceGame = new Sudoku("examples/spence.sudoku");
		Solution spenceGoldenSolution = new Solution("examples/spence.golden.solution");

		assertTrue(spenceGoldenSolution.existsIn(spenceGame.solve()));

		log.info("Test 2: Over/undersized file");
		new Solution("examples/wrong/big.solution"); // There must no exception be occurred
		assertThrows(IOException.class, () -> new Solution("examples/small.solution"));

		log.info("Test 3: Invalid solution file");
		assertThrows(IOException.class, () -> new Solution("examples/invalid-number.solution"));
		assertThrows(IOException.class, () -> new Solution("examples/invalid-char.solution"));

		log.info("Test 4: Non-file");

		assertThrows(IOException.class, () -> new Sudoku("examples/"));
		assertThrows(IOException.class, () -> new Sudoku("--non-exist-dir-or-file--"));

		log.info("Test 5: Non-ASCII-named file");
		Solution hangulSolution = new Solution("examples/얼릭.golden.solution");

		assertEquals(4, hangulSolution.getValue(9, 8).intValue());

		log.info("Test 6: Empty filename / variable");

		new Solution((int[][]) null); // There must be no exception be occurred
		assertThrows(IOException.class, () -> new Solution((String) null));
		assertThrows(IOException.class, () -> new Solution(""));

		log.info("Test completed!");

	}

	/**
	 * This test checks if getValue method of Solution works correctly.
	 */
	@Test
	public void testGetValue() throws Exception {

		log.info("Test getValue function...");

		Solution invalidSolution = new Solution("examples/wrong/big.solution");

		assertNull(invalidSolution.getValue(0, 0));
		assertNull(invalidSolution.getValue(100, 10));

		assertEquals(2, invalidSolution.getValue(1, 1).intValue());
		assertEquals(3, invalidSolution.getValue(1, 2).intValue());
		assertEquals(8, invalidSolution.getValue(1, 3).intValue());
		assertEquals(9, invalidSolution.getValue(1, 4).intValue());
		assertEquals(6, invalidSolution.getValue(1, 5).intValue());
		assertEquals(5, invalidSolution.getValue(1, 6).intValue());
		assertEquals(4, invalidSolution.getValue(1, 7).intValue());
		assertEquals(7, invalidSolution.getValue(1, 8).intValue());
		assertEquals(1, invalidSolution.getValue(1, 9).intValue());

		assertEquals(7, invalidSolution.getValue(2, 1).intValue());
		assertEquals(6, invalidSolution.getValue(2, 2).intValue());
		assertEquals(4, invalidSolution.getValue(2, 3).intValue());
		assertEquals(3, invalidSolution.getValue(2, 4).intValue());
		assertEquals(1, invalidSolution.getValue(2, 5).intValue());
		assertEquals(8, invalidSolution.getValue(2, 6).intValue());
		assertEquals(2, invalidSolution.getValue(2, 7).intValue());
		assertEquals(9, invalidSolution.getValue(2, 8).intValue());
		assertEquals(5, invalidSolution.getValue(2, 9).intValue());

		assertEquals(1, invalidSolution.getValue(3, 1).intValue());
		assertEquals(9, invalidSolution.getValue(3, 2).intValue());
		assertEquals(5, invalidSolution.getValue(3, 3).intValue());
		assertEquals(2, invalidSolution.getValue(3, 4).intValue());
		assertEquals(4, invalidSolution.getValue(3, 5).intValue());
		assertEquals(7, invalidSolution.getValue(3, 6).intValue());
		assertEquals(3, invalidSolution.getValue(3, 7).intValue());
		assertEquals(8, invalidSolution.getValue(3, 8).intValue());
		assertEquals(6, invalidSolution.getValue(3, 9).intValue());

		assertEquals(5, invalidSolution.getValue(4, 1).intValue());
		assertEquals(7, invalidSolution.getValue(4, 2).intValue());
		assertEquals(2, invalidSolution.getValue(4, 3).intValue());
		assertEquals(1, invalidSolution.getValue(4, 4).intValue());
		assertEquals(8, invalidSolution.getValue(4, 5).intValue());
		assertEquals(3, invalidSolution.getValue(4, 6).intValue());
		assertEquals(9, invalidSolution.getValue(4, 7).intValue());
		assertEquals(6, invalidSolution.getValue(4, 8).intValue());
		assertEquals(4, invalidSolution.getValue(4, 9).intValue());

		assertEquals(3, invalidSolution.getValue(5, 1).intValue());
		assertEquals(8, invalidSolution.getValue(5, 2).intValue());
		assertEquals(9, invalidSolution.getValue(5, 3).intValue());
		assertEquals(4, invalidSolution.getValue(5, 4).intValue());
		assertEquals(2, invalidSolution.getValue(5, 5).intValue());
		assertEquals(6, invalidSolution.getValue(5, 6).intValue());
		assertEquals(5, invalidSolution.getValue(5, 7).intValue());
		assertEquals(1, invalidSolution.getValue(5, 8).intValue());
		assertEquals(7, invalidSolution.getValue(5, 9).intValue());

		assertEquals(4, invalidSolution.getValue(6, 1).intValue());
		assertEquals(1, invalidSolution.getValue(6, 2).intValue());
		assertEquals(6, invalidSolution.getValue(6, 3).intValue());
		assertEquals(5, invalidSolution.getValue(6, 4).intValue());
		assertEquals(7, invalidSolution.getValue(6, 5).intValue());
		assertEquals(9, invalidSolution.getValue(6, 6).intValue());
		assertEquals(8, invalidSolution.getValue(6, 7).intValue());
		assertEquals(2, invalidSolution.getValue(6, 8).intValue());
		assertEquals(3, invalidSolution.getValue(6, 9).intValue());

		assertEquals(9, invalidSolution.getValue(7, 1).intValue());
		assertEquals(4, invalidSolution.getValue(7, 2).intValue());
		assertEquals(1, invalidSolution.getValue(7, 3).intValue());
		assertEquals(7, invalidSolution.getValue(7, 4).intValue());
		assertEquals(3, invalidSolution.getValue(7, 5).intValue());
		assertEquals(2, invalidSolution.getValue(7, 6).intValue());
		assertEquals(6, invalidSolution.getValue(7, 7).intValue());
		assertEquals(5, invalidSolution.getValue(7, 8).intValue());
		assertEquals(8, invalidSolution.getValue(7, 9).intValue());

		assertEquals(6, invalidSolution.getValue(8, 1).intValue());
		assertEquals(5, invalidSolution.getValue(8, 2).intValue());
		assertEquals(7, invalidSolution.getValue(8, 3).intValue());
		assertEquals(8, invalidSolution.getValue(8, 4).intValue());
		assertEquals(9, invalidSolution.getValue(8, 5).intValue());
		assertEquals(4, invalidSolution.getValue(8, 6).intValue());
		assertEquals(1, invalidSolution.getValue(8, 7).intValue());
		assertEquals(3, invalidSolution.getValue(8, 8).intValue());
		assertEquals(2, invalidSolution.getValue(8, 9).intValue());

		assertEquals(8, invalidSolution.getValue(9, 1).intValue());
		assertEquals(2, invalidSolution.getValue(9, 2).intValue());
		assertEquals(3, invalidSolution.getValue(9, 3).intValue());
		assertEquals(6, invalidSolution.getValue(9, 4).intValue());
		assertEquals(5, invalidSolution.getValue(9, 5).intValue());
		assertEquals(1, invalidSolution.getValue(9, 6).intValue());
		assertEquals(7, invalidSolution.getValue(9, 7).intValue());
		assertEquals(4, invalidSolution.getValue(9, 8).intValue());
		assertEquals(9, invalidSolution.getValue(9, 9).intValue());

		log.info("Test completed!");

	}

	/**
	 * Tests if export method works correctly. Simple check in this, and complex check will be
	 * proceeded in GameTest.testFileIO.
	 */
	@Test
	public void testExport() throws Exception {

		log.info("Testing export method...");

		Solution footeSolution = new Solution("examples/foote.golden.solution");

		log.info("Test 1: general exporting");

		assertTrue(footeSolution.export("examples/foote.test.solution"));

		log.info("Test 2: invalid exporting");

		assertFalse(footeSolution.export(null));

		log.info("Test 3: file error");

		assertFalse(footeSolution.export("examples/"));

		log.info("Test completed!");

	}

	/**
	 * This test checks if compareWith method of Solution works correctly.
	 */
	@Test
	public void testCompareWith() throws Exception {

		log.info("Testing compareWith method...");

		Solution thatcherSolution = new Solution("examples/thatcher.golden.solution");
		Solution yarnallSolution = new Solution("examples/yarnall.golden.solution");

		assertEquals(0, yarnallSolution.compareWith(null).size());
		assertEquals(77, yarnallSolution.compareWith(thatcherSolution).size());

		log.info("Test completed!");

	}

	/**
	 * This test checks if existsIn method of Solution works correctly.
	 */
	@Test
	public void testExistsIn() throws Exception {

		log.info("Testing existsIn method...");

		Solution thatcherSolution = new Solution("examples/thatcher.golden.solution");
		Solution terryGoldenSolution = new Solution("examples/terry.golden.solution");
		Set<Solution> terrySolutions = new Sudoku("examples/terry.blind.sudoku").solve();

		assertFalse(thatcherSolution.existsIn(terrySolutions));
		assertTrue(terryGoldenSolution.existsIn(terrySolutions));

		log.info("Test completed!");

	}

	/**
	 * This test checks if constructor of Difference in Solution works correctly.
	 */
	@Test
	public void testDifferenceConstructor() throws Exception {

		log.info("Testing Difference class...");

		Solution thatcherSolution = new Solution("examples/thatcher.golden.solution");
		Solution terrySolution = new Solution("examples/terry.golden.solution");

		Difference diff = new Difference(thatcherSolution, terrySolution, 1, 1);

		assertEquals(5, diff.sourceValue);
		assertEquals(3, diff.otherValue);

		assertEquals(-1, new Difference(null, null, 0, 0).sourceValue);
		assertEquals(-1, new Difference(null, null, 0, 0).otherValue);

		log.info("Test completed!");

	}

	/**
	 * This test checks if equals method of Solution works correctly.
	 */
	@Test
	public void testEquals() throws Exception {

		log.info("Testing equals method...");

		Solution thatcherSolution = new Solution("examples/thatcher.golden.solution");
		Solution terrySolution = new Solution("examples/terry.golden.solution");
		Solution terryBlindSolution = new Solution("examples/terry.blind_1.golden.solution");

		assertFalse(thatcherSolution.equals(terrySolution));
		assertFalse(thatcherSolution.equals(null));
		assertTrue(terrySolution.equals(terryBlindSolution));

		log.info("Test completed!");

	}

}
