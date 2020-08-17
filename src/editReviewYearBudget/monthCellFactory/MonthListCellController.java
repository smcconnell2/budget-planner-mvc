package editReviewYearBudget.monthCellFactory;

import budgetLogic.MonthBudget;
import dialogWindows.Alerts;
import eventHandlers.EditMonthEventHandler;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
public class MonthListCellController extends ListCell<MonthBudget> {
    
    @FXML private Label nameLabel;
    @FXML private Label incomeLabel;
    @FXML private Label totalExpensesLabel;
    @FXML private Button editMonthBtn;
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
            setCellProperties(month);
            setText(null);
            setGraphic(this.gridPane);
         }   
    }   
    
    private void setCellProperties(MonthBudget month){
            this.nameLabel.setText(month.getName());
            this.incomeLabel.setText(month.getMonthlyIncome().toString());
            this.totalExpensesLabel.setText(month.getMonthlyExpenses().toString());

            EditMonthEventHandler eventHandle = new EditMonthEventHandler(month);
            this.editMonthBtn.setOnAction(eventHandle);
    }
}
