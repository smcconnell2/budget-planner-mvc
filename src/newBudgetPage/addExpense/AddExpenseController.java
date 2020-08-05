/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage.addExpense;


import java.math.BigDecimal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


/**
 * FXML Controller class
 *
 * @author steve
 */
public class AddExpenseController extends Dialog<ButtonType> {

    @FXML private Label label;
    @FXML private TextField nameField;
    @FXML private TextField priorityField;
    @FXML private TextField amountField;
    @FXML private ColorPicker colorPicker;
    
    private String name = "";
    private String priority = "";
    private String amount = "";
    private Color color; 

    /**
     * This will be used to initialize values upon window opening.
     */
    private void initialize(){
        
    }
    
    private void messageToUser(String message){
        this.label.setText(message);
    }
    
    @FXML
    public void onSubmitBtnClick(ActionEvent action){
        messageToUser("Submit Clicked");
        if(varifyInputSetStruct()){
            ExpenseStruct.submitClicked = true;
        }
    }
    
    private boolean varifyInputSetStruct(){
        getFieldInput();
        
        /*if(!true){
            return false; // insert logic to handle checking if data is valid
        }*/
        int tempP = Integer.parseInt(this.priority);
        BigDecimal tempBD = new BigDecimal(this.amount);
        
        ExpenseStruct.priority = tempP;
        ExpenseStruct.name = this.name;
        ExpenseStruct.amount = tempBD;
        ExpenseStruct.color = this.color;
        
        return true;
    }
    
    private void getFieldInput(){
        this.name = this.nameField.getText();
        this.priority = this.priorityField.getText();
        this.amount = this.amountField.getText();
        this.color = this.colorPicker.getValue();
    }
    
    @FXML
    public void onBackBtnClick(ActionEvent action){
        messageToUser("Back Clicked");
        
    }
}
