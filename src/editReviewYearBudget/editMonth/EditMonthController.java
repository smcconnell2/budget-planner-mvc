/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReviewYearBudget.editMonth;

import Structs.MonthStruct;
import budgetLogic.MonthBudget;
import interfaces.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class EditMonthController implements Initializable, Controller{
    
    @FXML Label label;
    @FXML Label nameValue;
    @FXML Label incomeValue;
    @FXML Label expenseTotalValue;
    @FXML Label spentValue;
    
    private MonthBudget month;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.month = new MonthBudget(MonthStruct.monthInt, MonthStruct.expenseMap, MonthStruct.income);
        
        this.nameValue.setText(this.month.getName());
        this.incomeValue.setText(this.month.getMonthlyIncome().toString());
        this.expenseTotalValue.setText(this.month.getMonthlyExpenses().toString());
    }    
    
    private void setStruct(){
        
    }

    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }

    @Override
    public void clearMessageToUser() {
        this.label.setText("");    
    }
    

    
}
