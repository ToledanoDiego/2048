package application;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 * This class is the Sample Skeleton for 'Sample.fxml' Controller Class
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class SampleController {

  int counter = 0;
  static Point A = new Point(1, 1);
  static Point B = new Point(1, 1);

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="Scan"
  private Button Scan; // Value injected by FXMLLoader

  @FXML // fx:id="Scan2"
  private Button Scan2; // Value injected by FXMLLoader

  @FXML // fx:id="Solve"
  private Button Solve; // Value injected by FXMLLoader

  @FXML // fx:id="Algo"
  private ToggleGroup Algo; // Value injected by FXMLLoader

  @FXML // fx:id="MinMax"
  private RadioButton MinMax; // Value injected by FXMLLoader

  @FXML
  TextArea point1;


  /**
   * Action when you press the scan button
   * 
   * @param event
   */
  @FXML
  void ScanBtn(ActionEvent event) {
    Scanner.scan1();
    point1.setText("50%");
  }


  /**
   * Action when you press the solve button
   * 
   * @param event
   */
  @FXML
  void SolveBtn(ActionEvent event) {
    Scanner.solve();
  }

  @FXML
  void ScanBtn2(ActionEvent event) {
    Scanner.scan2();
    point1.setText("100%");
  }

  /**
   * initialize everything at the start.
   */
  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert Scan != null : "fx:id=\"Scan\" was not injected: check your FXML file 'Sample.fxml'.";
    assert Solve != null : "fx:id=\"Solve\" was not injected: check your FXML file 'Sample.fxml'.";
    assert Algo != null : "fx:id=\"Algo\" was not injected: check your FXML file 'Sample.fxml'.";
    assert MinMax != null : "fx:id=\"MinMax\" was not injected: check your FXML file 'Sample.fxml'.";

  }


}
