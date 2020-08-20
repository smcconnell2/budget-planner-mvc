package newBudgetPage.addExpense;


import Structs.ExpenseStruct;
import enums.TextColor;
import java.math.BigDecimal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import interfaces.Controller;
import utils.ScrubUserData;


/**
 * FXML Controller class
 *
 * @author steve
 */
public class AddExpenseController extends Dialog<ButtonType> implements Controller{

    @FXML private Label label;
    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private ColorPicker colorPicker;
    
    private boolean nameFieldValid = false;
    private boolean amountFieldValid = false;
    
    @FXML private Label nameError;
    @FXML private Label amountError;
    
    @FXML private Button closeWindow;
     
    private String name = "";
    private String amount = "";
    private Color color; 

    public void messageToUser(String message, Paint color){
        this.label.setTextFill(color);
        this.label.setText(message);
        
        displayErrorFields();
    }
    
    private void displayErrorFields(){
        if(!this.nameFieldValid){
            this.nameError.setTextFill(TextColor.ERROR.getColor());
            this.nameError.setVisible(true);
        }
        else{
            this.nameError.setVisible(false);
        }
        if(!amountFieldValid){
            this.amountError.setTextFill(TextColor.ERROR.getColor());
            this.amountError.setVisible(true);
        }    
        else{
            this.amountError.setVisible(false);
        }
    }
    
    @FXML
    public void onSubmitBtnClick(ActionEvent action){
        
        if(varifyInputSetStruct()){
            ExpenseStruct.submitClicked = true;
            Stage stage = (Stage) this.closeWindow.getScene().getWindow();
            stage.close();   
        }
        else{
            messageToUser("Please enter valid data", TextColor.ERROR.getColor());
        }
    }
    
    private boolean varifyInputSetStruct(){
        getFieldInput();
        
        BigDecimal tempBD = ScrubUserData.parseToBigDecimal(this.amount);
        this.amountFieldValid = tempBD.intValue() >= 0;
        
        //Use Regex to varify Name then implement if else
        if(this.name == null || this.name.isEmpty()){
            this.nameFieldValid = false;
        }
        else{
            this.nameFieldValid = true;
        }
        
        
        if(this.amountFieldValid && this.nameFieldValid){
            ExpenseStruct.name = this.name;
            ExpenseStruct.amount = tempBD;
            ExpenseStruct.color = this.color;
            return true;
        }               
        return false;
    }
    
    private void getFieldInput(){
        this.name = this.nameField.getText();
        this.amount = this.amountField.getText();
        this.color = this.colorPicker.getValue(); 
    }
}
