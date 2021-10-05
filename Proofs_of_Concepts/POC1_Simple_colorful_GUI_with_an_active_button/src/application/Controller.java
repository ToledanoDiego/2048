/**
 * Sample Skeleton for 'window.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Controller {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="button"
  private Button button; // Value injected by FXMLLoader

  @FXML // fx:id="pane"
  private BorderPane pane; // Value injected by FXMLLoader

  @FXML
  void changeColor(ActionEvent event) {

    Random rand = new Random();
    int r = rand.nextInt(256);
    int g = rand.nextInt(256);
    int b = rand.nextInt(256);

    String color = "rgb(" + r + ", " + g + ", " + b + ")";
    pane.setStyle("-fx-background-color: " + color);

  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'window.fxml'.";
    assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'window.fxml'.";

  }
}
