/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setExpensePriority;

import Structs.ExpenseListStruct;
import Structs.MonthStruct;
import budgetLogic.Expense;
import enums.TextColor;
import interfaces.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import newBudgetPage.NewBudgetPageController;
import observer.CellObserver;
import setExpensePriority.priorityExpenseCellFactory.PriorityExpenseCellController;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class SetExpensePriorityController extends CellObserver implements Initializable, Controller{
    
    @FXML Label label;
    @FXML Button backBtn;
    @FXML Button nextBtn;
    @FXML ListView listView;
    
    private ObservableList<Expense> expensesList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.expensesList = ExpenseListStruct.expenses;
        initListView();
    }    
    
    public void update(List list){
        messageToUser("Working", TextColor.TEST.getColor());
        this.expensesList.setAll((ArrayList)list);
        this.listView.setItems(this.expensesList);
    }

    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setText(message);
        this.label.setTextFill(color);
    }
    
    private void initListView(){
       this.listView.setItems(this.expensesList);
       this.listView.setCellFactory(customListView -> new PriorityExpenseCellController(this));
   
    }  
    
    @FXML
    private void handleNextClick(ActionEvent event){
        messageToUser("Next Button pressed", TextColor.TEST.getColor());
        updateMonthStructValues();
        
        
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
    
    @FXML
    private void handleBackClick(ActionEvent event){
        messageToUser("Back Button Clicked", TextColor.TEST.getColor());
        if(varifyBackPress()){
            ExpenseListStruct.backPressed = true;
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/newBudgetPage/newBudgetPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("New Budget");
                stage.setScene(scene);
                stage.show();
            } catch(IOException ex){
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    private boolean varifyBackPress(){
        // add logic to check values
        return true;
    }
    
    private void updateMonthStructValues(){
        
       int counter = 1;
       Map<Integer, Expense> tempMap = new HashMap<>();
       for(Expense e: this.expensesList){
           tempMap.put(e.getPriority(),e);
           //tempMap.put(counter, e);
           counter ++;
       }      
       MonthStruct.expenseMap = tempMap;
       messageToUser("Here", TextColor.ERROR.getColor());
    }
    
    private boolean verifyNextPress(){
        return true;
    }
}
