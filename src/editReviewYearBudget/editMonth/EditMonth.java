/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReviewYearBudget.editMonth;

import Structs.MonthStruct;
import budgetLogic.Expense;
import budgetLogic.MonthBudget;
import dialogWindows.Alerts;
import editReviewYearBudget.EditReviewYearBudgetController;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author steve
 */
public class EditMonth implements EventHandler<ActionEvent> {
    
    private MonthBudget month;
    private MonthBudget newMonth;
    
    private EditReviewYearBudgetController observer;
    private Map<Integer, Expense> expenseMap;
    
    public EditMonth(MonthBudget m, Object observer){
        this.month = m;
        this.observer = (EditReviewYearBudgetController)observer;  
    }
    
    @Override
    public void handle(ActionEvent event) {
        
        initStructValues();
       
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editReviewYearBudget/editMonth/editMonth.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
             new Alerts().information("Made it here", "Yeeeaaah");
            Logger.getLogger(EditMonth.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(MonthStruct.submitClicked){
           MonthStruct.submitClicked = false;
           updateMonth();
           notifyObserver();
       }    
    }
    
    private void initStructValues(){
        MonthStruct.monthInt = this.month.getMonthValue();
        MonthStruct.expenseMap = this.month.getExpenseMap();
        MonthStruct.income = this.month.getMonthlyIncome();
        MonthStruct.totalExpenses = this.month.getMonthlyExpenses();
    }
    
    private void updateMonth(){
        this.newMonth = new MonthBudget(this.month.getMonthValue(), MonthStruct.expenseMap, MonthStruct.income);
    }
    
    private void notifyObserver(){
        this.observer.update(this.newMonth, this.month);
    }
    
    public MonthBudget getMonth(){
        return this.month;
    }  
}