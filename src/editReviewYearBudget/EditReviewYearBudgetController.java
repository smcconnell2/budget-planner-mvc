/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReviewYearBudget;

import Structs.MonthStruct;
import Structs.YearStruct;
import budgetLogic.MonthBudget;
import editReviewYearBudget.monthCellFactory.MonthListCellController;
import enums.TextColor;
import interfaces.Controller;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import newBudgetPage.NewBudgetPageController;
import observer.Observer;
import utils.GlobalButtonInfo;
import utils.ScrubUserData;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class EditReviewYearBudgetController extends Observer implements Initializable, Controller {
    
    @FXML private Label label;
    @FXML private ListView monthListView;
    @FXML private Button addIncomeBtn;
    @FXML private TextField yearIncomeField;
    @FXML private Label yearlyIncomeValue;
    @FXML private Label totalYearlyExpensesValue;
    
    private ObservableList<MonthBudget> monthList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAddIncome();
        initializeMonthList();
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
    
    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    @Override
    public void update(Object newObj, Object oldObj) {
        
        messageToUser("updated", TextColor.TEST.getColor());
        this.monthList.remove((MonthBudget)oldObj);
        if(newObj != null){
            this.monthList.add((MonthBudget) newObj);
        }
        
        updateMonthListDisplay();
        updateYearlyExpenses();
    }
    
    private void updateYearlyExpenses(){
        BigDecimal totalExpenses = new BigDecimal("0");
        
        for(MonthBudget m : this.monthList){
            totalExpenses.add(m.getMonthlyExpenses());
        }
        
        this.totalYearlyExpensesValue.setText(totalExpenses.toString());
    }
    
    private void initializeMonthList(){
        
        if(this.monthList == null){
            this.monthList = FXCollections.observableArrayList();
        }
        
        for(int i = 1; i < 13; i ++){
            this.monthList.add(new MonthBudget(i, MonthStruct.expenseMap, MonthStruct.income));
        }
        updateMonthListDisplay();        
    }
    
    private void updateMonthListDisplay(){
        
        this.monthListView.setItems(this.monthList);
        this.monthListView.setCellFactory(customListView -> new MonthListCellController(this));
    }
    
    @FXML
    public void handleFinishClick(ActionEvent event){
        updateYearStruct();
        
         try{
            Parent root = FXMLLoader.load(getClass().getResource("/finalBudgetPage/finalBudgetPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("MY Budget");
            stage.setScene(scene);
            stage.show();
        } catch(IOException ex){
            Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void updateYearStruct(){
        Map<Integer, MonthBudget> tempMap = new HashMap<>();
        
        for(int i = 0; i < this.monthList.size(); i ++){
            tempMap.put(this.monthList.get(i).getMonthValue(), this.monthList.get(i));
        }
        YearStruct.monthMap = tempMap;
    }
    
    @FXML
    private void handleBackButton(ActionEvent event) {     
        if(varifyBackPress()){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/setExpensePriority/setExpensePriority.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Set Expense Priorities");
                stage.setScene(scene);
                stage.show();
            } catch(IOException ex){
                Logger.getLogger(NewBudgetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
    
    private void editMonth(){
        
    }
    
    private boolean varifyBackPress(){
        // add logic to check values
        return true;
    }
    
    public void clearMessageToUser(){
        this.label.setText("");
    }
    
    @FXML
    public void handleAddYearIncomeClick(){
        clearMessageToUser();
        
        String incomeValue = this.yearIncomeField.getText();
        BigDecimal tempBD = ScrubUserData.parseToBigDecimal(incomeValue);
        if(tempBD.intValue() < 0){
            messageToUser("Enter a valid dollar amount", TextColor.ERROR.getColor());
        }
        else{
            if(verifyAddNewYearIncomePress()){
                this.yearlyIncomeValue.setText("$" + incomeValue);
                
            } 
        }
        
        //this.yearIncomeField.setTextFill(TextColor.STANDARD.getColor());
        
        updateYearlyIncomeTotal();
    }
    
    private void updateYearlyIncomeTotal(){
        
    }
    
    private boolean verifyAddNewYearIncomePress(){
        return true;
    }

}
