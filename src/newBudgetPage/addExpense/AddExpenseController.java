package newBudgetPage.addExpense;


import Structs.ExpenseStruct;
import dialogWindows.Alerts;
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
import javafx.scene.image.ImageView;
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
    @FXML private ImageView colorInfo;
    
    private boolean nameFieldValid = false;
    private boolean amountFieldValid = false;
    
    @FXML private Label nameError;
    @FXML private Label amountError;
    
    @FXML private Button cancelBtn;
     
    private String name = "";
    private String amount = "";
    private Color color; 

    public void messageToUser(String message, Paint color){
        this.label.setTextFill(color);
        this.label.setText(message);
        
        displayErrorFields();
    }
    
    public void clearMessageToUser(){
        this.label.setText("");
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
        
        if(varifyInput()){
            ExpenseStruct.submitClicked = true;
            Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
            stage.close();   
        }
        else{
            messageToUser("Please enter valid data", TextColor.ERROR.getColor());
        }
    }
    
    @FXML
    private void onColorInfo(){
        new Alerts().information(
                "Expense Color", 
                "Used to differentiate Expenses from one " + "\n" + "another when displayed in a graph."
        );
    }
    
    private boolean varifyInput(){
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
            setStruct(tempBD);
            return true;
        }               
        return false;
    }
    
    private void setStruct(BigDecimal bD){
        ExpenseStruct.name = this.name;
        ExpenseStruct.amount = bD;
        ExpenseStruct.color = this.color;
    }
    
    private void getFieldInput(){
        this.name = this.nameField.getText();
        this.amount = this.amountField.getText();
        this.color = this.colorPicker.getValue(); 
    }
}
