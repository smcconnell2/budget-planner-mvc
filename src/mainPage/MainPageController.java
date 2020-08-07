package mainPage;

import enums.TextColor;
import interfaces.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author steve
 */
public class MainPageController implements Initializable, Controller {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleNewButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/newBudgetPage.fxml"));          
            Scene scene= new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("New Budget");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            messageToUser("Error on new button", TextColor.ERROR.getColor()); // Temp error checking
        }
    }
    
    @FXML
    private void handleLoadButton(ActionEvent event) {
        messageToUser("Load Clicked", TextColor.TEST.getColor());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
}
