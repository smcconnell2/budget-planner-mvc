/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setExpensePriority.priorityExpenseCellFactory;

import Structs.ExpenseListStruct;
import budgetLogic.Expense;
import setExpensePriority.SetExpensePriorityController;
import observer.ObservableCell;

import interfaces.Controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * FXML Controller class
 *
 * @author steve
 */
public class PriorityExpenseCellController extends ListCell<Expense> implements Controller, ObservableCell{
    @FXML private GridPane gridPane;
    @FXML private Label priorityLabel;
    @FXML private Label nameLabel;
    @FXML private Label amountLabel; 
    
    private FXMLLoader loader;   
    private SetExpensePriorityController observer;
    private double fullOpacity = 1.0;
    private double dragOverOpacity = 0.3;
    
    public PriorityExpenseCellController(Object obj){
        this.observer = (SetExpensePriorityController)obj;
    }
    
    @Override
    protected void updateItem(Expense expense, boolean empty){
        super.updateItem(expense, empty);
        
        if(empty || expense == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/setExpensePriority/priorityExpenseCellFactory/priorityExpenseCell.fxml"));
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
    
    @Override
    public void messageToUser(String message, Paint color) {
        this.nameLabel.setText(message);
    }
    
    private void setCellProperties(Expense expense){
        this.priorityLabel.setText(expense.getPriority() + "");
        this.nameLabel.setText(expense.getName());
        this.amountLabel.setText(expense.getAmount().toString());
    } 
    
    @FXML
    private void onDragDetected(Event event){
        Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(this.nameLabel.getText());

        Image move = new Image("/images/move.png");
        
        dragboard.setDragView(
            new Image("/images/move3.png")
        );
        dragboard.setContent(content);

        event.consume();
    }
    
    @FXML
    private void onDragOver(DragEvent event){
        if(event.getGestureSource() != this){
            event.acceptTransferModes(TransferMode.MOVE);
        }  
        event.consume();
    }
    
    @FXML
    private void onDragEnter(DragEvent event){
        if(event.getGestureSource() != this){
            this.setOpacity(this.dragOverOpacity);
        }  
    }
    
    @FXML
    private void onDragExit(DragEvent event){
        if(event.getGestureSource() != this){
            this.setOpacity(this.fullOpacity);
        }  
    }
    
    @FXML
    private void onDragDropped(DragEvent event){
        
        handleDropped2(event);
       /* Dragboard db = event.getDragboard();
        
        ObservableList<Expense> items = getListView().getItems();
        int draggedIndex = items.indexOf(db.getString());
        int thisIndex = items.indexOf(this.getItem());
        
        items.set(draggedIndex, this.getItem());
        items.set(thisIndex, (Expense)event.getSource());
        

        List<Expense> itemsCopy = new ArrayList<>();
        itemsCopy.add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        ObservableList<Expense> items2 = null;
        items2.add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        
        this.getListView().setItems(items2);*/
       
        //this.getListView().setCellFactory(customListView -> new ExpenseListCellController(new NewBudgetPageController()));
        
        //List<Expense> itemsCopy = new ArrayList<>(this.getListView().getItems());
        //this.getListView().getItems().setAll(itemsCopy);
        
        //this.listView.setItems(this.expensesList);
        
        //event.setDropCompleted(true);
        //event.consume();
    }
    
    private void handleDropped2(DragEvent event){
        //Alerts alert = new Alerts();
        //alert.warning("test", "test2");
        
        /*Dragboard db = event.getDragboard();
        
        ObservableList<Expense> items = this.getListView().getItems();
        Expense test = (Expense)event.getSource();
        int index = items.indexOf(test);*/
        
        List<Expense> itemsCopy = new ArrayList<>();
        itemsCopy.add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        notifyObserver(itemsCopy);
        //this.getListView().getItems().add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        
        
            //this.expensesList = FXCollections.observableArrayList();
        /*
        this.expensesList.add(new Expense(
                (this.expensesList.size() + 1), 
                ExpenseStruct.name, 
                ExpenseStruct.amount, 
                ExpenseStruct.color)
        );
        updateExpenseListDisplay();      
    */
    }
    
    private void handleDropped(DragEvent event){
        Dragboard db = event.getDragboard();
        
        ObservableList<Expense> items = this.getListView().getItems();
        Expense test = (Expense)event.getSource();
        int index = items.indexOf(test);
        
        List<Expense> itemsCopy = new ArrayList<>();
        itemsCopy.add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        ExpenseListStruct.expenses.add(new Expense(1, "Test", new BigDecimal(0.0), Color.BLACK));
        //items.setAll(itemsCopy);
        //this.getListView().getItems().setAll(itemsCopy);
        //this.getListView().setCellFactory(customListView -> new ExpenseListCellController(new NewBudgetPageController()));
        //String name = this.getListView().getId();
        
        //items.remove(test);
        //this.getListView().setItems(items);
        
    }
    
    public void notifyObserver(List list){
        this.observer.update(list);
    }
    
}
