package edu.postech.csed332.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.joran.action.LoggerAction;

/**
 * Test the behavior of the class Game, including file I/O. For example, what should happen if an
 * input file is invalid?
 */
public class GameTest {
	private static Logger log = LoggerFactory.getLogger(GameTest.class);

	/**
	 * This test runs main() method in user interface level, e.g., 1. Read the input file which has
	 * a sudoku puzzle. 2. Write the solution files of the given puzzle. 3. Validate the solution
	 * files. After the test, you have to delete all output files created during the test.
	 */
	@Test
	public void testFileIO() throws Exception {

		log.info("Testing overall features...");

		log.info("Test 1: Valid sudokus");
		String[] basic = {"examples/cowell.sudoku", "examples/fletcher.sudoku"};
		Game.main(basic);

		Solution cowellGoldenSolution = new Solution("examples/cowell.golden.solution");
		Solution fletcherGoldenSolution = new Solution("examples/fletcher.golden.solution");
		Solution cowellSolution = new Solution("examples/cowell.solution");
		Solution fletcherSolution = new Solution("examples/fletcher.solution");

		assertTrue(cowellSolution.equals(cowellGoldenSolution));
		assertTrue(fletcherSolution.equals(fletcherGoldenSolution));

		log.info("Test 2: Invalid arguments");
		String[] irregular = {null, "", "/", "/dev/tty", "C:\\Windows\\notepad.exe", "3939"};
		Game.main(irregular);

		log.info("Test 3: Invalid extension");
		String[] invalidExt = {"examples/wrong/extension.eosdk"};
		Game.main(invalidExt);

		assertTrue(new File("examples/wrong/extension.eosdk.solution").delete());

		log.info("Test completed!");

		clearTestResult();

	}

	/**
	 * Deletes test outputs
	 */
	public static void clearTestResult() {

		deleteExtension("examples", "solution", ".golden.solution");

	}

	/**
	 * Deletes specified directory
	 * 
	 * @param directory directory path in string
	 */
	public static void deleteDirectory(String directory) {

		deleteDirectory(new File(directory));

	}

	/**
	 * Deletes specified directory
	 * 
	 * @param path directory object (File)
	 */
	public static void deleteDirectory(File path) {

		if (path.exists()) {

			for (File child : path.listFiles()) {

				if (child.isDirectory()) {

					deleteDirectory(child);

				} else {

					child.delete();

				}

			}

			path.delete();

		}

	}

	/**
	 * Deletes files with specified extension non-recursively in a given directory
	 * 
	 * @param path      target directory
	 * @param extension target extension
	 * @param filter    filter string (files whose name include this will be excluded)
	 */
	public static void deleteExtension(String path, String extension, String filter) {

		deleteExtension(new File(path), extension, filter);

	}

	/**
	 * Deletes files with specified extension non-recursively in a given directory
	 * 
	 * @param path      target directory
	 * @param extension target extension
	 * @param filter    filter string (files whose name include this will be excluded)
	 */
	public static void deleteExtension(File path, String extension, String filter) {

		if (path.exists()) {

			for (File child : path.listFiles()) {

				if (child.isFile() && child.getName().contains("." + extension)
						&& !child.getName().contains(filter)
						&& child.getName().substring(child.getName().lastIndexOf(".") + 1)
								.indexOf(extension) == 0) {

					child.delete();

				}

			}

			path.delete();

		}

	}

}
