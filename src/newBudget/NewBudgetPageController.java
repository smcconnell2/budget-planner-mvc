/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudget;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class NewBudgetPageController implements Initializable {

    @FXML
    private Label label;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // is called once all @FXML methods are called and loaded
        // maybe use to populate fields with data to ensure that everything
        // is loaded properly first
    }    
    
    @FXML
    private void handleBackButton(ActionEvent event) {
        label.setText("Back Clicked"); // temp error checking
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/mainPage/mainPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Budget Planner");
            stage.setScene(scene);
            stage.show();
            
        } catch(IOException ex){
            Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
