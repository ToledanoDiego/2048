package application;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * This class solves the puzzle using the Monte Carlo tree search alorithm.
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class Solver {

  private Interface puzzle;
  Random rand = new Random();

  /**
   * Constructor
   * 
   * @param puzzle interface the Ai is using to interact with the puzzle
   */
  public Solver(Interface puzzle) {
    this.puzzle = puzzle;
    // System.out.println("sadness");
    puzzle.run();
  }

  /**
   * solves the puzzle using the Monte Carlo tree search alorithm.
   */
  public void solve() {

    Robot robot;
    try {
      robot = new Robot();

      int[][] tempBoard = new int[4][4];

      int left = 1;
      int right = 1;
      int up = 1;
      int down = 1;

      int leftScore = 0;
      int rightScore = 0;
      int upScore = 0;
      int downScore = 0;

      double avrgleftScore = 0;
      double avrgrightScore = 0;
      double avrgupScore = 0;
      double avrgdownScore = 0;

      double a, b, c, d;
      int tempScore = puzzle.getScore();
      int nbGames = 500;
      int loading = nbGames / 15;

      for (int moves = 0; moves < 10000; moves++) { //start solving
        for (int n = 1; n < nbGames; n++) {
          if (n % loading == 0) {
            System.out.print("-");
          }

          tempScore = puzzle.getScore();

          for (int i = 0; i < puzzle.getBoard().length; i++) { //save temporarily the current board
            for (int j = 0; j < puzzle.getBoard().length; j++) {

              tempBoard[i][j] = puzzle.getBoard()[i][j];

            }
          }


          int[] randomPlay = new int[2];
          randomPlay = random(tempBoard);

          if (randomPlay[1] == 0) { // setup
            left++;
            leftScore = leftScore + randomPlay[0];
          } else {
            if (randomPlay[1] == 1) {
              right++;
              rightScore = rightScore + randomPlay[0];
            } else {
              if (randomPlay[1] == 2) {
                up++;
                upScore = upScore + randomPlay[0];
              } else {
                if (randomPlay[1] == 3) {
                  down++;
                  downScore = downScore + randomPlay[0];
                }
              }
            }
          }



          puzzle.setScore(tempScore);
          avrgleftScore = (double) leftScore / (double) left;
          avrgrightScore = (double) rightScore / (double) right;
          avrgupScore = (double) upScore / (double) up;
          avrgdownScore = (double) downScore / (double) down;



        }
        leftScore = 0; //reset
        rightScore = 0;
        upScore = 0;
        downScore = 0;
        left = 0;
        right = 0;
        up = 0;
        down = 0;


        a = avrgleftScore;
        b = avrgrightScore;
        c = avrgupScore;
        d = avrgdownScore;

        if (max(a, b, c, d) == "LEFT") { //slide and rescan
          System.out.println("LEFT");
          robot.keyPress(KeyEvent.VK_A);
          Scanner.scan(Scanner.A, Scanner.B, Scanner.C, Scanner.D);
        } else {
          if (max(a, b, c, d) == "RIGHT") {
            System.out.println("RIGHT");
            robot.keyPress(KeyEvent.VK_D);
            Scanner.scan(Scanner.A, Scanner.B, Scanner.C, Scanner.D);
          } else {
            if (max(a, b, c, d) == "UP") {
              System.out.println("UP");
              robot.keyPress(KeyEvent.VK_W);
              Scanner.scan(Scanner.A, Scanner.B, Scanner.C, Scanner.D);
            } else {
              System.out.println("DOWN");
              robot.keyPress(KeyEvent.VK_S);
              Scanner.scan(Scanner.A, Scanner.B, Scanner.C, Scanner.D);
            }
          }
        }


        /// MAGIC
        // puzzle.print(tempBoard);
        puzzle.print(puzzle.getBoard());



      }
      System.exit(0);


    } catch (AWTException e) {
      e.printStackTrace();
    }


  }

  /**
   * play a random game and store its result.
   * 
   * @param board puzzle the AI is using to play the random games with
   * @return an array containing the first move and the final score of the simulation
   */
  private int[] random(int[][] board) {
    int[] scoreMove = new int[2];
    String[] moves = {"LEFT", "RIGHT", "UP", "DOWN"};
    int firstMove;

    int random = rand.nextInt(moves.length);
    String move = moves[random];
    firstMove = random; // play the first move and keep track of it

    if (move.equals("LEFT")) {
      puzzle.left(board);
    } else {
      if (move.equals("RIGHT")) {
        puzzle.right(board);
      } else {
        if (move.equals("UP")) {
          puzzle.up(board);
        } else {
          if (move.equals("DOWN")) {
            puzzle.down(board);
          }
        }
      }
    }

    for (int i = 0; i < 10; i++) { // play randomly 10 moves
      move = moves[rand.nextInt(moves.length)];

      if (move.equals("LEFT")) {
        puzzle.left(board);
      } else {
        if (move.equals("RIGHT")) {
          puzzle.right(board);
        } else {
          if (move.equals("UP")) {
            puzzle.up(board);
          } else {
            if (move.equals("DOWN")) {
              puzzle.down(board);
            }
          }
        }
      }
    }
    scoreMove[0] = puzzle.getScore(); // return the final score
    scoreMove[1] = firstMove;

    return scoreMove;
  }

  /**
   * Check if is is a game over
   * 
   * @param board puzzle the AI is using
   * @return true if the puzzle is stuck
   */
  @SuppressWarnings("unused")
  private boolean isBlock(int[][] board) {

    int[][] tempBoard = new int[4][4];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        tempBoard[i][j] = board[i][j];
      }
    }

    puzzle.left(tempBoard);
    puzzle.right(tempBoard);
    puzzle.up(tempBoard);
    puzzle.down(tempBoard);

    puzzle.print(tempBoard);

    boolean temp = false;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 0) {
          temp = false;
        }
        if (tempBoard[i][j] != board[i][j]) {
          temp = false;
        } else {
          temp = true;
        }
      }
    }

    return temp;
  }

  /**
   * Find the maximum value
   * 
   * @param a first value
   * @param b second value
   * @param c third value
   * @param d fourth value
   * @return the maximum value between a, b, c and d as a direction
   */
  private String max(double a, double b, double c, double d) {

    double max = Math.max(Math.max(a, b), Math.max(c, d));
    // System.out.print(max);


    if (max == a) {
      return "LEFT";
    } else {
      if (max == b) {
        return "RIGHT";
      } else {
        if (max == c) {
          return "UP";
        } else {
          return "DOWN";
        }
      }
    }
  }

}
