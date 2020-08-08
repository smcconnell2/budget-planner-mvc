/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage.expenseCellFactory;

import budgetLogic.Expense;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class ExpenseListCellController extends ListCell<Expense> {
    
    @FXML private Label priorityLabel;
    @FXML private Label nameLabel;
    @FXML private Label amountLabel;
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
                    e.printStackTrace();
                }

            }
            
            priorityLabel.setText(expense.getPriority() + "");
            nameLabel.setText(expense.getName());
            amountLabel.setText(expense.getAmount() + "");
            
            setText(null);
            setGraphic(this.gridPane);
         }   
    }   
}
