/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalBudgetPage.menu;

import Structs.FinalBudgetStruct;
import dialogWindows.Alerts;
import enums.TextColor;
import interfaces.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class MenuController extends Application implements Initializable, Controller{
    
    @FXML private Label label;
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getStylesheets().add(getClass().getResource("/mainPage/MainPage.css").toExternalForm());
        this.saveBtn.setDisable(!FinalBudgetStruct.needSaving);

    }    
    
    @Override
    public void messageToUser(String message, Paint color) {
        this.label.setTextFill(color);
        this.label.setText(message);
    }
    
    public void clearMessageToUser(){
        this.label.setText("");
    }
    
    @FXML
    public void onNewBtnClick(){
        messageToUser("New Clicked", TextColor.TEST.getColor());
    }
    
    @FXML
    public void onSaveBtnClick(){
        messageToUser("Save Clicked", TextColor.TEST.getColor());
    }
    
    @FXML
    public void onSaveAsBtnClick(){
        messageToUser("SaveAs Clicked", TextColor.TEST.getColor());
    }
    
    @FXML
    public void onLoadBtnClick(){
        //messageToUser("Load Clicked", TextColor.TEST.getColor());
        boolean load = new Alerts().warning2Choice(
                "WARNING",
                "All unsaved data will be lost.",
                "Load new budget?"
        );
        if(load){
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Load Budget");
            chooser.showOpenDialog(this.stage);
        }
    }
    
    @FXML
    public void onQuitBtnClick(){
        boolean quit = new Alerts().warning2Choice(
                "WARNING", 
                "All unsaved data will be lost.",
                "Are you sure you want to quit?"
        );
        if(quit){
            Platform.exit();
        }  
    }
    
    @FXML
    public void onCancelBtnClick(){
        //messageToUser("Cancel Clicked", TextColor.TEST.getColor());
        Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
        stage.close(); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
    }
}
