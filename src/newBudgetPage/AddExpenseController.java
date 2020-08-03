/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage;
        
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


/**
 * FXML Controller class
 *
 * @author steve
 */
public class AddExpenseController extends Dialog<ButtonType> {

    public AddExpenseController() throws IOException{        
        
            /*Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/addExpense.fxml"));
            getDialogPane().setContent(root);*/
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/newBudgetPage/addExpense.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);
            
            
            
    }   
}
