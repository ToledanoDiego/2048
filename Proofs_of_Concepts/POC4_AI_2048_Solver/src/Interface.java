/**
 * This class is a facade for the class TwentyFortyEight
 *
 * @author Diego Toledano
 * @version 1.0
 * @since 05/11/2019
 */
import java.util.InputMismatchException;
import java.util.Scanner;

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

  public void run() {
    puzzle.newDigit(board);
    puzzle.newDigit(board);
    while (!puzzle.win(board)) {
      puzzle.print(board);
      input = scanner();
      if (input.equals("SOLVE")) {
        break;
      } else {
        if (!input.equals("SOLVE")) {
          puzzle.swipe(puzzle.WASD((input).charAt(0)), board);
        }
      }
    }
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

  public boolean isUpBlock() {
    return puzzle.isUpBlock();
  }

  public boolean isLeftBlock() {
    return puzzle.isLeftBlock();
  }

  public boolean isRightBlock() {
    return puzzle.isRightBlock();
  }

  public boolean win() {
    return puzzle.win(board);
  }

  public int pos(int x, int y) {
    return board[x][y];
  }

  public int getScore() {
    return puzzle.getScore();

  }

  public void print(int[][] board) {
    puzzle.print(board);

  }

  public void setScore(int score) {
    puzzle.setScore(score);
  }

  public int[][] getBoard() {
    return board;
  }

  public boolean isDownBlock() {
    return puzzle.isDownBlock();
  }

}
