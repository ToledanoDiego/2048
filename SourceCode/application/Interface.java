package application;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is a facade for the class TwentyFortyEight
 *
 * @author Diego Toledano
 * @version 1.0
 * @since 05/11/2019
 */
public class Interface {

  private static Scanner scanner;
  private String input = "";
  public TwentyFortyEight puzzle;
  public int[][] board;
  private int time = 0;

  /**
   * Constructor.
   * 
   * @param score
   */
  public Interface(int score) {
    puzzle = new TwentyFortyEight();
    board = puzzle.Board();
  }

  /**
   * Run the puzzle game in CMD.
   */
  public void run() {
    // puzzle.newDigitRand(board);
    // puzzle.newDigitRand(board);
    while (!puzzle.win(board)) {
      puzzle.print(board);
      input = solve();
      if (input.equals("SOLVE")) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        break;
      } else {
        if (!input.equals("SOLVE")) {
          puzzle.swipe(puzzle.WASD((input).charAt(0)), board);
        }
      }
    }
  }

  /**
   * return "SOLVE"
   * 
   * @return "SOLVE" a string that state SOLVE
   */
  private String solve() {
    return "SOLVE";
  }

  /**
   * Scan user inputs.
   *
   * @return as a char or a string, the input from the user's keyboard.
   * @throws InputMismatchException if input is not correct.
   */
  public String scanner() {
    try {
      System.out.println(TwentyFortyEight.message);
      if (TwentyFortyEight.message != "---GAME-OVER---") {
        System.out.println("-------W-------");
        System.out.println("-----A-S-D-----");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.toUpperCase();
      } else {
        if (TwentyFortyEight.message == "---GAME-OVER---") {
          System.exit(0);
        }
      }
    } catch (Exception e) {

    }
    return " ";

  }

  /**
   * Swipe Left.
   * 
   * @param Board
   *
   * @throws InterruptedException if timer "sleep()" is stop.
   */
  public void left(int[][] board) {
    puzzle.swipe(("LEFT"), board);
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Swipe Down.
   *
   * @throws InterruptedException if timer "sleep()" is stop.
   */
  public void down(int[][] board) {
    puzzle.swipe(("DOWN"), board);

    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Swipe Right.
   *
   * @throws InterruptedException if timer "sleep()" is stop.
   */
  public void right(int[][] board) {
    puzzle.swipe(("RIGHT"), board);
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Swipe Up.
   *
   * @throws InterruptedException if timer "sleep()" is stop.
   */
  public void up(int[][] board) {
    puzzle.swipe(("UP"), board);

    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check is the AI can swipe up.
   *
   * @return true if the AI can not swipe up.
   * 
   */
  public boolean isUpBlock() {
    return puzzle.isUpBlock();
  }

  /**
   * Check is the AI can swipe left.
   *
   * @return true if the AI can not swipe left.
   * 
   */
  public boolean isLeftBlock() {
    return puzzle.isLeftBlock();
  }

  /**
   * Check is the AI can swipe right.
   *
   * @return true if the AI can not swipe right.
   * 
   */
  public boolean isRightBlock() {
    return puzzle.isRightBlock();
  }

  /**
   * Check is the AI can swipe down.
   *
   * @return true if the AI can not swipe down.
   * 
   */
  public boolean isDownBlock() {
    return puzzle.isDownBlock();
  }

  /**
   * Check is the puzzle is solved.
   *
   * @return true if the puzzle is solved.
   * 
   */
  public boolean win() {
    return puzzle.win(board);
  }

  /**
   * return the value of the board at a given position.
   * 
   * @param x x coordinate on the board
   * @param y y coordinate on the board
   *
   * @return the value of the board at a given position.
   * 
   */
  public int pos(int x, int y) {
    return board[x][y];
  }

  /**
   * get the current score
   * 
   * @return current Score
   */
  public int getScore() {
    return puzzle.getScore();

  }

  /**
   * set the current score
   * 
   * @param score score to set
   */
  public void setScore(int score) {
    puzzle.setScore(score);
  }

  /**
   * Display the game in CMD
   *
   * @param board (required) is the board we are displaying.
   */
  public void print(int[][] board) {
    puzzle.print(board);

  }

  /**
   * get the current board.
   * 
   * @return current board.
   */
  public int[][] getBoard() {
    return board;
  }

  /**
   * Create a new digit at a given coordinates.
   * 
   * 
   * @param board board in which we are adding a new digit.
   * @param value value of the new digit.
   * @param x x coordinate of the new digit.
   * @param y y coordinate of the new digit.
   */
  public void newDigit(int[][] board, int Value, int x, int y) {
    puzzle.newDigit(board, Value, x, y);
  }

}
