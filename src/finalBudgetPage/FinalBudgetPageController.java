/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalBudgetPage;

import enums.TextColor;
import interfaces.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FinalBudgetPageController extends Application implements Initializable, Controller {
    
    @FXML private Label budgetTabLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
    @FXML
    public void handleAboutBtnClick() throws IOException{
        //this.author.aboutAuthor(this);
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/finalBudgetPage/about/about.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();  
    }
    
    @FXML
    public void handleMenuBtnClick(ActionEvent e){
        messageToUser("Menu Clicked", TextColor.TEST.getColor());
    }

    @Override
    public void messageToUser(String message, Paint color) {
        this.budgetTabLabel.setTextFill(color);
        this.budgetTabLabel.setText(message);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
