/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage.editExpense;

import Structs.ExpenseStruct;
import dialogWindows.Alerts;
import enums.TextColor;
import interfaces.Controller;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import observer.Observer;
import utils.ScrubUserData;

/**
 * FXML Controller class
 *
 * @author steve
 */

// OBSERVER PATTERN: THIS IS SUBJECT
public class EditExpenseController implements Initializable, Controller{
    
    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private ColorPicker colorPicker;
    @FXML private Label label;
    @FXML private Button cancelBtn;
    
    private boolean nameFieldValid = false;
    private boolean amountFieldValid = false;
    
    @FXML private Label nameError;
    @FXML private Label amountError;
    
    @FXML private Button closeWindow;
     
    private String name = "";
    private String amount = "";
    private Color color; 
    
    private Observer observer;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nameField.setText(ExpenseStruct.name);
        this.amountField.setText(ExpenseStruct.amount.toString());
        this.colorPicker.setValue(ExpenseStruct.color);
    }    
    
    @Override
    public void messageToUser(String message, Paint color) {
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
    private void handleOnCancel(ActionEvent event){
        Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleOnSubmit(ActionEvent event){
        if(varifyInput()){
            
            if(new Alerts().warning2Choice("Submit", "Expense will be overwritten.", "Do you want to proceed?")){
                ExpenseStruct.submitClicked = true;
                Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
                stage.close();  
            } 
        }
        else{
            messageToUser("Please enter valid data", TextColor.ERROR.getColor());
        }
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
