package edu.postech.csed332.homework3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.postech.csed332.homework3.Game.WrongGameException;

/**
 * 
 * An instance of this class represents a even/odd Sudoku puzzle. This class must be immutable. That
 * is, you should define a constructor to create a Sudoku object with all the necessary information
 * for a concrete even/odd Sudoku puzzle.
 * 
 * You can freely add member variables and methods to implement this class.
 * 
 */
public class Sudoku {
	private static Logger log = LoggerFactory.getLogger(Sudoku.class);
	private char[][] sudoku = null;
	private Set<Solution> solutions = null;

	/**
	 * Creates a new sudoku game from given file.
	 *
	 * @param fileName the name of the input file
	 * @throws IOException, WrongGameException
	 */
	public Sudoku(String fileName) throws IOException, WrongGameException {

		// Check file readability
		File inputFile = new File(fileName);
		if (inputFile.isFile() && inputFile.canRead()) {

			try {

				FileInputStream input = new FileInputStream(inputFile);

				try {

					log.info("Reading a game from {}.", fileName);

					// File read by line
					BufferedReader read = new BufferedReader(new InputStreamReader(input));
					String line;
					int i = 0;

					// Initialize
					this.sudoku = new char[9][];

					try {

						// Read by line
						while ((line = read.readLine()) != null) {

							log.debug("A line entered: {} [{}]", line, line.length());

							if (line.length() != 9)
								log.warn("Line length is not ⑨, YOU STUPID!");

							sudoku[i++] = line.toCharArray();

						}

						log.info("Successfully read.");

					} catch (IOException e) {

						throw e;

					} finally {

						read.close();

					}

				} catch (IOException e) {

					throw e;

				} finally {

					input.close();

				}

			} catch (IOException e) {

				log.error("Check the file and its format.");
				throw e;

			}

		} else {

			log.error("File is not readable.");
			throw new WrongGameException();

		}

	}

	/**
	 * Returns a set of Solution instance for the given sudoku puzzle.
	 * 
	 * @return the set of solutions
	 */
	public Set<Solution> solve() {

		if (this.solutions == null) {

			solutions = new HashSet<Solution>();

			

		}

		return this.solutions;

	}

	/**
	 * Returns true if a given cell must contain an even number.
	 * 
	 * @param row    row number
	 * @param column column number
	 * @return true if a cell (row, column) must be filled by an even number; otherwise, return
	 *         false.
	 */
	public Boolean isEven(int row, int column) {

		int cellValue = Character.getNumericValue(sudoku[row][column]);

		if ((cellValue > 0 && cellValue % 2 == 0) || (sudoku[row][column] == '*')) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Return the initial value of a cell, given an even/odd Sudoku puzzle. If there is no value to
	 * get (e.g. out of range, or empty), return null.
	 * 
	 * @param row    row number
	 * @param column column number
	 * @return A value of a cell, if it is non-empty
	 */
	public Integer getValue(int row, int column) {

		return Character.getNumericValue(sudoku[row][column]);

	}

	/**
	 * Returns raw game board.
	 * 
	 * @return "sudoku" local variable
	 */
	public char[][] getRawBoard() {

		return sudoku;

	}

}
