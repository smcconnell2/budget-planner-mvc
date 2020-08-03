/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage.addExpense;
        
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author steve
 */
public class AddExpenseController extends Dialog<ButtonType> {

    @FXML private Label label;
    
   /* public AddExpenseController() throws IOException{        
            // first attempt
            Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/addExpense.fxml"));
            getDialogPane().setContent(root);
            // second attempt
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/newBudgetPage/addExpense/addExpense.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);

    }   */
    /**
     * This will be used to initialize values upon window opening.
     */
    private void initialize(){
        
    }
    
    private void messageToUser(String message){
        this.label.setText(message);
    }
    
    public void onExitClicked(ActionEvent action){
        messageToUser("working");
    }
}
