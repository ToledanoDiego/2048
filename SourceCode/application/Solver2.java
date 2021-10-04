package application;

import java.util.Random;

/**
 * This class solves the puzzle using the Minimax alorithm.
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class Solver2 {
  int l = 0;
  private Interface puzzle;
  Random rand = new Random();

  /**
   * Constructor
   */
  public Solver2() {
    puzzle = new Interface(0);
    puzzle.run();
  }

  /**
   * solves the puzzle using the Minimax alorithm.
   */
  public void solve() {

    for (int move = 0; move < 10000; move++) {

        int[][] tempBoard = new int[4][4];
        set(tempBoard, puzzle.getBoard());

        int[][] root = new int[4][4];
        set(root, tempBoard);
        Node<int[][]> rootNode = new Node<int[][]>(root);
        // System.out.println(rootNode.getData());

    // ------

        int[][] childLeft = new int[4][4];
        puzzle.left(puzzle.getBoard());
        set(childLeft, puzzle.getBoard());
        Node<int[][]> childLeftNode = new Node<int[][]>(childLeft);
        rootNode.addChild(childLeftNode);
        // childLeftNode.setParent(rootNode);

        for (int i = 0; i < puzzle.getBoard().length; i++) {
            for (int j = 0; j < puzzle.getBoard().length; j++) {

            if (puzzle.getBoard()[i][j] == 0) {

                puzzle.getBoard()[i][j] = 2;

                childLeftNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                // print(childLeftNode.children.get(0).data);
                // System.out.println();
                puzzle.getBoard()[i][j] = 4;
                // print(puzzle.getBoard());
                // System.out.println();
                childLeftNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                puzzle.getBoard()[i][j] = 0;

                }
            }
        }
        // print(childLeftNode.children.get(0).data);
        // print(childLeftNode.children.get(1).data);
        // System.out.println(childLeftNode.children.size());
        set(puzzle.getBoard(), tempBoard);

        // ------

        int[][] childRight = new int[4][4];
        puzzle.right(puzzle.getBoard());
        set(childRight, puzzle.getBoard());
        Node<int[][]> childRightNode = new Node<int[][]>(childRight);
        rootNode.addChild(childRightNode);

        for (int i = 0; i < puzzle.getBoard().length; i++) {
            for (int j = 0; j < puzzle.getBoard().length; j++) {

                if (puzzle.getBoard()[i][j] == 0) {

                    puzzle.getBoard()[i][j] = 2;
                    childRightNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 4;
                    childRightNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 0;

                    }

                }
            }
        set(puzzle.getBoard(), tempBoard);

        // ------

        int[][] childUp = new int[4][4];
        puzzle.up(puzzle.getBoard());
        set(childUp, puzzle.getBoard());
        set(puzzle.getBoard(), tempBoard);
        Node<int[][]> childUpNode = new Node<int[][]>(childUp);
        rootNode.addChild(childUpNode);

        for (int i = 0; i < puzzle.getBoard().length; i++) {
            for (int j = 0; j < puzzle.getBoard().length; j++) {

                if (puzzle.getBoard()[i][j] == 0) {

                    puzzle.getBoard()[i][j] = 2;
                    childUpNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 4;
                    childUpNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 0;
                }
            }
        }

        // ------

        int[][] childDown = new int[4][4];
        puzzle.down(puzzle.getBoard());
        set(childDown, puzzle.getBoard());
        set(puzzle.getBoard(), tempBoard);
        Node<int[][]> childDownNode = new Node<int[][]>(childDown);
        rootNode.addChild(childDownNode);

        for (int i = 0; i < puzzle.getBoard().length; i++) {
            for (int j = 0; j < puzzle.getBoard().length; j++) {

                if (puzzle.getBoard()[i][j] == 0) {

                    puzzle.getBoard()[i][j] = 2;
                    childDownNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 4;
                    childDownNode.addChild(new Node<int[][]>(puzzle.getBoard()));
                    puzzle.getBoard()[i][j] = 0;
                }

            }
        }
        
        System.out.println(childRightNode.children);
        String[] moves = {"LEFT", "RIGHT", "UP", "DOWN"};
        System.out.println(pos(rootNode)); //run

        if (moves[pos(rootNode)] == "LEFT") {
            System.out.println("LEFT");
            puzzle.left(puzzle.board);
            puzzle.newDigit(puzzle.board);
        } else {
            if (moves[pos(rootNode)] == "RIGHT") {
                System.out.println("RIGHT");
                puzzle.right(puzzle.board);
                puzzle.newDigit(puzzle.board);
            } else {
                if (moves[pos(rootNode)] == "UP") {
                    System.out.println("UP");
                    puzzle.up(puzzle.board);
                    puzzle.newDigit(puzzle.board);
                } else {
                    System.out.println("DOWN");
                    puzzle.down(puzzle.board);
                    puzzle.newDigit(puzzle.board);
                }
            }
        }

    }
    // puzzle.print(puzzle.getBoard());
    // print(rootNode.children.get(0).children.get(2));

    System.out
        .println(rootNode.children.get(0).children.size() + rootNode.children.get(1).children.size()
            + rootNode.children.get(2).children.size() + rootNode.children.get(3).children.size());

    print(rootNode.children.get(0).children.get(0).data);
    // System.out.println(minmax(rootNode, 3, true));

    System.exit(0);


  }

  /**
   * play a random game and store its result.
   * 
   * @param board puzzle the AI is using to play the random games with
   * @return an array containing the first move and the number of empty squares at the end of the
   *         simulation
   */
  @SuppressWarnings("unused")
  private int[] empty(int[][] board) {

    int[] emptyMove = new int[2];
    int empty = 0;
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

    for (int i = 0; i < puzzle.getBoard().length; i++) {
      for (int j = 0; j < puzzle.getBoard().length; j++) {


        if (puzzle.getBoard()[i][j] == 0) {
          empty++;
        }

      }
    }


    // String[] moves = {"LEFT", "RIGHT", "UP", "DOWN"};
    // System.out.println("First move was : " + moves[firstMove]);
    emptyMove[0] = empty;
    emptyMove[1] = firstMove;
    return emptyMove;

  }

  /**
   * play a random game and store its result.
   * 
   * @param board puzzle the AI is using to play the random games with
   * @return an array containing the first move and the number of move the AI has perform at the end
   *         of the simulation
   */
  @SuppressWarnings("unused")
  private int[] playDepths(int[][] board) {

    int[] depthsMove = new int[2];
    int depths = 0;
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
    for (int i = 0; i < 10; i++) {

      int tempScore = puzzle.getScore();
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
      int tempScore2 = puzzle.getScore();

      if (tempScore == tempScore2) {
        System.out.println("Game Over");
        System.out.println("depth is: " + depths);
        // depths = 0;
        break;
      } else {
        depths++;
      }
      // puzzle.print(puzzle.getBoard());
    }

    // String[] moves = {"LEFT", "RIGHT", "UP", "DOWN"};
    // System.out.println("First move was : " + moves[firstMove]);
    depthsMove[0] = depths;
    depthsMove[1] = firstMove;
    return depthsMove;
  }

  /**
   * play a random game and store its result.
   * 
   * @param board puzzle the AI is using to play the random games with
   * @return an array containing the first move and the final score of the simulation
   */
  @SuppressWarnings("unused")
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
  @SuppressWarnings("unused")
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

  /**
   * Board A = Board B
   * 
   * @param board Board A
   * @param board2 Board B
   */
  private void set(int[][] board, int[][] board2) {

    for (int i = 0; i < board2.length; i++) {
      for (int j = 0; j < board2.length; j++) {

        board[i][j] = board2[i][j];

      }
    }

  }

  /**
   * Minimax algorithm
   * 
   * @param position current state of the game
   * @param depth depth of the tree the AI is using
   * @param maximizing Player true if it is the AI's turn
   * @return the evaluation of the current state of the game
   */
  @SuppressWarnings("unused")
  private double minmax(Node<int[][]> position, int depth, boolean maximizingPlayer) {

    // System.out.println(position.children);

    int maxEval, minEval;
    int eval;

    // System.out.println(depth);
    // System.out.println(maximizingPlayer);

    if (depth == 0 || position.isLeaf()) {
      // System.out.println("end?");
      return staticEvaluation(position);
    }

    if (maximizingPlayer) {
      maxEval = Integer.MIN_VALUE;

      for (int i = 0; i < position.children.size(); i++) {
        Node<int[][]> child = position.children.get(i);
        print(position.children.get(i).data);
        eval = (int) minmax(child, depth - 1, false);
        maxEval = (int) max(maxEval, eval);
      }
      return maxEval;
    }

    else {
      minEval = Integer.MAX_VALUE;
      // System.out.println(position.children);
      for (int i = 0; i < position.children.size(); i++) {
        Node<int[][]> child = position.children.get(i);
        print(position.children.get(i).data);
        eval = (int) minmax(child, depth - 1, true);
        minEval = (int) min(minEval, eval);
      }
      return minEval;
    }
  }

  /**
   * evaluate the state of the game
   * 
   * @param position state of the game
   * @return Static evaluation of the given state
   */
  private double staticEvaluation(Node<int[][]> position) {
    double saticEval = 0;
    double saticEval2 = 0;
    int a[][] = {{7, 6, 5, 4}, {6, 5, 4, 3}, {5, 4, 3, 2}, {4, 3, 2, 1}};
    int c[][] = new int[4][4];


    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        c[i][j] = a[i][j] * position.data[i][j];
        // System.out.println(a[i][j]);

      }
    }

    for (int i = 0; i < position.data.length; i++) {
      for (int j = 0; j < position.data.length; j++) {
        saticEval2 = saticEval2 + c[i][j];
      }
    }

    for (int i = 0; i < position.data.length; i++) {
      for (int j = 0; j < position.data.length; j++) {
        saticEval = saticEval + c[i][j];
      }
    }

    position.setdata2(saticEval);

    System.out.println("Static Evaluation is : " + saticEval + " " + l++);
    System.out.println("Static Evaluation is : " + saticEval2);
    return saticEval;
  }

  /**
   * Find the maximum value
   * 
   * @param a first value
   * @param b second value
   * @return the maximum value between a and b
   */
  private double max(double a, double b) {
    if (a > b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * Find the minimum value
   * 
   * @param a first value
   * @param b second value
   * @return the minimum value between a and b
   */
  private double min(double a, double b) {
    if (a < b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * print on the CMD the state of the game
   * 
   * @param ks state of the game
   */
  private void print(int[][] ks) {
    for (int i = 0; i < ks.length; i++) {
      for (int j = 0; j < ks.length; j++) {

        System.out.print("[" + ks[i][j] + "] ");
      }

      System.out.println();
    }
  }
}

