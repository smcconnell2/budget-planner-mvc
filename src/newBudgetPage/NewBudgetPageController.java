/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage;
import newBudgetPage.addExpense.AddExpenseController;
import newBudgetPage.addExpense.ExpenseStruct;
import budgetLogic.*;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.RED;



/**
 * FXML Controller class
 *
 * @author steve
 */
public class NewBudgetPageController implements Initializable {

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
    
    private Paint defaultColor = GRAY;
    private Paint inputColor = BLACK;
    private Paint errorColor = RED;
    
    // Some variable to hold the available months
    //private Map<String, boolean> availableMonths = new HashMap<String, boolean>
    // maybe use setItems to fill the list with available months
    // also use setItems to populate category list
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // is called once all @FXML methods are called and loaded
        // maybe use to populate fields with data to ensure that everything
        // is loaded properly first
    }    
    
    @FXML
    private void handleBackButton(ActionEvent event) {
        messageToUser("Back Clicked"); // temp error checking
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

    private void messageToUser(String message){
        this.label.setText(message);
    }
    
    
    @FXML
    private void handleAddMonthIncomeClick(ActionEvent event){
        messageToUser("Submit Month Income Clicked"); // temp check
        
        String incomeValue = this.monthIncomeField.getText();// run against Regex
        if(incomeValue.equals("")){
            this.monthIncomeValue.setText("$0.00");
        }
        else{
            this.monthIncomeValue.setText("$" + incomeValue);
            this.defaultMonth.setIncome(incomeValue);
        }
        
        this.monthIncomeValue.setTextFill(this.inputColor);
        
        updateYearlyIncomeTotal();
        
    }
    
    /*@FXML
    private void handleAddExpenseClick(ActionEvent event) throws IOException{
        messageToUser("Add Expense"); // temp check
        
        this.expensesList = FXCollections.observableArrayList(
                new Expense(1, "Test"),
                new Expense(2, "stuff"),
                new Expense(3, "other")
        );
        this.expensesListView.setItems(FXCollections.observableArrayList(this.expensesList));
        
        newExpensePopup();
    }*/
    
    @FXML
    private void handleAddExpenseClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/newBudgetPage/addExpense/addExpense.fxml"));
        Parent parent = fxmlLoader.load();
        AddExpenseController dialogController = fxmlLoader.<AddExpenseController>getController();
        //dialogController.setAppMainObservableList(tvObservableList);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();        
        
       updateMonthlyExpensesTotal();
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
    }
    
    private void updateExpenseListDisplay(){
        this.expensesListView.setItems(FXCollections.observableArrayList(this.expensesList));
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
                total.add(e.getAmount());
            }
            this.totalMonthlyExpensesValue.setText("$" + total.toString());
        }
        else{
            this.totalMonthlyExpensesValue.setText("$0.00");
        }
        
        this.totalMonthlyExpensesValue.setTextFill(this.inputColor);
               
        updateYearlyExpensesTotal();   
    }
    
    private void updateYearlyExpensesTotal(){

        if(this.defaultMonth.getTotalExpenses().toString().equals("0")){
            this.totalYearlyExpensesValue.setText("$0.00");
        }
        else{
            
            this.totalYearlyExpensesValue.setText("$" + this.defaultMonth.getTotalExpenses().multiply(new BigDecimal("12")).toString());
        }
        this.totalYearlyExpensesValue.setTextFill(this.inputColor);  
    }
    
    private void updateYearlyIncomeTotal(){
        if(this.defaultMonth.getIncome().toString().equals("0")){
            this.yearlyIncomeValue.setText("$0.00");
        }
        else{
            this.yearlyIncomeValue.setText("$" + defaultMonth.getIncome().multiply(new BigDecimal("12")).toString());
        }      
        this.yearlyIncomeValue.setTextFill(this.inputColor);
    }

    
    @FXML
    private void handleNextClick(ActionEvent event){
        messageToUser("Next Clicked"); // temp check
    }
}
