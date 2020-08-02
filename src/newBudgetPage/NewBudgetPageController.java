/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage;
import budgetLogic.*;

import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class NewBudgetPageController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField monthIncomeField;
    @FXML
    private ListView<Expenses> expensesListview;
    
    private ObservableList<Expenses> expensesList;

    private DefaultMonthBudget defaultMonth = new DefaultMonthBudget();
    
    
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
        
        this.monthIncomeField.setText("works");
    }
    
    @FXML
    private void handleAddExpenseClick(ActionEvent event){
        messageToUser("Add Expense"); // temp check
        
        ObservableList<String> names = FXCollections.observableArrayList(
          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        
        this.expensesList = FXCollections.observableArrayList(
                new Expenses(1, "Test"),
                new Expenses(2, "stuff"),
                new Expenses(3, "other")
        );
        this.expensesListview.setItems(FXCollections.observableArrayList(this.expensesList));
        //this.expensesListview.setItems(FXCollections.observableArrayList(names));
    }

    
    @FXML
    private void handleNextClick(ActionEvent event){
        messageToUser("Next Clicked"); // temp check
    }
}
