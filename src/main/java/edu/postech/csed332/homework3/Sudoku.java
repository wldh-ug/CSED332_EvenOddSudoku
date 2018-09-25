package edu.postech.csed332.homework3;

import java.util.Set;

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
	// TODO implement this

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
