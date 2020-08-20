package eventHandlers.editMonth;

import budgetLogic.MonthBudget;
import dialogWindows.Alerts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author steve
 */
public class EditMonth implements EventHandler<ActionEvent>{
    
    private MonthBudget month;
    
    public EditMonth(MonthBudget m){
        this.month = m;
    }
    @Override
    public void handle(ActionEvent event) {
        new Alerts().warning("Test", "This is a test for " + this.month.getName());
    }
    
    public MonthBudget getMonth(){
        return this.month;
    }  
}