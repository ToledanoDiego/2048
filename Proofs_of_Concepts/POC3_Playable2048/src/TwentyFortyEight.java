/**
 * This class create a playable 2048 game in CMD.
 *
 * @author Diego Toledano
 * @version 1.0
 * @since 04/11/2019
 */

import java.util.Random;
import java.util.Scanner;

public class TwentyFortyEight {

  private static Scanner scanner;
  private static String error1 = "";
  private static String error2 = "";
  private static int score = 0;
  private static String message = "--Press-a-key--";

  /**
   * Main functions that run everything.
   */
  public static void main(String[] args) {

    int[][] board = new int[4][4];
    newDigit(board);
    newDigit(board);

    while (!win(board)) {
      for (int i = 0; i < 100; i++) {
        System.out.println();
      }
      System.out.println("-2048-THE-GAME-");
      System.out.println("-----SCORE-----");
      System.out.println(("-" + score + "---------------").substring(0, 15));

      if (error1 != "") {
        System.out.println(error1);
        System.out.println(error2);
      }

      print(board);
      swipe(WASD(scanner()), board);
    }

  }

  /**
   * Sum digits when we are swiping.
   *
   * @param sum (required) is array where we are adding digits.
   * @param board (required) is the board where sums append.
   * @return an array with the proper numbers sum with each other.
   * 
   */
  private static void swipe(String wasd, int[][] board) {
    if (wasd == "LEFT") {

      int[] sum = new int[board.length];

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {

          sum[j] = board[i][j];
        }

        sum = sum(sum, board);

        for (int j = 0; j < board.length; j++) {

          board[i][j] = sum[j];
        }
      }
    } else {
      if (wasd == "RIGHT") {

        int[] sum = new int[board.length];

        for (int i = 0; i < board.length; i++) {
          for (int j = board.length - 1; j > -1; j--) {
            sum[sum.length - j - 1] = board[i][j];

          }

          sum = sum(sum, board);

          for (int j = board.length - 1; j > -1; j--) {

            board[i][j] = sum[sum.length - j - 1];
          }
        }
      } else {
        if (wasd == "UP") {

          int[] sum = new int[board.length];

          for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
              sum[j] = board[j][i];

            }

            sum = sum(sum, board);

            for (int j = 0; j < board.length; j++) {

              board[j][i] = sum[j];
            }
          }
        } else {
          if (wasd == "DOWN") {

            int[] sum = new int[board.length];

            for (int i = 0; i < board.length; i++) {
              for (int j = board.length - 1; j > -1; j--) {
                sum[sum.length - j - 1] = board[j][i];

              }

              sum = sum(sum, board);

              for (int j = board.length - 1; j > -1; j--) {

                board[j][i] = sum[sum.length - j - 1];
              }
            }
          }
        }
      }
    }



  }

  /**
   * Sum digits when we are swiping.
   *
   * @param sum (required) is array where we are adding digits.
   * @param board (required) is the board where sums append.
   * @return an array with the proper number sum with each other.
   * 
   */
  private static int[] sum(int[] sum, int[][] board) {

    int a = sum[0];
    int b = sum[1];
    int c = sum[2];
    int d = sum[3];

    int tempA = a;
    int tempB = b;
    int tempC = c;
    int tempD = d;


    if (a == b && b == c && c == d && d == 0) {
      return sum;
    }

    if (a == b && c == d) { // 2244
      a = a + b;
      b = c + d;
      c = 0;
      d = 0;
      score = score + a + b;
    } else {
      if (a == b) { // 22--
        a = a + b;
        b = c;
        c = d;
        d = 0;
        score = score + a;
      } else {
        if (a == c && b == 0) { // 202-
          a = a + c;
          b = d;
          c = 0;
          d = 0;
          score = score + a;
        } else {
          if (a == d && b == 0 && c == 0) { // 2002
            a = a + d;
            b = 0;
            c = 0;
            d = 0;
            score = score + a;
          } else {
            if (b == c) { // -22-
              b = b + c;
              c = d;
              d = 0;
              score = score + b;
            } else {
              if (b == d && c == 0) { // -202
                b = b + d;
                c = 0;
                d = 0;
                score = score + b;
              } else {
                if (c == d) { // --22
                  c = c + d;
                  d = 0;
                  score = score + c;
                }
              }
            }
          }
        }
      }
    }

    if (tempA != a || tempB != b || tempC != c || tempD != d) {
      newDigit(board);
    }


    sum[0] = a;
    sum[1] = b;
    sum[2] = c;
    sum[3] = d;

    return slide(sum);

  }

  /**
   * Make sure there is no gap (0) when you are swiping up/left/down/right
   *
   * @param sum (required) is an array of the board that we are swiping.
   * @return the array swiped.
   * 
   */
  private static int[] slide(int[] sum) {
    int a = sum[0];
    int b = sum[1];
    int c = sum[2];
    int d = sum[3];

    if (a == 0 && b == 0 && c == 0) { // 0002
      a = d;
      d = 0;
    } else {
      if (a == 0 && b == 0) { // 002-
        a = c;
        b = d;
        c = 0;
        d = 0;
      } else {
        if (a == 0) { // 02--
          a = b;
          b = c;
          c = d;
          d = 0;
        } else {
          if (b == 0 && c == 0) { // 2002
            b = d;
            d = 0;
          } else {
            if (b == 0) { // 202-
              b = c;
              c = d;
              d = 0;
            } else {
              if (c == 0) { // -202
                c = d;
                d = 0;
              }
            }
          }
        }
      }
    }

    sum[0] = a;
    sum[1] = b;
    sum[2] = c;
    sum[3] = d;

    return sum;
  }

  /**
   * Check if the player have won the game.
   *
   * @param board (required) is the board where we are checking for the win.
   * @return <code>true</code> only if the player win.
   * 
   */
  private static boolean win(int[][] board) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 2048) {
          message = "----YOU-WIN----";
          return true;
        }
      }
    }
    return false;

  }

  /**
   * Map key input to actions.
   *
   * @param scanner (required) is the input we are getting from the keyboard
   */
  private static String WASD(char scanner) {

    if (scanner == 'W') {
      error();
      return "UP";
    } else {
      if (scanner == 'A') {
        error();
        return "LEFT";
      } else {
        if (scanner == 'S') {
          error();
          return "DOWN";
        } else {
          if (scanner == 'D') {
            error();
            return "RIGHT";
          } else {
            error1 = "---PRESS-THE---";
            error2 = "--CORRECT-KEY--";
          }
        }
      }
    }
    return null;
  }

  /**
   * Set the error message to nothing.
   */
  private static void error() {
    error1 = "";
    error2 = "";

  }

  /**
   * Scan user inputs.
   *
   * @return as a char, the input from the user's keyboard.
   */
  private static char scanner() {
    try {
      System.out.println(message);
      if (message != "---GAME-OVER---") {
        System.out.println("-------W-------");
        System.out.println("-----A-S-D-----");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.toUpperCase().charAt(0);
      } else {
        if (message == "---GAME-OVER---") {
          System.exit(0);
        }
      }
    } catch (Exception e) {

    }
    return ' ';

  }

  /**
   * Add a new digit (2 or 4) on the board
   *
   * @param board (required) is the board where we are adding the new digits.
   */
  private static void newDigit(int[][] board) {

    try {
      Random rand = new Random();
      int randomX = rand.nextInt(4);
      int randomY = rand.nextInt(4);
      int newDigit = (rand.nextInt(10) + 1);

      if (newDigit == 1) {
        newDigit = 4;
      } else {
        if (newDigit > 1) {
          newDigit = 2;
        }
      }

      if (board[randomX][randomY] == 0) {
        board[randomX][randomY] = newDigit;
      } else {
        newDigit(board);
      }
    } catch (StackOverflowError e) {
      message = "---GAME-OVER---";
    }

  }

  /**
   * Display the game in CMD
   *
   * @param board (required) is the board we are displaying.
   */
  private static void print(int[][] board) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print("[" + board[i][j] + "] ");
      }

      System.out.println();
    }
  }

}
