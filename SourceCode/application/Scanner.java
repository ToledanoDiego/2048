package application;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 * This class scans the sreen to find the puzzle
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class Scanner {

  static Point root = new Point(1, 1);
  static Point A = new Point(1, 1);
  static Point B = new Point(1, 1);
  static Point C = new Point(1, 1);
  static Point D = new Point(1, 1);
  static Interface puzzle = new Interface(0);
  static Solver solver = new Solver(puzzle);


  /**
   * Scan the screen using the mouse.
   */
  public static void scan1() {

    try {


      Thread.sleep(2000); // Scan the first point
      A = MouseInfo.getPointerInfo().getLocation();
      System.out.println(A);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }


  /**
   * Scan the screen using the mouse.
   */
  public static void scan2() {

    try {

      Thread.sleep(2000);
      B = MouseInfo.getPointerInfo().getLocation();
      System.out.println(B);


      C = MouseInfo.getPointerInfo().getLocation();
      System.out.println(C);

      D = MouseInfo.getPointerInfo().getLocation();
      System.out.println(D);

      scan(A, B, C, D);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * Calculate the distance between two points
   *
   * @param Point A
   * @param Point B
   * 
   * @return the distance between the point A and the the point B
   */
  private static double dist(Point A, Point B) {
    return Math.sqrt(Math.pow(((int) B.getX() - (int) A.getX()), 2)
        + Math.pow(((int) B.getY() - (int) A.getY()), 2));
  }

  /**
   * Database in which the program stores the corresponding value according to its rgb values
   * 
   * @param r the red value of the pixel
   * @param g the green value of the pixel
   * @param b the blue value of the pixel
   * 
   * @return the value of a pixel according to its rgb values
   */
  public static int database(int r, int g, int b) {
    if (r == 205 && g == 192 && b == 180) {
      return 0;
    } else {
      if (r == 205 && g == 192 && b == 180) {
        return 0;
      } else {

        if (r == 238 && g == 228 && b == 218) {
          return 2;
        } else {
          if (r == 237 && g == 224 && b == 200) {
            return 4;
          } else {
            if (r == 242 && g == 177 && b == 121) {
              return 8;
            } else {
              if (r == 245 && g == 149 && b == 99) {
                return 16;
              } else {
                if (r == 246 && g == 124 && b == 95) {
                  return 32;
                } else {
                  if (r == 246 && g == 94 && b == 59) {
                    return 64;
                  } else {
                    if (r == 237 && g == 207 && b == 114) {
                      return 128;
                    } else {
                      if (r == 237 && g == 204 && b == 97) {
                        return 256;
                      } else {
                        if (r == 237 && g == 200 && b == 80) {
                          return 512;
                        } else {
                          if (r == 237 && g == 197 && b == 63) {
                            return 1024;
                          } else {
                            if (r == 237 && g == 194 && b == 46) {
                              return 2048;
                            }
                            return 0;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * Scan and store the values of the real puzzle into a "fake" puzzle so it is easier to do some
   * calculations
   * 
   * @param top left corner of the real puzzle
   * @param top right corner of the real puzzle
   * @param bottom right corner of the real puzzle
   * @param bottom left corner of the real puzzle
   */
  public static void scan(Point A, Point B, Point C, Point D) {

    Robot robot;
    try {
      Thread.sleep(210);
      robot = new Robot();
      double AB = dist(A, B);

      for (int i = 0; i < 4; i++) {

        for (int j = 0; j < 4; j++) {
          root.setLocation((int) (A.getX() + AB / 8 + j * (AB / 4)),
              (int) (A.getY() + AB / 16 + i * (AB / 4)));
          Color rootColor = robot.getPixelColor((int) root.getX(), (int) root.getY());
          int rootValue = database(rootColor.getRed(), rootColor.getGreen(), rootColor.getBlue());
          puzzle.newDigit(puzzle.getBoard(), rootValue, i, j);
        }
      }

    } catch (AWTException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solve the puzzle using my implementation of the Monte Carlo Tree search algorithm.
   */
  public static void solve() {
    solver.solve();

  }

}
