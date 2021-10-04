package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class is the main class of the program.
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class Main extends Application {

  /**
   * Start the program.
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
      Scene scene = new Scene(root, 400, 300);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Main method of the program, just launch the software.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
