/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReviewYearBudget;

import budgetLogic.Expense;
import budgetLogic.MonthBudget;
import enums.TextColor;
import interfaces.Controller;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import newBudgetPage.NewBudgetPageController;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class EditReviewYearBudgetController implements Initializable, Controller {
    
    @FXML private Label label;
    private ObservableList<MonthBudget> monthList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
        @FXML
    public void handleFinishClick(){
        messageToUser("Finished Clicked", TextColor.TEST.getColor());
    }
    
    @FXML
    private void handleBackButton(ActionEvent event) {     
        if(varifyBackPress()){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/newBudgetPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("New Budget");
                stage.setScene(scene);
                stage.show();
            } catch(IOException ex){
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
    
    private void editMonth(){
        
    }
    
    private boolean varifyBackPress(){
        // add logic to check values
        return true;
    }
    
    @FXML
    public void handleAddYearIncomeClick(){
        messageToUser("Add Year Clicked", TextColor.TEST.getColor());
    }


}
