package eventHandlers;

import budgetLogic.Expense;
import dialogWindows.Alerts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author steve
 */
public class EditExpenseEventHandler implements EventHandler<ActionEvent> {
    
    private Expense expense;
    
    public EditExpenseEventHandler(Expense e){
        this.expense = e;
    }
    @Override
    public void handle(ActionEvent event) {
        new Alerts().warning("Test", "This is a test for " + this.expense.getName());
    }
    
    public Expense getExpense(){
        return this.expense;
    }  
}
