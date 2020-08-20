package newBudgetPage.expenseCellFactory;

import budgetLogic.Expense;
import newBudgetPage.editExpense.EditExpense;

import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class ExpenseListCellController extends ListCell<Expense> {
    
    @FXML private Label nameLabel;
    @FXML private Label amountLabel;
    @FXML private Button editExpenseBtn;
    @FXML private Button removeExpenseBtn;
    @FXML private GridPane gridPane;
    
    private FXMLLoader loader;
    
    @Override
    protected void updateItem(Expense expense, boolean empty){
        super.updateItem(expense, empty);
        
        if(empty || expense == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/newBudgetPage/expenseCellFactory/expenseListCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace(); // user LOGGER class here to report potential errors
                }

            }           
            setCellProperties(expense);
            setText(null);
            setGraphic(this.gridPane);
        }   
    } 
    
    @FXML public void handleRemoveExpense(ActionEvent even){
        
    }
    
    private void setCellProperties(Expense expense){
        this.nameLabel.setText(expense.getName());
        this.amountLabel.setText(expense.getAmount() + "");

        EditExpense eventHandle = new EditExpense(expense);
        this.editExpenseBtn.setOnAction(eventHandle);
    } 
    
    
}
