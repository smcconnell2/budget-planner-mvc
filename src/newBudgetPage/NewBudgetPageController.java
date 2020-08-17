package newBudgetPage;
import dialogWindows.Alerts;
import newBudgetPage.addExpense.AddExpenseController;
import Structs.ExpenseStruct;
import Structs.MonthStruct;
import budgetLogic.*;
import interfaces.Controller;
import enums.TextColor;
import newBudgetPage.expenseCellFactory.ExpenseListCellController;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import utils.ScrubUserData;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class NewBudgetPageController implements Initializable, Controller {

    @FXML private Label label;
    @FXML private TextField monthIncomeField;
    @FXML private Label monthIncomeValue;
    @FXML private Label totalMonthlyExpensesValue;
    @FXML private Label totalYearlyExpensesValue;
    @FXML private Label yearlyIncome;
    @FXML private Label yearlyIncomeValue;
    
    @FXML private ListView<Expense> expensesListView;
    
    private ObservableList<Expense> expensesList;
    private DefaultMonthBudget defaultMonth = new DefaultMonthBudget();
    
    private Alerts alerts = new Alerts();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // is called once all @FXML methods are called and loaded
        // maybe use to populate fields with data to ensure that everything
        // is loaded properly first
    }    
    
    
    public void messageToUser(String message, Paint color){
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    private void clearMessageToUser(){
        this.label.setText("");
    }
    
    @FXML
    private void handleBackButton(ActionEvent event) {     
        if(varifyBackPress()){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/mainPage/mainPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Budget Planner");
                stage.setScene(scene);
                stage.show();
            } catch(IOException ex){
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }

    @FXML
    private void handleAddMonthIncomeClick(ActionEvent event){
        
        clearMessageToUser();
        
        String incomeValue = this.monthIncomeField.getText();// run against Regex
        BigDecimal tempBD = ScrubUserData.parseToBigDecimal(incomeValue);
        if(tempBD.intValue() < 0){
            messageToUser("Enter a valid dollar amount", TextColor.ERROR.getColor());
        }
        else{
            if(verifyAddNewMonthIncomePress()){
                this.monthIncomeValue.setText("$" + incomeValue);
                this.defaultMonth.setIncome(incomeValue);
            } 
        }
        
        this.monthIncomeValue.setTextFill(TextColor.STANDARD.getColor());
        
        updateYearlyIncomeTotal();   
    }
    
    private boolean verifyAddNewMonthIncomePress(){
        return this.alerts.warning2Choice("Warning", "Month Income already has a value.", "Overwrite Month Income value?");
    }
    
    private boolean varifyBackPress(){
        if(this.expensesList == null){
            return true;
        }
        return this.alerts.warning2Choice("Warning", "All expense data will be lost!", "Do you still want to go back?");
    }
    
    private boolean verifyNextPress(){
        if(this.expensesList == null){
            this.alerts.warning("Error", "You must add an expense to move on."
                    + "\n\nTry adding a 'Bills' expense."
                    + "\nOr an 'Entertainment' expense."
            );
            return false;
        }
        return true;
    }
    
    @FXML
    private void handleAddExpenseClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/newBudgetPage/addExpense/addExpense.fxml"));
        Parent parent = fxmlLoader.load();
        //AddExpenseController dialogController = fxmlLoader.<AddExpenseController>getController();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();        
        
       if(ExpenseStruct.submitClicked){
           ExpenseStruct.submitClicked = false;
           addExpense();
       }
    }
    
    private void addExpense(){
        if(this.expensesList == null){
            this.expensesList = FXCollections.observableArrayList();
        }
        this.expensesList.add(new Expense(ExpenseStruct.priority, ExpenseStruct.name, ExpenseStruct.amount, ExpenseStruct.color));
        updateExpenseListDisplay();
        updateMonthlyExpensesTotal();
        
    }
    
    private void updateExpenseListDisplay(){
        SortedList<Expense> sorted = this.expensesList.sorted();
        this.expensesList.setAll(sorted);
        
        
        this.expensesListView.setItems(this.expensesList);
        this.expensesListView.setCellFactory(customListView -> new ExpenseListCellController());
    }
    
    /**
     * Iterates through each element in list of expenses and adds the total
     * value allocated for each expense and updates displayed monthly expense
     * total.
     */
    private void updateMonthlyExpensesTotal(){
 
        
        if(this.expensesList != null){
            BigDecimal total = new BigDecimal("0.00");
            for(Expense e: this.expensesList){
                total = total.add(e.getAmount());
            }
            this.defaultMonth.addTotalExpenses(total.toString());
            this.totalMonthlyExpensesValue.setText("$" + total.toString());
        }
        else{
            this.totalMonthlyExpensesValue.setText("$0.00");
        }
        
        this.totalMonthlyExpensesValue.setTextFill(TextColor.STANDARD.getColor());
               
        updateYearlyExpensesTotal();   
    }
    
    private void updateYearlyExpensesTotal(){

        if(this.defaultMonth.getTotalExpenses().toString().equals("0")){
            this.totalYearlyExpensesValue.setText("$0.00");
        }
        else{
            
            this.totalYearlyExpensesValue.setText("$" + this.defaultMonth.getTotalExpenses().multiply(new BigDecimal("12")).toString());
        }
        this.totalYearlyExpensesValue.setTextFill(TextColor.STANDARD.getColor());  
    }
    
    private void updateYearlyIncomeTotal(){
        if(this.defaultMonth.getIncome().toString().equals("0")){
            this.yearlyIncomeValue.setText("$0.00");
        }
        else{
            this.yearlyIncomeValue.setText("$" + defaultMonth.getIncome().multiply(new BigDecimal("12")).toString());
        }      
        this.yearlyIncomeValue.setTextFill(TextColor.STANDARD.getColor());
    }
    
    private void fillMonthStructValues(){
       Map<Integer, Expense> tempMap = new HashMap<>();
       for(Expense e: this.expensesList){
           tempMap.put(e.getPriority(),e);
       }      
       MonthStruct.expenseMap = tempMap;
       MonthStruct.income = this.defaultMonth.getIncome();
       MonthStruct.totalExpenses = this.defaultMonth.getTotalExpenses();
    }

    
    @FXML
    private void handleNextClick(ActionEvent event){
        fillMonthStructValues();
        if(verifyNextPress()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/editReviewYearBudget/editReviewYearBudget.fxml"));          
                Scene scene= new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Review Year Budget");
                stage.setScene(scene);
                stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
                messageToUser("Error on NEXT button", TextColor.ERROR.getColor());
            }
        }     
    }
}
