package newBudgetPage.editExpense;

import Structs.ExpenseStruct;
import budgetLogic.Expense;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import newBudgetPage.NewBudgetPageController;

/**
 *
 * @author steve
 */
public class EditExpense implements EventHandler<ActionEvent> {
    
    private Expense expense;
    private Expense newExpense;
    
    private NewBudgetPageController observer;
    private ObservableList<Expense> expenseList;
    
    public EditExpense(Expense e, Object observer){
        this.expense = e;
        this.observer = (NewBudgetPageController)observer;
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
           notifyObserver();
       }    
    }
    
    private void initStructValues(){
        ExpenseStruct.name = this.expense.getName();
        ExpenseStruct.amount = this.expense.getAmount();
        ExpenseStruct.color = this.expense.getColor();
    }
    
    private void updateExpense(){
        this.newExpense = new Expense(this.expense.getPriority(), ExpenseStruct.name, ExpenseStruct.amount, ExpenseStruct.color);
    }
    
    private void notifyObserver(){
        this.observer.update(this.newExpense, this.expense);
    }
    
    public Expense getExpense(){
        return this.expense;
    }  
}
