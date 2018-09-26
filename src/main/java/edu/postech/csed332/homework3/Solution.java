package edu.postech.csed332.homework3;

import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * An instance of this class represents a single solution for a sudoku puzzle
 * 
 * You can freely add member variables and methods to implement this class
 * 
 */
public class Solution {
	private static Logger log = LoggerFactory.getLogger(Solution.class);
	private int[][] solution;

	/**
	 * Saves solution in int array format
	 * 
	 * @param solved solved solution
	 */
	public Solution(int[][] solved) {

		this.solution = solved;

	}

	/**
	 * Returns a value of a cell in the solution. If the input is out of range, return null.
	 * 
	 * For example, in the example puzzle in the homework description, cell (1,3) will be filled by
	 * number 8 by the solution. In this case, getValue(1,3) should return 8.
	 * 
	 * @param row    row number
	 * @param column column number
	 * @return Value of a (row, column) cell, provided by a given solution
	 */
	public Integer getValue(int row, int column) {

		return this.solution[row][column];

	}

	/**
	 * Export self to plain text file with given file name.
	 * 
	 * @param fileName file name to be exported
	 * @return Whether file is successfully saved or not
	 */
	public Boolean export(String fileName) {

		try {

			FileOutputStream write = new FileOutputStream(fileName);
			String output = new String();

			for (int i = 0; i < 9; i++) {

				for (int j = 0; j < 9; j++) {

					output += this.solution[i][j];

				}

				output += '\n';

			}

			try {

				write.write(output.getBytes());

			} catch (IOException e) {

				throw e;

			} finally {

				write.close();

			}

			return true;

		} catch (IOException e) {

			log.error("File exportion failed!");
			log.error(e.toString());

			return false;

		}

	}

}
