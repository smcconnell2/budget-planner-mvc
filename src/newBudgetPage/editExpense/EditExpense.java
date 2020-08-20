package newBudgetPage.editExpense;

import Structs.ExpenseStruct;
import budgetLogic.Expense;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author steve
 */
public class EditExpense implements EventHandler<ActionEvent> {
    
    private Expense expense;
    
    public EditExpense(Expense e){
        this.expense = e;
    }
    @Override
    public void handle(ActionEvent event) {
        
        initStructValues();
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/newBudgetPage/editExpense/editExpense.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(EditExpense.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        if(ExpenseStruct.submitClicked){
           ExpenseStruct.submitClicked = false;
           updateExpense();
       }    
    }
    
    private void initStructValues(){
        ExpenseStruct.name = this.expense.getName();
        ExpenseStruct.amount = this.expense.getAmount();
        ExpenseStruct.color = this.expense.getColor();
    }
    
    private void updateExpense(){
        this.expense.setAmount(ExpenseStruct.amount);
        this.expense.setName(ExpenseStruct.name);
        this.expense.setColor(ExpenseStruct.color);
    }
    
    
    public Expense getExpense(){
        return this.expense;
    }  
}
