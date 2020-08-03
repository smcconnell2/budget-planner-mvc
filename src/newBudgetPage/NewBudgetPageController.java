/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBudgetPage;
import newBudgetPage.addExpense.AddExpenseController;
import budgetLogic.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    private ListView<Expenses> expensesListView;
    
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
    
    /*@FXML
    private void handleAddExpenseClick(ActionEvent event) throws IOException{
        messageToUser("Add Expense"); // temp check
        
        this.expensesList = FXCollections.observableArrayList(
                new Expenses(1, "Test"),
                new Expenses(2, "stuff"),
                new Expenses(3, "other")
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

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        
        
        
        
       /*
        AddExpenseController controller = null;
        try{
            controller = new AddExpenseController();
            Optional<ButtonType> option = controller.showAndWait();
        }catch(IOException ex){
            messageToUser("controller error");
            Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        /*
        this.expensesList.add(new Expenses(4, "new"));
        this.expensesListView.setItems(FXCollections.observableArrayList(this.expensesList));*/
    }

    
    @FXML
    private void handleNextClick(ActionEvent event){
        messageToUser("Next Clicked"); // temp check
    }
}
