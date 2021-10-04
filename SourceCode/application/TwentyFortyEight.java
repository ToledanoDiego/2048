package application;

import java.util.Random;

/**
 * This class creates a playable 2048 game in CMD.
 *
 * @author Diego Toledano
 * @version 3.1
 * @since 06/11/2019
 */
public class TwentyFortyEight {

  public static String message = "--Press-a-key--";
  private static String error1 = "";
  private static String error2 = "";
  public int score = 0;
  private static boolean isLeftBlock;
  private static boolean LeftLock;
  private static boolean RightLock;
  private static boolean UpLock;
  private static boolean DownLock;
  private static boolean isRightBlock;
  private static boolean isUpBlock;
  private static boolean isDownBlock;

  /**
   * Constructor.
   */
  public TwentyFortyEight() {
    isLeftBlock = true;
    LeftLock = false;
    isRightBlock = true;
    DownLock = false;
    isUpBlock = true;
    UpLock = false;
    isDownBlock = true;
    DownLock = false;

  }

  /**
   * Sum digits when we are swiping.
   *
   * @param sum (required) is array where we are adding digits.
   * @param board (required) is the board where sums append.
   * @return an array with the proper numbers sum with each other.
   * 
   */
  public void swipe(final String wasd, int[][] board) {
    if (wasd == "LEFT") {

      int[] sum = new int[board.length];

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {

          sum[j] = board[i][j];
        }

        int[] temp = sum;

        String tempS = temp[0] + "" + temp[1] + "" + temp[2] + "" + temp[3];
        // System.out.println(tempS);

        sum = sum(sum, board);

        String SumS = sum[0] + "" + sum[1] + "" + sum[2] + "" + sum[3];
        // System.out.println(SumS);

        // if after the above line nothing has changed

        if (SumS.equals(tempS)) {
          if (LeftLock == true) {
            isLeftBlock = false;
          } else {
            isLeftBlock = true;
          }
        } else {
          isLeftBlock = false;
          LeftLock = true;
        }

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

          int[] temp = sum;

          String tempS = temp[0] + "" + temp[1] + "" + temp[2] + "" + temp[3];
          // System.out.println(tempS);

          sum = sum(sum, board);

          String SumS = sum[0] + "" + sum[1] + "" + sum[2] + "" + sum[3];
          // System.out.println(SumS);

          // if after the above line nothing has changed

          if (SumS.equals(tempS)) {
            if (RightLock == true) {
              isRightBlock = false;
            } else {
              isLeftBlock = true;
            }
          } else {
            isRightBlock = false;
            RightLock = true;
          }


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

            int[] temp = sum;

            String tempS = temp[0] + "" + temp[1] + "" + temp[2] + "" + temp[3];
            // System.out.println(tempS);

            sum = sum(sum, board);

            String SumS = sum[0] + "" + sum[1] + "" + sum[2] + "" + sum[3];
            // System.out.println(SumS);

            // if after the above line nothing has changed

            if (SumS.equals(tempS)) {
              if (UpLock == true) {
                isUpBlock = false;
              } else {
                isUpBlock = true;
              }
            } else {
              isUpBlock = false;
              UpLock = true;
            }
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

              int[] temp = sum;

              String tempS = temp[0] + "" + temp[1] + "" + temp[2] + "" + temp[3];
              // System.out.println(tempS);

              sum = sum(sum, board);

              String SumS = sum[0] + "" + sum[1] + "" + sum[2] + "" + sum[3];
              // System.out.println(SumS);

              // if after the above line nothing has changed

              if (SumS.equals(tempS)) {
                if (DownLock == true) {
                  isDownBlock = false;
                } else {
                  isDownBlock = true;
                }
              } else {
                isDownBlock = false;
                DownLock = true;
              }
              for (int j = board.length - 1; j > -1; j--) {

                board[j][i] = sum[sum.length - j - 1];
              }
            }

          }
        }
      }
    }
    if (!isLeftBlock() || !isRightBlock() || !isUpBlock() || !isDownBlock()) {
      LeftLock = false;
      RightLock = false;
      UpLock = false;
      DownLock = false;
      newDigitRand(board);
    }
  }

  /**
   * Check is the AI can swipe down.
   *
   * @return true if the AI can not swipe down.
   * 
   */
  public boolean isDownBlock() {
    return isDownBlock;
  }

  /**
   * Check is the AI can swipe up.
   *
   * @return true if the AI can not swipe up.
   * 
   */
  public boolean isUpBlock() {
    return isUpBlock;
  }

  /**
   * Check is the AI can swipe right.
   *
   * @return true if the AI can not swipe right.
   * 
   */
  public boolean isRightBlock() {
    return isRightBlock;
  }

  /**
   * Check is the AI can swipe left.
   *
   * @return true if the AI can not swipe left.
   * 
   */
  public boolean isLeftBlock() {
    return isLeftBlock;
  }

  /**
   * Sum digits when we are swiping.
   *
   * @param sum (required) is array where we are adding digits.
   * @param board (required) is the board where sums append.
   * @return an array with the proper number sum with each other.
   * 
   */
  private int[] sum(int[] sum, int[][] board) {

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
  public boolean win(int[][] board) {

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
   * @return "LEFT", "DOWN", "UP" or "Right" according on the user input
   */
  public String WASD(char scanner) {

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
  private void error() {
    error1 = "";
    error2 = "";

  }

  /**
   * Add a new digit (2 or 4) on the board
   *
   * @param board (required) is the board where we are adding the new digits.
   */
  public void newDigitRand(int[][] board) {

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
        newDigitRand(board);
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
  public void print(int[][] board) {


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


    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print("[" + board[i][j] + "] ");
      }

      System.out.println();
    }
  }

  /**
   * Create a board
   *
   * @return the board we are using to play/solve the puzzle
   */
  public int[][] Board() {
    int[][] board = new int[4][4];
    return board;
  }

  /**
   * get the current score
   * 
   * @return current Score
   */
  public int getScore() {
    return score;
  }

  /**
   * set the current score
   * 
   * @param score score to set
   */
  public void setScore(int score) {
    this.score = score;

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
  public void newDigit(int[][] board, int value, int x, int y) {

    try {
      board[x][y] = value;
    } catch (StackOverflowError e) {
      message = "---GAME-OVER---";
    }

  }
}
