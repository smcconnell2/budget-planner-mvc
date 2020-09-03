/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setExpensePriority.priorityExpenseCellFactory;

import budgetLogic.Expense;
import setExpensePriority.SetExpensePriorityController;
import observer.ObservableCell;

import interfaces.Controller;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import utils.GlobalButtonInfo;
import utils.ScrubUserData;


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
    private Expense expense;
    private double fullOpacity = 1.0;
    private double dragOverOpacity = 0.3;
    
    public PriorityExpenseCellController(Object obj){
        this.observer = (SetExpensePriorityController)obj;
    }
    
    @Override
    protected void updateItem(Expense expense, boolean empty){
        super.updateItem(expense, empty);
        this.expense = expense;
        
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
    
    public void clearMessageToUser(){
        //this.label.setText("");
    }
    
    private void setCellProperties(Expense expense){
        this.priorityLabel.setText(expense.getPriority() + "");
        this.nameLabel.setText(expense.getName());
        this.amountLabel.setText(expense.getAmount().toString());
    } 
    
    @FXML
    private void onDragDetected(Event event){
        if(this.getItem() == null){
            return;
        }
        
        Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(priorityLabel.getText());
        
        double imageSize = GlobalButtonInfo.standardSmallIconHeight;
        
        Image moveImage = new Image("/images/move3.png", imageSize, imageSize, true, true);
        
        dragboard.setDragView(moveImage);
        
        dragboard.setContent(content);

        event.consume();
    }
    
    @FXML
    private void onDragOver(DragEvent event){

        if(event.getGestureSource() != this && event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.MOVE);
        }  
        event.consume();
    }
    
    @FXML
    private void onDragEnter(DragEvent event){
        if(event.getGestureSource() != this && event.getDragboard().hasString()){
            this.setOpacity(this.dragOverOpacity);
        }  
    }
    
    @FXML
    private void onDragExit(DragEvent event){
        if(event.getGestureSource() != this && event.getDragboard().hasString()){
            this.setOpacity(this.fullOpacity);
        }  
    }
    
    @FXML
    private void onDragDropped(DragEvent event){
        if(getItem() == null){
            return;
        }
        Dragboard db = event.getDragboard();
        boolean success = false;
        
        if(db.hasString()){

            int indexDraggedFrom = ScrubUserData.parseInt(db.getString());      
            int indexDroppedOn = this.expense.getPriority();

            notifyObserver(indexDroppedOn, indexDraggedFrom);
            success = true;
            
        }
            event.setDropCompleted(success);
            event.consume();
        
    }
    
    @FXML
    private void onDragDone(DragEvent event){
        event.consume();
    }
    
    public void notifyObserver(int dropped, int dragged){
        this.observer.update(dropped, dragged);
    }
    
}
