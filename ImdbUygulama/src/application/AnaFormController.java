package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnaFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Anchor1;

    @FXML
    private Button adminButton;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button kullanıcıButton;

    @FXML
    void adminButton_OnClick(ActionEvent event) {
           try {
        	   AnchorPane pane1 = (AnchorPane) FXMLLoader.load(getClass().getResource("AdminGiris.fxml"));
        	   anchor2.getChildren().setAll(pane1);
           } catch (Exception e) {
        	   e.printStackTrace();
           }
    }

    @FXML
    void kullanıcıButton_OnClick(ActionEvent event) {
    	try {
    		Stage stage1 = new Stage();
			AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("Kullanici.fxml"));
			Scene scene = new Scene(anchor,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage1.setScene(scene);
			stage1.show();
			stage1.setTitle("Kullanici");
			stage1.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		
		}
    }

    @FXML
    void initialize() {
 

    }

}
