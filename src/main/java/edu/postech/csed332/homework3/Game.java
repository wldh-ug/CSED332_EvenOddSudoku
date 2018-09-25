package edu.postech.csed332.homework3;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Receives a sudoku puzzle as a file by the command line argument, solves the puzzle, and makes a
 * file for each solution
 * 
 * For example, if you received an input sudoku file "A" and the solver gives 3 solutions for the
 * puzzle, the output files "A_1.solution", "A_2.solution" and "A_3.solution" will be made.
 *
 */
public class Game {
	private static Logger log = LoggerFactory.getLogger(Game.class);

	/**
	 * @param args name of the input file for a sudoku puzzle
	 */
	public static void main(String[] args) {
		
		log.info("SUDOKUS started with {} problem(s)", args.length);

		// Iterate args, load game, and solve the game
		for (String prob : args) {

			log.info("New game: from {}", prob);

			try {

				Sudoku game = new Sudoku(prob);

			} catch (Exception e) {

				log.error(e.toString());

			}

		}

		log.info("SUDOKUS iterating finished.");

	}

	public static class WrongGameException extends Exception {

		private static final long serialVersionUID = -3590540730484860628L;

	}

}
