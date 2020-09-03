package newBudgetPage;

import Structs.ExpenseListStruct;
import dialogWindows.Alerts;
import Structs.ExpenseStruct;
import Structs.MonthStruct;
import budgetLogic.*;
import interfaces.Controller;
import enums.TextColor;
import newBudgetPage.expenseCellFactory.ExpenseListCellController;
import observer.Observer;
import utils.GlobalButtonInfo;
import utils.ScrubUserData;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;


/**
 * FXML Controller class
 *
 * @author steve
 */

// OBSERVER PATTERN: THIS IS OBSERVER
public class NewBudgetPageController extends Observer implements Initializable, Controller {

    @FXML private Label label;
    @FXML private TextField monthIncomeField;
    @FXML private Label monthIncomeValue;
    @FXML private Label totalMonthlyExpensesValue;
    @FXML private Label totalYearlyExpensesValue;
    @FXML private Label yearlyIncome;
    @FXML private Label yearlyIncomeValue;
    
    @FXML private Button addExpenseBtn;
    @FXML private Button addIncomeBtn;
    
    @FXML private ListView<Expense> expensesListView;
    
    private ObservableList<Expense> expensesList;
    
    private Alerts alerts = new Alerts();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAddExpense();
        initAddIncome();
        if(ExpenseListStruct.backPressed){
            fillExpenseFields();
        }
        if(MonthStruct.backPressed){
            fillMonthFields();
            updateYearlyIncomeTotal();
        }
    }
    
    @Override
    public void update(Object newObj, Object oldObj) {

        this.expensesList.remove((Expense)oldObj);
        if(newObj != null){
            this.expensesList.add((Expense) newObj);
        }   
        updateExpenseListDisplay();
        updateMonthlyExpensesTotal();
    }
    
    private void fillMonthFields(){
        MonthStruct.backPressed = false;   
        this.monthIncomeValue.setText(MonthStruct.income.toString());
        this.monthIncomeValue.setTextFill(TextColor.STANDARD.getColor());
    }
    
    private void fillExpenseFields(){        
        this.expensesList = ExpenseListStruct.expenses;
        ExpenseListStruct.backPressed = false;
        
        updateExpenseListDisplay();
        updateMonthlyExpensesTotal();
    }

    private void initAddExpense(){
        this.addExpenseBtn.setTooltip(new Tooltip("Add Expense"));
        ImageView view = new ImageView("/images/plus.png");
        
        view.setFitHeight(GlobalButtonInfo.plusIconHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.addExpenseBtn.setGraphic(view);
    }
    
    private void initAddIncome(){
        this.addIncomeBtn.setTooltip(new Tooltip("Add Income"));
        ImageView view = new ImageView("/images/plus.png");
        
        view.setFitHeight(GlobalButtonInfo.plusIconHeight);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        
        this.addIncomeBtn.setGraphic(view);
    }
  
    public void messageToUser(String message, Paint color){
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    public void clearMessageToUser(){
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
            if(!tempBD.toString().equals(0) && verifyAddNewMonthIncomePress()){
                this.monthIncomeValue.setText("$" + incomeValue);
                MonthStruct.income = tempBD;
                this.monthIncomeValue.setTextFill(TextColor.STANDARD.getColor());
            } 
        }
        updateYearlyIncomeTotal();   
    }
    
    @FXML
    private void handleAddExpenseClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/newBudgetPage/addExpense/addExpense.fxml"));
        Parent parent = fxmlLoader.load();
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();        
        
       if(ExpenseStruct.submitClicked){
           ExpenseStruct.submitClicked = false;
           addExpenseFromStruct();
       }
    }
    
    private void addExpenseFromStruct(){
        if(this.expensesList == null){
            this.expensesList = FXCollections.observableArrayList();
        }
        this.expensesList.add(new Expense(
                (this.expensesList.size() + 1), 
                ExpenseStruct.name, 
                ExpenseStruct.amount, 
                ExpenseStruct.color)
        );
        updateExpenseListDisplay();
        updateMonthlyExpensesTotal();
        
    }
    
    private void updateExpenseListDisplay(){     
        
        this.expensesListView.setItems(this.expensesList);
        this.expensesListView.setCellFactory(customListView -> new ExpenseListCellController(this));
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
            MonthStruct.totalExpenses = total;
            this.totalMonthlyExpensesValue.setText("$" + total.toString());
        }
        else{
            this.totalMonthlyExpensesValue.setText("$0.00");
        }
        
        this.totalMonthlyExpensesValue.setTextFill(TextColor.STANDARD.getColor());
               
        updateYearlyExpensesTotal();   
    }
    
    private void updateYearlyExpensesTotal(){

        if(MonthStruct.totalExpenses.equals(0)){
            this.totalYearlyExpensesValue.setText("$0.00");
        }
        else{
            
            this.totalYearlyExpensesValue.setText("$" + MonthStruct.totalExpenses.multiply(new BigDecimal("12")).toString());
        }
        this.totalYearlyExpensesValue.setTextFill(TextColor.STANDARD.getColor());  
    }
    
    private void updateYearlyIncomeTotal(){

        if(MonthStruct.income.equals(0)){
            this.yearlyIncomeValue.setText("$0.00");
        }
        else{
            this.yearlyIncomeValue.setText("$" + MonthStruct.income.multiply(new BigDecimal("12")).toString());
        }      
        this.yearlyIncomeValue.setTextFill(TextColor.STANDARD.getColor());
    }
    
    @FXML
    private void handleNextClick(ActionEvent event){
        ExpenseListStruct.expenses = this.expensesList;
        
        if(this.expensesList != null){
            //fillMonthStructValues();
        }
        
        if(verifyNextPress()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/setExpensePriority/setExpensePriority.fxml"));          
                Scene scene= new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Set Expense Priorities");
                stage.setScene(scene);
                stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
                messageToUser("Error on NEXT button", TextColor.ERROR.getColor());
            }
        }     
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
}
