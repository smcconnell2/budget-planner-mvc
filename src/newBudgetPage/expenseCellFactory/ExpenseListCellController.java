package newBudgetPage.expenseCellFactory;

import budgetLogic.Expense;
import dialogWindows.Alerts;
import newBudgetPage.editExpense.EditExpense;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import newBudgetPage.NewBudgetPageController;
import utils.GlobalButtonInfo;

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
    private Expense expense;
    private NewBudgetPageController observer;
    
    public ExpenseListCellController(Object observer){
        this.observer = (NewBudgetPageController)observer;
    }
    
    @Override
    protected void updateItem(Expense expense, boolean empty){
        this.expense = expense;
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
    
    private void setCellProperties(Expense expense){
        this.expense = expense;
        this.nameLabel.setText(expense.getName());
        this.amountLabel.setText(expense.getAmount() + "");
        
        setEditButtonImage();
        setRemoveButtonImage();

        EditExpense eventHandle = new EditExpense(expense, this.observer);
        this.editExpenseBtn.setOnAction(eventHandle);
        
    } 
        private void setRemoveButtonImage(){
        this.removeExpenseBtn.setTooltip(new Tooltip("Remove " + this.nameLabel.getText()));
        ImageView view = new ImageView("/images/x.png");
        
        view.setFitWidth(GlobalButtonInfo.standardBtnHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.removeExpenseBtn.setGraphic(view); 
    }
    
        private void setEditButtonImage(){
        this.editExpenseBtn.setTooltip(new Tooltip("Edit " + this.nameLabel.getText()));
        ImageView view = new ImageView("/images/pencil.png");
        
        view.setFitWidth(GlobalButtonInfo.standardBtnHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.editExpenseBtn.setGraphic(view); 
    }
    
    @FXML
    private void onRemoveExpense(){
        boolean remove = new Alerts().warning2Choice("WARNING", "Remove this expense?", "Expense Information will be lost.");
        if(remove){
           this.observer.update(null, this.expense);
        }
    }
    
}
