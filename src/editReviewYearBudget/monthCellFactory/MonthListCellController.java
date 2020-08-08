package editReviewYearBudget.monthCellFactory;

import budgetLogic.Expense;
import budgetLogic.MonthBudget;

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
public class MonthListCellController extends ListCell<MonthBudget> {
    
    @FXML private Label nameLabel;
    @FXML private Label incomeLabel;
    @FXML private Label expenseTotalLabel;
    @FXML private GridPane gridPane;
    
    private FXMLLoader loader;
    
    @Override
    protected void updateItem(MonthBudget month, boolean empty){
        super.updateItem(month, empty);
        
         if(empty || month == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/editReviewYearBudget/monthCellFactory/monthListCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
            priorityLabel.setText(month.getPriority() + "");
            nameLabel.setText(month.getName());
            amountLabel.setText(month.getAmount() + "");
            
            setText(null);
            setGraphic(this.gridPane);
         }   
    }   
}
