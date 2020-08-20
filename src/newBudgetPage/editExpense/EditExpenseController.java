/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage.editExpense;

import Structs.ExpenseStruct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class EditExpenseController implements Initializable {
    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private ColorPicker colorPicker;
    @FXML private Label label;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nameField.setText(ExpenseStruct.name);
        this.amountField.setText(ExpenseStruct.amount.toString());
        this.colorPicker.setValue(ExpenseStruct.color);
    }    
    
    
    
}
