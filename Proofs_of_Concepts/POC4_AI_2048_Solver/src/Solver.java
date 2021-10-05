import java.util.Random;

public class Solver {

  private Interface puzzle;
  Random rand = new Random();

  public Solver() {
    puzzle = new Interface(0);
    puzzle.run();
  }

  public void solve() {

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
    int nbGames = 100;
    int loading = nbGames / 15;

    for (int moves = 0; moves < 10000; moves++) {
      for (int n = 0; n < nbGames; n++) {
        if (n % loading == 0) {
          System.out.print("-");
        }

        // from 30 to 41 ~= reset
        tempScore = puzzle.getScore();
        // System.out.println(tempScore);


        // tempBoard = current board
        for (int i = 0; i < puzzle.getBoard().length; i++) {
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
      leftScore = 0;
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

      if (max(a, b, c, d) == "LEFT") {
        System.out.println("LEFT");
        puzzle.left(puzzle.board);
      } else {
        if (max(a, b, c, d) == "RIGHT") {
          System.out.println("RIGHT");
          puzzle.right(puzzle.board);
        } else {
          if (max(a, b, c, d) == "UP") {
            System.out.println("UP");
            puzzle.up(puzzle.board);
          } else {
            System.out.println("DOWN");
            puzzle.down(puzzle.board);
          }
        }
      }


      /// MAGIC
      // puzzle.print(tempBoard);
      puzzle.print(puzzle.getBoard());



    }
    System.exit(0);

  }

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
