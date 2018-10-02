package edu.postech.csed332.homework3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	 * Load a solution from given file.
	 *
	 * @param fileName the name of the input file
	 * @throws IOException
	 */
	public Solution(String fileName) throws IOException {

		// NOTE: DO check the integrity of the file in this function

		// Check file readability
		File inputFile = new File(fileName);
		if (inputFile.isFile() && inputFile.canRead()) {

			try {

				FileInputStream input = new FileInputStream(inputFile);

				try {

					log.info("Reading a solution from {}...", fileName);

					// File read by line
					BufferedReader read = new BufferedReader(new InputStreamReader(input));
					String line;
					int lineNo = 0;

					// Initialize
					this.solution = new int[9][];

					try {

						// Read by line
						while ((line = read.readLine()) != null) {

							// Pass if empty lines & comments
							if (line.length() == 0 || line.indexOf("//") == 0) {

								continue;

							}

							log.debug("A line entered: {} [{}]", line, line.length());

							if (line.length() < 9) {

								log.error("Line length is less than ⑨, YOU STUPID (Strict Rule)!");
								throw new IOException();

							} else {

								for (int i = 0; i < 9; i++) {

									int value = Character.getNumericValue(line.charAt(i));

									if (value > 0 && value < 10) {

										this.solution[lineNo][i] = value;

									} else {

										log.error(
												"Wrong character at {} th line {} th (1-9) character: {}",
												lineNo, i + 1, line.charAt(i));
										throw new IOException();

									}

								}

								lineNo++;

							}

							// To prevent IndexOutOfBoundsException
							if (lineNo >= 9) {

								break;

							}

						}

						log.info("Successfully read.");

					} finally {

						read.close();

					}

				} finally {

					input.close();

				}

			} catch (IOException e) {

				log.error("Check the file and its format.");
				throw e;

			}

		} else {

			log.error("File is not readable.");
			throw new IOException();

		}

	}

	/**
	 * Saves solution in int array format
	 * 
	 * @param solved solved solution
	 */
	public Solution(int[][] solved) {

		if (solved != null) {

			log.debug("New solution saved in memory.");

			for (int i = 0; i < solved.length; i++) {

				String solutionLine = "";

				for (int j = 0; j < solved[0].length; j++) {

					solutionLine += " " + solved[i][j] + " ";

				}

				log.debug(solutionLine);

			}

			this.solution = solved;

		} else {

			log.warn("Null solution detected!");

			// Make all other results "null" or "false"
			this.solution = new int[0][0];

		}

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

		// NOTE: Row and column number STARTS FROM ONE, NOT ZERO

		try {

			return this.solution[row - 1][column - 1];

		} catch (IndexOutOfBoundsException e) {

			return null;

		}

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

	/**
	 * This compares two solutions and return the array about difference information.
	 * 
	 * @param other a solution which this solution will be compared with
	 * @return an information about comparison. If no difference, this will be an empty array.
	 */
	public List<Difference> compareWith(Solution other) {

		List<Difference> diffs = new ArrayList<Solution.Difference>();

		for (int i = 1; i < 10; i++) {

			for (int j = 1; j < 10; j++) {

				if (this.getValue(i, j) != other.getValue(i, j)) {

					diffs.add(new Difference(this, other, i, j));

				}

			}

		}

		return diffs;

	}

	/**
	 * This class stores the difference data of an specified cell.
	 */
	public static class Difference {

		public int row;
		public int column;
		public int sourceValue;
		public int otherValue;
		public Solution source;
		public Solution other;

		/**
		 * Load the value of specified cell automatically and saves them.
		 * 
		 * @param source original solution (object that contains callee)
		 * @param other  compared solution (object from parameter)
		 * @param row    row of the different value cell
		 * @param column row of the different value cell
		 */
		public Difference(Solution source, Solution other, int row, int column) {

			// NOTE: Operations below are safe because this uses "getValue" method!
			// NOTE: As Solution.getValue does, row and column number STARTS FROM ONE, NOT ZERO

			this.row = row;
			this.column = column;
			this.source = source;
			this.other = other;

			if (source != null) {

				this.sourceValue = source.getValue(row, column);

			}

			if (other != null) {

				this.otherValue = other.getValue(row, column);

			}

		}

	}

}
