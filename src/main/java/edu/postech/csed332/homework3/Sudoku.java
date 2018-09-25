package edu.postech.csed332.homework3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	/**
	 * Creates a new sudoku game from given file.
	 *
	 * @param fileName the name of the input file
	 */
	public Sudoku(String fileName) {

		// Check file readability
		File inputFile = new File(fileName);
		if (inputFile.isFile() && inputFile.canRead()) {

			try {

				FileInputStream input = new FileInputStream(inputFile);

				try {

					

				} catch (Exception e) {

				} finally {

					input.close();

				}

			} catch (IOException e) {

			}

		}

	}

	/**
	 * Returns a set of Solution instance for the given sudoku puzzle.
	 * 
	 * @return the set of solutions
	 */
	public Set<Solution> solve() {
		// TODO implement this
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
		// TODO implement this
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
		// TODO implement this
	}

}
